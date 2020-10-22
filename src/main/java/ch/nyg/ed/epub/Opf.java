package ch.nyg.ed.epub;

import ch.nyg.ed.model.dc.Identifier;
import ch.nyg.ed.model.dc.Language;
import ch.nyg.ed.model.dc.Title;
import ch.nyg.ed.model.dc.Type;
import ch.nyg.ed.model.opf.Item;
import ch.nyg.ed.model.opf.Itemref;
import ch.nyg.ed.model.opf.Manifest;
import ch.nyg.ed.model.opf.Meta;
import ch.nyg.ed.model.opf.Metadata;
import ch.nyg.ed.model.opf.Package;
import ch.nyg.ed.model.opf.Spine;

import javax.xml.namespace.QName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Opf {

    private final Package pkg;

    public Opf() {
        pkg = new Package();
        pkg.setVersion(3);
        pkg.setUniqueIdentifier("uid");
        pkg.setMetadata(initMetadata());
        pkg.setManifest(initManifest());
        pkg.setSpine(initSpine());
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

        pkg.getManifest().getItemList().add(item);

        if (addToSpine) {
            pkg.getSpine().getItemrefList().add(itemref);
        }
    }

    private Metadata initMetadata() {

        Identifier identifier = new Identifier();
        identifier.setId("uid"); // TODO string constant
        identifier.setValue("urn:uuid:" + UUID.randomUUID().toString());

        Metadata metadata = new Metadata();
        metadata.setIdentifier(identifier);
        metadata.setTitle(new Title());
        metadata.setLanguage(new Language());
        metadata.setPublisher("nyg/epub-dictionary");

        return metadata;
    }

    private Manifest initManifest() {

        Item navItem = new Item();
        navItem.setId("nav");
        navItem.setHref("nav.xhtml");
        navItem.setMediaType("application/xhtml+xml");
        navItem.setProperties("nav");

        List<Item> items = new ArrayList<>();
        items.add(navItem);

        Manifest manifest = new Manifest();
        manifest.setItemList(items);
        return manifest;
    }

    private Spine initSpine() {
        Spine spine = new Spine();
        spine.setItemrefList(new ArrayList<>());
        return spine;
    }
}
