
package com.bibliotheques.appliweb.model.bean.edition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour Edition complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Edition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="isbn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dateParution" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="nbPages" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ouvrage" type="{http://www.example.org/beans}Ouvrage"/&gt;
 *         &lt;element name="photo" type="{http://www.example.org/beans}Photo"/&gt;
 *         &lt;element name="editeur" type="{http://www.example.org/beans}Editeur"/&gt;
 *         &lt;element name="genre" type="{http://www.example.org/beans}Genre"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Edition", propOrder = {
    "id",
    "isbn",
    "dateParution",
    "nbPages",
    "format",
    "ouvrage",
    "photo",
    "editeur",
    "genre"
})
public class Edition {

    protected int id;
    @XmlElement(required = true)
    protected String isbn;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateParution;
    protected int nbPages;
    @XmlElement(required = true)
    protected String format;
    @XmlElement(required = true)
    protected Ouvrage ouvrage;
    @XmlElement(required = true)
    protected Photo photo;
    @XmlElement(required = true)
    protected Editeur editeur;
    @XmlElement(required = true)
    protected Genre genre;

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
     * Obtient la valeur de la propriété isbn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Définit la valeur de la propriété isbn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsbn(String value) {
        this.isbn = value;
    }

    /**
     * Obtient la valeur de la propriété dateParution.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateParution() {
        return dateParution;
    }

    /**
     * Définit la valeur de la propriété dateParution.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateParution(XMLGregorianCalendar value) {
        this.dateParution = value;
    }

    /**
     * Obtient la valeur de la propriété nbPages.
     * 
     */
    public int getNbPages() {
        return nbPages;
    }

    /**
     * Définit la valeur de la propriété nbPages.
     * 
     */
    public void setNbPages(int value) {
        this.nbPages = value;
    }

    /**
     * Obtient la valeur de la propriété format.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Définit la valeur de la propriété format.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Obtient la valeur de la propriété ouvrage.
     * 
     * @return
     *     possible object is
     *     {@link Ouvrage }
     *     
     */
    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    /**
     * Définit la valeur de la propriété ouvrage.
     * 
     * @param value
     *     allowed object is
     *     {@link Ouvrage }
     *     
     */
    public void setOuvrage(Ouvrage value) {
        this.ouvrage = value;
    }

    /**
     * Obtient la valeur de la propriété photo.
     * 
     * @return
     *     possible object is
     *     {@link Photo }
     *     
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Définit la valeur de la propriété photo.
     * 
     * @param value
     *     allowed object is
     *     {@link Photo }
     *     
     */
    public void setPhoto(Photo value) {
        this.photo = value;
    }

    /**
     * Obtient la valeur de la propriété editeur.
     * 
     * @return
     *     possible object is
     *     {@link Editeur }
     *     
     */
    public Editeur getEditeur() {
        return editeur;
    }

    /**
     * Définit la valeur de la propriété editeur.
     * 
     * @param value
     *     allowed object is
     *     {@link Editeur }
     *     
     */
    public void setEditeur(Editeur value) {
        this.editeur = value;
    }

    /**
     * Obtient la valeur de la propriété genre.
     * 
     * @return
     *     possible object is
     *     {@link Genre }
     *     
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Définit la valeur de la propriété genre.
     * 
     * @param value
     *     allowed object is
     *     {@link Genre }
     *     
     */
    public void setGenre(Genre value) {
        this.genre = value;
    }

}
