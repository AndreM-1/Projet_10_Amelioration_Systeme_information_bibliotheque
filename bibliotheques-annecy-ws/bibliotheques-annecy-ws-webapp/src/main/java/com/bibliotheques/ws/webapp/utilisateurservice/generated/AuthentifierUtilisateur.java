
package com.bibliotheques.ws.webapp.utilisateurservice.generated;

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
 *         &lt;element name="adresseMail" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="motDePasse" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "adresseMail",
    "motDePasse"
})
@XmlRootElement(name = "authentifierUtilisateur")
public class AuthentifierUtilisateur {

    @XmlElement(required = true)
    protected String adresseMail;
    @XmlElement(required = true)
    protected String motDePasse;

    /**
     * Obtient la valeur de la propriété adresseMail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresseMail() {
        return adresseMail;
    }

    /**
     * Définit la valeur de la propriété adresseMail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresseMail(String value) {
        this.adresseMail = value;
    }

    /**
     * Obtient la valeur de la propriété motDePasse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Définit la valeur de la propriété motDePasse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotDePasse(String value) {
        this.motDePasse = value;
    }

}
