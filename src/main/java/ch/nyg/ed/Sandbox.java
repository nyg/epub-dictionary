package ch.nyg.ed;

import ch.nyg.ed.epub.Epub;
import ch.nyg.ed.epub.Opf;

import java.util.logging.Logger;

public class Sandbox {

    private static final Logger LOG = Logger.getLogger(Sandbox.class.getName());

    public static void main(String[] args) throws Exception {

        Opf opf = new Opf();
        opf.setTitle("Greek–English Dictionary");
        opf.setLanguage("el");
        //opf.setType("dictionary");
        opf.addItem("lexicon0.xhtml", "application/xhtml+xml");

        Epub epub = new Epub();
        epub.setOpf(opf);
        epub.makeZip();
    }
}
