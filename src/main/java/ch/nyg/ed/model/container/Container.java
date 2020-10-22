package ch.nyg.ed.model.container;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(namespace = "urn:oasis:names:tc:opendocument:xmlns:container")
@XmlAccessorType(XmlAccessType.FIELD)
public class Container {

    @XmlAttribute
    private float version;

    @XmlElementWrapper(name = "rootfiles")
    @XmlElement(name = "rootfile")
    private final List<RootFile> rootFiles;

    public Container() {
        rootFiles = new ArrayList<>();
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public void addRootFile(RootFile rootFile) {
        rootFiles.add(rootFile);
    }
}
