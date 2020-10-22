package ch.nyg.ed.epub;

import ch.nyg.ed.model.opf.Item;
import ch.nyg.ed.model.opf.Meta;
import ch.nyg.ed.model.opf.Package;
import ch.nyg.java.util.LogUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Epub {

    protected final Opf opf;

    public Epub() {
        opf = new Opf();
    }

    public void setTitle(String title) {
        opf.setTitle(title);
    }

    public void setLanguage(String lang) {
        opf.setLanguage(lang);
    }

    public void addFile(String filename, String mediaType) {
        opf.addItem(filename, mediaType);
    }

    public void setCreator(String creator) {
        opf.setCreator(creator);
    }

    public void setPublicationDate(LocalDateTime date) {
        opf.setDate(date);
    }

    public void setPublisher(String publisher) {
        opf.setPublisher(publisher);
    }

    public void make(String filename) throws IOException, JAXBException {

        // Update modified date
        Meta modified = new Meta();
        modified.setProperty(new QName("http://purl.org/dc/terms/", "modified", "dcterms"));
        modified.setValue(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        opf.getPackage().getMetadata().getMetaList().add(modified);

        // Create ZIP file
        File f = new File(filename);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));

        /* mimetype */
        ZipEntry e = new ZipEntry("mimetype");
        e.setMethod(ZipEntry.STORED);
        e.setSize(20);
        e.setCompressedSize(20);
        e.setCrc(0x2CAB616F); // pre-computed

        out.putNextEntry(e);
        out.write("application/epub+zip".getBytes());
        out.closeEntry();

        /* container.xml */
        e = new ZipEntry("META-INF/container.xml");
        out.putNextEntry(e);
        getMarshaller(ch.nyg.ed.model.container.Container.class).marshal(new Container().getContainer(), out);
        out.closeEntry();

        /* OPF */
        e = new ZipEntry("EPUB/Dictionary.opf");
        out.putNextEntry(e);
        getMarshaller(Package.class).marshal(opf.getPackage(), out);
        out.closeEntry();

        /* Manifest files */
        for (Item item : opf.getPackage().getManifest().getItemList()) {

            if (item.getId().equals("nav")) {
                continue;
            }

            // TODO buffer + charset ?

            e = new ZipEntry("EPUB/" + item.getHref());
            out.putNextEntry(e);

            int len = 0;
            byte[] buf = new byte[4096];
            InputStream in = getClass().getResourceAsStream("/" + item.getHref());
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }

            out.closeEntry();
        }

        /* Nav */
        e = new ZipEntry("EPUB/nav.xhtml");
        out.putNextEntry(e);

        Nav nav = new Nav();
        nav.setItems(opf.getPackage().getManifest().getItemList());
        out.write(nav.render().getBytes());
        out.closeEntry();

        /* Lexicon
        e = new ZipEntry("EPUB/lexicon0.xhtml");
        out.putNextEntry(e);
        out.write(new Lexicon().render().getBytes());
        out.closeEntry();
        */

        out.close();
    }

    private Marshaller getMarshaller(Class<?> clazz) {

        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            return marshaller;
        }
        catch (JAXBException e) {
            LogUtil.severe(e);
            return null;
        }
    }

}
