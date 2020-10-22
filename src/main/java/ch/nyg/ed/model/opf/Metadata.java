package ch.nyg.ed.model.opf;

import ch.nyg.ed.model.dc.Identifier;
import ch.nyg.ed.model.dc.Language;
import ch.nyg.ed.model.dc.Title;
import ch.nyg.ed.model.dc.Type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata {

    /* Requiered elements */

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", required = true)
    private Identifier identifier;

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", required = true)
    private Title title;

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", required = true)
    private Language language;

    /* Optional elements */

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/")
    private Type type;

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/")
    private String creator;

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/")
    private String date;

    @XmlElement(name = "meta")
    private final List<Meta> metaList = new ArrayList<>();

    public Metadata() {
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Meta> getMetaList() {
        return metaList;
    }
}
