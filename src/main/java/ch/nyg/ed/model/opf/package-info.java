@XmlSchema(
        elementFormDefault = XmlNsForm.QUALIFIED,
        namespace = "http://www.idpf.org/2007/opf",
        xmlns = {
                @XmlNs(prefix = "", namespaceURI = "http://www.idpf.org/2007/opf"),
                @XmlNs(prefix = "dc", namespaceURI = "http://purl.org/dc/elements/1.1/")
})

package ch.nyg.ed.model.opf;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
