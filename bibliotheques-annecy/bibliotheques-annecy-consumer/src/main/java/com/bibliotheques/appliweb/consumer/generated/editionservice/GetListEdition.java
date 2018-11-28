
package com.bibliotheques.appliweb.consumer.generated.editionservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="nbEdition" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "nbEdition"
})
@XmlRootElement(name = "getListEdition")
public class GetListEdition {

    protected int nbEdition;

    /**
     * Obtient la valeur de la propriété nbEdition.
     * 
     */
    public int getNbEdition() {
        return nbEdition;
    }

    /**
     * Définit la valeur de la propriété nbEdition.
     * 
     */
    public void setNbEdition(int value) {
        this.nbEdition = value;
    }

}
