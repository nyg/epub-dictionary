package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Spine {

    @XmlElement(name = "itemref", required = true)
    private final List<ItemRef> itemRefs = new ArrayList<>();

    public List<ItemRef> getItemRefs() {
        return itemRefs;
    }
}
