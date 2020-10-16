package ch.nyg.ed.model.container;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/*<?xml version="1.0"?>
<container version="1.0" xmlns="urn:oasis:names:tc:opendocument:xmlns:container">
    <rootfiles>
        <rootfile full-path="EPUB/My_Crazy_Life.opf"
            media-type="application/oebps-package+xml" />
    </rootfiles>
</container>*/
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
