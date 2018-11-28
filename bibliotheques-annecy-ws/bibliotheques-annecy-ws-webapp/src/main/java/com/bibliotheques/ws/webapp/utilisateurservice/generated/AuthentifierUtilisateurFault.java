
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
 *         &lt;element name="faultMessageErreur" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "faultMessageErreur"
})
@XmlRootElement(name = "authentifierUtilisateurFault")
public class AuthentifierUtilisateurFault {

    @XmlElement(required = true)
    protected String faultMessageErreur;

    /**
     * Obtient la valeur de la propriété faultMessageErreur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultMessageErreur() {
        return faultMessageErreur;
    }

    /**
     * Définit la valeur de la propriété faultMessageErreur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultMessageErreur(String value) {
        this.faultMessageErreur = value;
    }

}
