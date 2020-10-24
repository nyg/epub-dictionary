package ch.nyg.ed;

import ch.nyg.ed.epub.Epub;
import ch.nyg.ed.model.opf.MediaType;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import static j2html.TagCreator.a;
import static j2html.TagCreator.each;
import static j2html.TagCreator.li;

public class Sandbox {

    private static final Map<String, String> CHAPTER_NAMES = Map.of(
            "chapter-one.xhtml", "Chapter One",
            "chapter-two.xhtml", "Chapter Two",
            Epub.NAVIGATION_DOC, "Table of Contents");

    private static final ZonedDateTime PUBLICATION_DATE = ZonedDateTime.of(
            LocalDateTime.of(1991, Month.AUGUST, 1, 0, 0), ZoneId.systemDefault());

    public static void main(String[] args) throws Exception {

        Epub epub = new Epub();

        epub.setTitle("My Own Book");
        epub.setCreator("John Smith");

        epub.setLanguage("en");

        epub.setPublisher("One Day Publisher");
        epub.setPublicationDate(PUBLICATION_DATE);

        epub.setNavigationDocumentRenderer(filenames -> each(filenames, f -> li(a(CHAPTER_NAMES.get(f)).withHref(f))));
        epub.addCover("cover.jpg", MediaType.JPEG);

        epub.addNavigationDocument(); // optional
        epub.addFile( "chapter-one.xhtml", MediaType.XHTML);
        epub.addFile( "chapter-two.xhtml", MediaType.XHTML);

        epub.make("one-page-book.epub");

//        Dictionary dict = new Dictionary();
//        dict.setTitle("Greek–English Dictionary");
//        dict.setSourceLanguage("el");
//        dict.setTargetLanguage("en");
//        dict.addWord("Ελλάς", "Greece");
//        dict.make();
    }
}
