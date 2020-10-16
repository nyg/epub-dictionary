package ch.nyg.ed.epub;

import static j2html.TagCreator.a;
import static j2html.TagCreator.body;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.li;
import static j2html.TagCreator.nav;
import static j2html.TagCreator.ol;
import static j2html.TagCreator.title;

public class Nav {

    public Nav() {
    }

    public String render() {

        return html(
                head(
                        title("EPUB Navigation Document")
                ),
                body(
                        nav(
                                ol(
                                        li(a("Chapter 1").withHref("lexicon0.xhtml"))
                                )
                        ).attr("epub:type", "toc")
                )
        ).attr("xmlns", "http://www.w3.org/1999/xhtml")
                .attr("xmlns:epub", "http://www.idpf.org/2007/ops")
                .renderFormatted();
    }
}
