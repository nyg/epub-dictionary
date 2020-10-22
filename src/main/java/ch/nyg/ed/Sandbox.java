package ch.nyg.ed;

import ch.nyg.ed.epub.Epub;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.logging.Logger;

public class Sandbox {

    private static final Logger LOG = Logger.getLogger(Sandbox.class.getName());

    public static void main(String[] args) throws Exception {

        Epub epub = new Epub();
        epub.setTitle("The One-Page Book");
        epub.setCreator("John Smith");
        epub.setPublicationDate(LocalDateTime.of(1991, Month.AUGUST, 1, 0, 0));
        epub.setLanguage("en");
        epub.addFile("only-one.xhtml", "application/xhtml+xml");
        epub.make("one-page-book.epub");

//        Dictionary dict = new Dictionary();
//        dict.setTitle("Greek–English Dictionary");
//        dict.setSourceLanguage("el");
//        dict.setTargetLanguage("en");
//        dict.addWord("Ελλάς", "Greece");
//        dict.make();
    }
}
