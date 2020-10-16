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
    private String uniqueIdentifier;

    @XmlAttribute(required = true)
    private float version;

    /* Required elements */

    @XmlElement(required = true)
    private Metadata metadata;

    @XmlElement(required = true)
    private Manifest manifest;

    @XmlElement(required = true)
    private Spine spine;

    public Package() {
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Manifest getManifest() {
        return manifest;
    }

    public void setManifest(Manifest manifest) {
        this.manifest = manifest;
    }

    public Spine getSpine() {
        return spine;
    }

    public void setSpine(Spine spine) {
        this.spine = spine;
    }
}
