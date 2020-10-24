package ch.nyg.ed.epub;

import ch.nyg.ed.model.opf.MediaType;
import ch.nyg.ed.model.opf.Item;
import ch.nyg.ed.model.opf.ItemRef;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static j2html.TagCreator.a;
import static j2html.TagCreator.each;
import static j2html.TagCreator.li;

public class Epub {

    public static final String NAVIGATION_DOC = "nav.xhtml";

    protected final Opf opf = new Opf();

    protected NavigationDocumentRenderer navDocRenderer = filenames -> each(filenames, f -> li(a(f).withHref(f)));
    protected boolean hasNavDoc = false;

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

    public void addCover(String filename, MediaType mediaType) {
        opf.addManifestItem(filename, mediaType, "cover-image", false);
    }

    public void addNavigationDocument() {
        opf.addManifestItem(NAVIGATION_DOC, MediaType.XHTML, "nav", true);
        this.hasNavDoc = true;
    }

    public void addFile(String filename, MediaType mediaType) {
        opf.addManifestItem(filename, mediaType);
    }

    public void setNavigationDocumentRenderer(NavigationDocumentRenderer renderer) {
        this.navDocRenderer = renderer;
    }

    public void make(String filename) throws IOException, JAXBException {

        opf.setModificationDate(ZonedDateTime.now());
        if (!hasNavDoc) {
            opf.addManifestItem(NAVIGATION_DOC, MediaType.XHTML, "nav", false);
            hasNavDoc = true;
        }

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

            if (item.getId().equals(NAVIGATION_DOC)) {
                Nav nav = new Nav();
                nav.setTitle("Table of Contents");
                List<String> filenames = opf.getPackage().getSpine().getItemRefs().stream().map(ItemRef::getIdRef).collect(Collectors.toList());
                out.write(nav.render(navDocRenderer, filenames).getBytes());
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
