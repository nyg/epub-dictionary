package ch.nyg.ed.epub;

import ch.nyg.ed.model.opf.Package;
import ch.nyg.java.util.LogUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Epub {

    private Opf opf;

    public void makeZip() throws IOException, JAXBException {

        File f = new File("dictionary.epub");
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

        /* Nav */
        e = new ZipEntry("EPUB/nav.xhtml");
        out.putNextEntry(e);
        out.write(new Nav().render().getBytes());
        out.closeEntry();

        /* Lexicon */
        e = new ZipEntry("EPUB/lexicon0.xhtml");
        out.putNextEntry(e);
        out.write(new Lexicon().render().getBytes());
        out.closeEntry();

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

    public void setOpf(Opf opf) {
        this.opf = opf;
    }
}
