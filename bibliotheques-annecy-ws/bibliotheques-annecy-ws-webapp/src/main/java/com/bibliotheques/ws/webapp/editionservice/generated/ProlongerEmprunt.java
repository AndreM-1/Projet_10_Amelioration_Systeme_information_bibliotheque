
package com.bibliotheques.ws.webapp.editionservice.generated;

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
 *         &lt;element name="utilisateurId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="bibliothequeId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "utilisateurId",
    "bibliothequeId",
    "editionId"
})
@XmlRootElement(name = "prolongerEmprunt")
public class ProlongerEmprunt {

    protected int utilisateurId;
    protected int bibliothequeId;
    protected int editionId;

    /**
     * Obtient la valeur de la propriété utilisateurId.
     * 
     */
    public int getUtilisateurId() {
        return utilisateurId;
    }

    /**
     * Définit la valeur de la propriété utilisateurId.
     * 
     */
    public void setUtilisateurId(int value) {
        this.utilisateurId = value;
    }

    /**
     * Obtient la valeur de la propriété bibliothequeId.
     * 
     */
    public int getBibliothequeId() {
        return bibliothequeId;
    }

    /**
     * Définit la valeur de la propriété bibliothequeId.
     * 
     */
    public void setBibliothequeId(int value) {
        this.bibliothequeId = value;
    }

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
