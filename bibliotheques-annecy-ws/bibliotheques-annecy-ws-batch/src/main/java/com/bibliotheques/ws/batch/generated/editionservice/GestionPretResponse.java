
package com.bibliotheques.ws.batch.generated.editionservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.bibliotheques.ws.model.bean.edition.Emprunt;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="emprunt" type="{http://www.example.org/beans}Emprunt" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "emprunt"
})
@XmlRootElement(name = "gestionPretResponse")
public class GestionPretResponse {

    @XmlElement(required = true)
    protected List<Emprunt> emprunt;

    /**
     * Gets the value of the emprunt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emprunt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmprunt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Emprunt }
     * 
     * 
     */
    public List<Emprunt> getEmprunt() {
        if (emprunt == null) {
            emprunt = new ArrayList<Emprunt>();
        }
        return this.emprunt;
    }

}
