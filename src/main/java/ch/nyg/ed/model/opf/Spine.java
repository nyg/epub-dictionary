package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Spine {

    @XmlElement(name = "itemref", required = true)
    private List<Itemref> itemrefList;

    public Spine() {
    }

    public List<Itemref> getItemrefList() {
        return itemrefList;
    }

    public void setItemrefList(List<Itemref> itemrefList) {
        this.itemrefList = itemrefList;
    }
}
