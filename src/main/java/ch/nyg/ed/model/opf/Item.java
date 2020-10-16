package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    /* Required attributes */

    @XmlAttribute(required = true)
    private String href;

    @XmlAttribute(required = true)
    private String id;

    @XmlAttribute(name = "media-type", required = true)
    private String mediaType;

    /* Optional attributes */

    @XmlAttribute
    private String properties;

    public Item() {
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
