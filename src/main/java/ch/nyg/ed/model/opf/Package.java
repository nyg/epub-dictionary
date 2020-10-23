package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://www.idpf.org/2007/opf")
@XmlAccessorType(XmlAccessType.FIELD)
public class Package {

    /* Required attributes */

    @XmlAttribute(name = "unique-identifier", required = true)
    private final String uniqueIdentifier = "uid";

    @XmlAttribute(required = true)
    private float version;

    /* Required elements */

    @XmlElement(required = true)
    private final Metadata metadata = new Metadata();

    @XmlElement(required = true)
    private final Manifest manifest = new Manifest();

    @XmlElement(required = true)
    private final Spine spine = new Spine();

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Manifest getManifest() {
        return manifest;
    }

    public Spine getSpine() {
        return spine;
    }
}
