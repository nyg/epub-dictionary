package ch.nyg.ed.epub;

import ch.nyg.ed.model.opf.Item;
import ch.nyg.ed.model.opf.Package;
import ch.nyg.java.util.LogUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Epub {

    protected final Opf opf;

    public Epub() {
        opf = new Opf();
        opf.addManifestItem("nav.xhtml", "application/xhtml+xml", "nav", 0);
    }

    public void setTitle(String title) {
        opf.setTitle(title);
    }

    public void setLanguage(String lang) {
        opf.setLanguage(lang);
    }

    public void setCreator(String creator) {
        opf.setCreator(creator);
    }

    public void setPublicationDate(ZonedDateTime date) {
        opf.setPublicationDate(date);
    }

    public void setPublisher(String publisher) {
        opf.setPublisher(publisher);
    }

    public void addFile(String filename, String mediaType, String properties, int readingOrder) {
        opf.addManifestItem(filename, mediaType, properties, readingOrder);
    }

    public void addCover(String filename, String mediaType) {
        opf.addManifestItem(filename, mediaType, "cover-image");
    }

    public void make(String filename) throws IOException, JAXBException {

        opf.setModificationDate(ZonedDateTime.now());

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
        e = new ZipEntry("EPUB/content.opf");
        out.putNextEntry(e);
        getMarshaller(Package.class).marshal(opf.getPackage(), out);
        out.closeEntry();

        /* Manifest files */
        // TODO buffer + charset ?
        for (Item item : opf.getPackage().getManifest().getItems()) {

            e = new ZipEntry("EPUB/" + item.getHref());
            out.putNextEntry(e);

            if (item.getId().equals("nav.xhtml")) {
                Nav nav = new Nav();
                nav.setItems(opf.getPackage().getManifest().getItems());
                out.write(nav.render().getBytes());
            }
            else {
                int len = 0;
                byte[] buf = new byte[4096];
                InputStream in = getClass().getResourceAsStream("/" + item.getHref());
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
            }

            out.closeEntry();
        }

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
