
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
 *         &lt;element name="editionId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "editionId"
})
@XmlRootElement(name = "getListExemplaire")
public class GetListExemplaire {

    protected int editionId;

    /**
     * Obtient la valeur de la propriété editionId.
     * 
     */
    public int getEditionId() {
        return editionId;
    }

    /**
     * Définit la valeur de la propriété editionId.
     * 
     */
    public void setEditionId(int value) {
        this.editionId = value;
    }

}
