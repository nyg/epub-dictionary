package ch.nyg.ed.epub;

import ch.nyg.ed.model.opf.Item;
import ch.nyg.ed.model.opf.Itemref;

import java.util.List;

import static j2html.TagCreator.a;
import static j2html.TagCreator.body;
import static j2html.TagCreator.each;
import static j2html.TagCreator.filter;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.i;
import static j2html.TagCreator.li;
import static j2html.TagCreator.nav;
import static j2html.TagCreator.ol;
import static j2html.TagCreator.title;

public class Nav {

    private List<Item> items;

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
                                        // TODO filter some elements
                                        each(filter(items, i -> !i.getId().equals("nav")),
                                                item -> li(a(item.getId()).withHref(item.getHref())))
                                )
                        ).attr("epub:type", "toc")
                )
        ).attr("xmlns", "http://www.w3.org/1999/xhtml")
                .attr("xmlns:epub", "http://www.idpf.org/2007/ops")
                .renderFormatted();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
