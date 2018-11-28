
package com.bibliotheques.appliweb.consumer.generated.utilisateurservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ancienMdp" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nouveauMdp" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="confirmationNouveauMdp" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "id",
    "ancienMdp",
    "nouveauMdp",
    "confirmationNouveauMdp"
})
@XmlRootElement(name = "updateMdpUtilisateur")
public class UpdateMdpUtilisateur {

    protected int id;
    @XmlElement(required = true)
    protected String ancienMdp;
    @XmlElement(required = true)
    protected String nouveauMdp;
    @XmlElement(required = true)
    protected String confirmationNouveauMdp;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété ancienMdp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAncienMdp() {
        return ancienMdp;
    }

    /**
     * Définit la valeur de la propriété ancienMdp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAncienMdp(String value) {
        this.ancienMdp = value;
    }

    /**
     * Obtient la valeur de la propriété nouveauMdp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNouveauMdp() {
        return nouveauMdp;
    }

    /**
     * Définit la valeur de la propriété nouveauMdp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNouveauMdp(String value) {
        this.nouveauMdp = value;
    }

    /**
     * Obtient la valeur de la propriété confirmationNouveauMdp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmationNouveauMdp() {
        return confirmationNouveauMdp;
    }

    /**
     * Définit la valeur de la propriété confirmationNouveauMdp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmationNouveauMdp(String value) {
        this.confirmationNouveauMdp = value;
    }

}
