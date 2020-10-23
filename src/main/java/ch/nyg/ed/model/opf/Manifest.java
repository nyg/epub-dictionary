package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Manifest {

    @XmlElement(name = "item", required = true)
    private final List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }
}
