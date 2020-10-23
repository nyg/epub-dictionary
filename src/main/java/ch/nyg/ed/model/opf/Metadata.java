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
    private final Identifier identifier = new Identifier();

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", required = true)
    private final Title title = new Title();

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", required = true)
    private final Language language = new Language();

    /* Optional elements */

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/")
    private Type type;

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/")
    private String creator;

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/")
    private String date;

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/")
    private String publisher;

    @XmlElement(name = "meta")
    private final List<Meta> metaList = new ArrayList<>();

    public Identifier getIdentifier() {
        return identifier;
    }

    public Title getTitle() {
        return title;
    }

    public Language getLanguage() {
        return language;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Meta> getMetaList() {
        return metaList;
    }
}
