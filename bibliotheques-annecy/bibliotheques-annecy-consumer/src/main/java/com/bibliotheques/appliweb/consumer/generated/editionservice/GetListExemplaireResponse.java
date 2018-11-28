
package com.bibliotheques.appliweb.consumer.generated.editionservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.bibliotheques.appliweb.model.bean.edition.Exemplaire;


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
 *         &lt;element name="exemplaire" type="{http://www.example.org/beans}Exemplaire" maxOccurs="unbounded"/&gt;
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
    "exemplaire"
})
@XmlRootElement(name = "getListExemplaireResponse")
public class GetListExemplaireResponse {

    @XmlElement(required = true)
    protected List<Exemplaire> exemplaire;

    /**
     * Gets the value of the exemplaire property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exemplaire property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExemplaire().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Exemplaire }
     * 
     * 
     */
    public List<Exemplaire> getExemplaire() {
        if (exemplaire == null) {
            exemplaire = new ArrayList<Exemplaire>();
        }
        return this.exemplaire;
    }

}
