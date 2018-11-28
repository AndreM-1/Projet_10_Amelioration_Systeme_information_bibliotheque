
package com.bibliotheques.appliweb.consumer.generated.editionservice;

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
 *         &lt;element name="titre" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nomAuteur" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nomEditeur" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="anneeParution" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="genre" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "titre",
    "nomAuteur",
    "nomEditeur",
    "anneeParution",
    "genre"
})
@XmlRootElement(name = "rechercheAvanceeEdition")
public class RechercheAvanceeEdition {

    @XmlElement(required = true)
    protected String titre;
    @XmlElement(required = true)
    protected String nomAuteur;
    @XmlElement(required = true)
    protected String nomEditeur;
    @XmlElement(required = true)
    protected String anneeParution;
    @XmlElement(required = true)
    protected String genre;

    /**
     * Obtient la valeur de la propriété titre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit la valeur de la propriété titre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitre(String value) {
        this.titre = value;
    }

    /**
     * Obtient la valeur de la propriété nomAuteur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomAuteur() {
        return nomAuteur;
    }

    /**
     * Définit la valeur de la propriété nomAuteur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomAuteur(String value) {
        this.nomAuteur = value;
    }

    /**
     * Obtient la valeur de la propriété nomEditeur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomEditeur() {
        return nomEditeur;
    }

    /**
     * Définit la valeur de la propriété nomEditeur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomEditeur(String value) {
        this.nomEditeur = value;
    }

    /**
     * Obtient la valeur de la propriété anneeParution.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnneeParution() {
        return anneeParution;
    }

    /**
     * Définit la valeur de la propriété anneeParution.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnneeParution(String value) {
        this.anneeParution = value;
    }

    /**
     * Obtient la valeur de la propriété genre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Définit la valeur de la propriété genre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenre(String value) {
        this.genre = value;
    }

}
