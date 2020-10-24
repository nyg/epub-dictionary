package ch.nyg.ed;

import ch.nyg.ed.epub.Epub;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.logging.Logger;

import static j2html.TagCreator.a;
import static j2html.TagCreator.each;
import static j2html.TagCreator.li;

public class Sandbox {

    private static final Logger LOG = Logger.getLogger(Sandbox.class.getName());
    private static final Map<String, String> CHAPTER_NAMES = Map.of(
            "only-one.xhtml", "Chapter One",
            Epub.NAV_DOC, "Table of Contents");

    public static void main(String[] args) throws Exception {

        Epub epub = new Epub();

        epub.setTitle("The One-Page Book");
        epub.setCreator("John Smith");

        epub.setLanguage("en");

        epub.setPublisher("One Day Publisher");
        epub.setPublicationDate(ZonedDateTime.of(LocalDateTime.of(1991, Month.AUGUST, 1, 0, 0), ZoneId.systemDefault()));

        epub.addCover("cover.jpg", "image/jpeg");
        epub.addFile(1, "only-one.xhtml", "application/xhtml+xml");
        epub.setNavigationRenderer(0, filenames -> each(filenames, f -> li(a(CHAPTER_NAMES.get(f)).withHref(f))));

        epub.make("one-page-book.epub");

//        Dictionary dict = new Dictionary();
//        dict.setTitle("Greek–English Dictionary");
//        dict.setSourceLanguage("el");
//        dict.setTargetLanguage("en");
//        dict.addWord("Ελλάς", "Greece");
//        dict.make();
    }
}
