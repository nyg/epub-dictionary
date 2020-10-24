package ch.nyg.ed.epub;

import java.util.List;

import static j2html.TagCreator.body;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.nav;
import static j2html.TagCreator.ol;
import static j2html.TagCreator.title;

public class Nav {

    private String title = "EPUB Navigation Document";

    public void setTitle(String title) {
        this.title = title;
    }

    public String render(NavigationDocumentRenderer renderer, List<String> filenames) {
        return html(head(title(title)),
                body(
                        nav(
                                ol(
                                        renderer.render(filenames)
                                )
                        ).attr("epub:type", "toc")
                )
        ).attr("xmlns", "http://www.w3.org/1999/xhtml")
                .attr("xmlns:epub", "http://www.idpf.org/2007/ops")
                .render();
    }
}
