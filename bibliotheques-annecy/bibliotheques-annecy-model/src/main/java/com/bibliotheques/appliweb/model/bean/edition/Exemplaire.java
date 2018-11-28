
package com.bibliotheques.appliweb.model.bean.edition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Exemplaire complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Exemplaire"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bibliotheque" type="{http://www.example.org/beans}Bibliotheque"/&gt;
 *         &lt;element name="edition" type="{http://www.example.org/beans}Edition"/&gt;
 *         &lt;element name="nbExemplaires" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Exemplaire", propOrder = {
    "bibliotheque",
    "edition",
    "nbExemplaires"
})
public class Exemplaire {

    @XmlElement(required = true)
    protected Bibliotheque bibliotheque;
    @XmlElement(required = true)
    protected Edition edition;
    protected int nbExemplaires;

    /**
     * Obtient la valeur de la propriété bibliotheque.
     * 
     * @return
     *     possible object is
     *     {@link Bibliotheque }
     *     
     */
    public Bibliotheque getBibliotheque() {
        return bibliotheque;
    }

    /**
     * Définit la valeur de la propriété bibliotheque.
     * 
     * @param value
     *     allowed object is
     *     {@link Bibliotheque }
     *     
     */
    public void setBibliotheque(Bibliotheque value) {
        this.bibliotheque = value;
    }

    /**
     * Obtient la valeur de la propriété edition.
     * 
     * @return
     *     possible object is
     *     {@link Edition }
     *     
     */
    public Edition getEdition() {
        return edition;
    }

    /**
     * Définit la valeur de la propriété edition.
     * 
     * @param value
     *     allowed object is
     *     {@link Edition }
     *     
     */
    public void setEdition(Edition value) {
        this.edition = value;
    }

    /**
     * Obtient la valeur de la propriété nbExemplaires.
     * 
     */
    public int getNbExemplaires() {
        return nbExemplaires;
    }

    /**
     * Définit la valeur de la propriété nbExemplaires.
     * 
     */
    public void setNbExemplaires(int value) {
        this.nbExemplaires = value;
    }

}
