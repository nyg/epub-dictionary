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

    public void setCreator(String creator) {
        pkg.getMetadata().setCreator(creator);
    }

    public void setType(String value) {
        Type type = new Type();
        type.setValue(value);
        pkg.getMetadata().setType(type);
    }

    public void addItem(String filename, String mediaType) {

        String id = filename.split("\\.")[0]; // TODO

        Item item = new Item();
        item.setHref(filename);
        item.setId(id);
        item.setMediaType(mediaType);

        Itemref itemref = new Itemref();
        itemref.setIdref(id);

        pkg.getManifest().getItemList().add(item);
        pkg.getSpine().getItemrefList().add(itemref); // TODO not always the case
    }

    private Metadata initMetadata() {

        Identifier identifier = new Identifier();
        identifier.setId("uid"); // TODO string constant
        identifier.setValue("urn:uuid:" + UUID.randomUUID().toString());

        Title title = new Title();
        Language language = new Language();

        Metadata metadata = new Metadata();
        metadata.setIdentifier(identifier);
        metadata.setTitle(title);
        metadata.setLanguage(language);

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
