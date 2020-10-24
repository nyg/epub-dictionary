package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum MediaType {

    @XmlEnumValue("application/xhtml+xml")
    XHTML,

    @XmlEnumValue("image/jpeg")
    JPEG;
}
