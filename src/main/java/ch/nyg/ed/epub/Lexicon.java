package ch.nyg.ed.epub;

import static j2html.TagCreator.body;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.p;
import static j2html.TagCreator.title;

public class Lexicon {

    public Lexicon() {
    }

    public String render() {

        return html(
                head(
                        title("Chapter 1")
                ),
                body(
                        p("Hello, World!")
                )
        ).attr("xmlns", "http://www.w3.org/1999/xhtml").renderFormatted();
    }
}
