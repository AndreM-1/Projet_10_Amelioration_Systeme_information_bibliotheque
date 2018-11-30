
package com.bibliotheques.ws.model.bean.edition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;


/**
 * <p>Classe Java pour Reservation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Reservation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dateReservation" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="utilisateur" type="{http://www.example.org/beans}Utilisateur"/&gt;
 *         &lt;element name="exemplaire" type="{http://www.example.org/beans}Exemplaire"/&gt;
 *         &lt;element name="prioriteReservation" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dateReceptionMail" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reservation", propOrder = {
    "id",
    "dateReservation",
    "utilisateur",
    "exemplaire",
    "prioriteReservation",
    "dateReceptionMail"
})
public class Reservation {

    protected int id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateReservation;
    @XmlElement(required = true)
    protected Utilisateur utilisateur;
    @XmlElement(required = true)
    protected Exemplaire exemplaire;
    protected int prioriteReservation;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateReceptionMail;

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
     * Obtient la valeur de la propriété dateReservation.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateReservation() {
        return dateReservation;
    }

    /**
     * Définit la valeur de la propriété dateReservation.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateReservation(XMLGregorianCalendar value) {
        this.dateReservation = value;
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

    /**
     * Obtient la valeur de la propriété prioriteReservation.
     * 
     */
    public int getPrioriteReservation() {
        return prioriteReservation;
    }

    /**
     * Définit la valeur de la propriété prioriteReservation.
     * 
     */
    public void setPrioriteReservation(int value) {
        this.prioriteReservation = value;
    }

    /**
     * Obtient la valeur de la propriété dateReceptionMail.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateReceptionMail() {
        return dateReceptionMail;
    }

    /**
     * Définit la valeur de la propriété dateReceptionMail.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateReceptionMail(XMLGregorianCalendar value) {
        this.dateReceptionMail = value;
    }

}
