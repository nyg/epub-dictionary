package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Itemref {

    /* Required attributes */

    @XmlAttribute(required = true)
    private String idref;

    public Itemref() {
    }

    public String getIdref() {
        return idref;
    }

    public void setIdref(String idref) {
        this.idref = idref;
    }
}
