package ch.nyg.ed.epub;

import ch.nyg.ed.model.dc.Type;
import ch.nyg.ed.model.opf.Item;
import ch.nyg.ed.model.opf.ItemRef;
import ch.nyg.ed.model.opf.Meta;
import ch.nyg.ed.model.opf.Package;

import javax.xml.namespace.QName;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class Opf {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private final Package pkg = new Package();

    public Opf() {
        pkg.setVersion(3);
        pkg.getMetadata().setPublisher("nyg/epub-dictionary");
        pkg.getMetadata().getIdentifier().setValue("urn:uuid:" + UUID.randomUUID().toString()); // TODO user should set this value uid
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

    public void setModificationDate(ZonedDateTime date) {
        Meta meta = new Meta();
        meta.setProperty(new QName("http://purl.org/dc/terms/", "modified", "dcterms"));
        meta.setValue(DATE_FORMAT.format(date));
        getPackage().getMetadata().getMetaList().add(meta);
    }

    public void setPublicationDate(ZonedDateTime date) {
        pkg.getMetadata().setDate(DATE_FORMAT.format(date));
    }

    public void setPublisher(String publisher) {
        pkg.getMetadata().setPublisher(publisher);
    }

    public void addManifestItem(String filename, String mediaType) {
        addManifestItem(filename, mediaType, null, -1);
    }

    public void addManifestItem(String filename, String mediaType, String properties) {
        addManifestItem(filename, mediaType, properties, -1);
    }

    public void addManifestItem(String filename, String mediaType, String properties, int spineIndex) {

        // add manifest item
        Item item = new Item();
        item.setHref(filename);
        item.setId(filename);
        item.setMediaType(mediaType);
        item.setProperties(properties);
        pkg.getManifest().getItems().add(item);

        // add spine itemref if desired
        if (spineIndex != -1) {

            ItemRef itemRef = new ItemRef();
            itemRef.setIdRef(filename);

            // add at spineIndex or at the end
            List<ItemRef> itemRefs = pkg.getSpine().getItemRefs();
            if (itemRefs.size() <= spineIndex) {
                itemRefs.add(itemRef);
            }
            else {
                itemRefs.add(spineIndex, itemRef);
            }
        }
    }
}
