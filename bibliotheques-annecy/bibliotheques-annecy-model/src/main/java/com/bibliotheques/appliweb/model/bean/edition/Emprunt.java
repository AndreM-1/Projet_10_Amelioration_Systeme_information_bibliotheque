
package com.bibliotheques.appliweb.model.bean.edition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.bibliotheques.appliweb.model.bean.utilisateur.Utilisateur;


/**
 * <p>Classe Java pour Emprunt complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Emprunt"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dateDeDebut" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dateDeFin" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="prolongation" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="dateDeProlongation" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="dureeProlongation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateDeRetourEffective" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="utilisateur" type="{http://www.example.org/beans}Utilisateur"/&gt;
 *         &lt;element name="statutEmprunt" type="{http://www.example.org/beans}StatutEmprunt"/&gt;
 *         &lt;element name="exemplaire" type="{http://www.example.org/beans}Exemplaire"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Emprunt", propOrder = {
    "id",
    "dateDeDebut",
    "dateDeFin",
    "prolongation",
    "dateDeProlongation",
    "dureeProlongation",
    "dateDeRetourEffective",
    "utilisateur",
    "statutEmprunt",
    "exemplaire"
})
public class Emprunt {

    protected int id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateDeDebut;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateDeFin;
    protected boolean prolongation;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateDeProlongation;
    protected String dureeProlongation;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateDeRetourEffective;
    @XmlElement(required = true)
    protected Utilisateur utilisateur;
    @XmlElement(required = true)
    protected StatutEmprunt statutEmprunt;
    @XmlElement(required = true)
    protected Exemplaire exemplaire;

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
     * Obtient la valeur de la propriété dateDeDebut.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateDeDebut() {
        return dateDeDebut;
    }

    /**
     * Définit la valeur de la propriété dateDeDebut.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateDeDebut(XMLGregorianCalendar value) {
        this.dateDeDebut = value;
    }

    /**
     * Obtient la valeur de la propriété dateDeFin.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateDeFin() {
        return dateDeFin;
    }

    /**
     * Définit la valeur de la propriété dateDeFin.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateDeFin(XMLGregorianCalendar value) {
        this.dateDeFin = value;
    }

    /**
     * Obtient la valeur de la propriété prolongation.
     * 
     */
    public boolean isProlongation() {
        return prolongation;
    }

    /**
     * Définit la valeur de la propriété prolongation.
     * 
     */
    public void setProlongation(boolean value) {
        this.prolongation = value;
    }

    /**
     * Obtient la valeur de la propriété dateDeProlongation.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateDeProlongation() {
        return dateDeProlongation;
    }

    /**
     * Définit la valeur de la propriété dateDeProlongation.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateDeProlongation(XMLGregorianCalendar value) {
        this.dateDeProlongation = value;
    }

    /**
     * Obtient la valeur de la propriété dureeProlongation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDureeProlongation() {
        return dureeProlongation;
    }

    /**
     * Définit la valeur de la propriété dureeProlongation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDureeProlongation(String value) {
        this.dureeProlongation = value;
    }

    /**
     * Obtient la valeur de la propriété dateDeRetourEffective.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateDeRetourEffective() {
        return dateDeRetourEffective;
    }

    /**
     * Définit la valeur de la propriété dateDeRetourEffective.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateDeRetourEffective(XMLGregorianCalendar value) {
        this.dateDeRetourEffective = value;
    }

    /**
     * Obtient la valeur de la propriété utilisateur.
     * 
     * @return
     *     possible object is
     *     {@link Utilisateur }
     *     
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Définit la valeur de la propriété utilisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link Utilisateur }
     *     
     */
    public void setUtilisateur(Utilisateur value) {
        this.utilisateur = value;
    }

    /**
     * Obtient la valeur de la propriété statutEmprunt.
     * 
     * @return
     *     possible object is
     *     {@link StatutEmprunt }
     *     
     */
    public StatutEmprunt getStatutEmprunt() {
        return statutEmprunt;
    }

    /**
     * Définit la valeur de la propriété statutEmprunt.
     * 
     * @param value
     *     allowed object is
     *     {@link StatutEmprunt }
     *     
     */
    public void setStatutEmprunt(StatutEmprunt value) {
        this.statutEmprunt = value;
    }

    /**
     * Obtient la valeur de la propriété exemplaire.
     * 
     * @return
     *     possible object is
     *     {@link Exemplaire }
     *     
     */
    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    /**
     * Définit la valeur de la propriété exemplaire.
     * 
     * @param value
     *     allowed object is
     *     {@link Exemplaire }
     *     
     */
    public void setExemplaire(Exemplaire value) {
        this.exemplaire = value;
    }

}
