package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
public class Meta {

    @XmlAttribute
    private QName property;

    @XmlValue
    private String value;

    public Meta() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QName getProperty() {
        return property;
    }

    public void setProperty(QName property) {
        this.property = property;
    }
}
