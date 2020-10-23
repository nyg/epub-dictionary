package ch.nyg.ed.epub;

import ch.nyg.ed.model.dc.Type;
import ch.nyg.ed.model.opf.Item;
import ch.nyg.ed.model.opf.Itemref;
import ch.nyg.ed.model.opf.Package;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Opf {

    private final Package pkg = new Package();

    public Opf() {
        pkg.setVersion(3);
        pkg.getMetadata().setPublisher("nyg/epub-dictionary");

        // TODO user should set this value uid
        pkg.getMetadata().getIdentifier().setValue("urn:uuid:" + UUID.randomUUID().toString());

        addItem("nav.xhtml", "application/xhtml+xml", "nav", true);
    }

    public Package getPackage() {
        return pkg;
    }

    public void setTitle(String title) {
        pkg.getMetadata().getTitle().setValue(title);
    }

    public void setLanguage(String language) {
        pkg.getMetadata().getLanguage().setValue(language);
    }

    public void setType(String value) {
        Type type = new Type();
        type.setValue(value);
        pkg.getMetadata().setType(type);
    }

    public void setCreator(String creator) {
        pkg.getMetadata().setCreator(creator);
    }

    public void setDate(LocalDateTime date) {
        pkg.getMetadata().setDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
    }

    public void setPublisher(String publisher) {
        pkg.getMetadata().setPublisher(publisher);
    }

    public void addItem(String filename, String mediaType, String properties, boolean addToSpine) {

        String id = filename.split("\\.")[0]; // TODO

        Item item = new Item();
        item.setHref(filename);
        item.setId(id);
        item.setMediaType(mediaType);
        item.setProperties(properties);

        Itemref itemref = new Itemref();
        itemref.setIdref(id);

        pkg.getManifest().getItems().add(item);

        if (addToSpine) {
            pkg.getSpine().getItemRefs().add(itemref);
        }
    }
}
