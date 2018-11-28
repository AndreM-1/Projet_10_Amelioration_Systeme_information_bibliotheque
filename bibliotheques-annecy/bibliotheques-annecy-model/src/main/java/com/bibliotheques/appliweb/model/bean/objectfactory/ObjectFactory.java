
package com.bibliotheques.appliweb.model.bean.objectfactory;

import javax.xml.bind.annotation.XmlRegistry;

import com.bibliotheques.appliweb.model.bean.edition.Auteur;
import com.bibliotheques.appliweb.model.bean.edition.Bibliotheque;
import com.bibliotheques.appliweb.model.bean.edition.Editeur;
import com.bibliotheques.appliweb.model.bean.edition.Edition;
import com.bibliotheques.appliweb.model.bean.edition.Emprunt;
import com.bibliotheques.appliweb.model.bean.edition.Exemplaire;
import com.bibliotheques.appliweb.model.bean.edition.Genre;
import com.bibliotheques.appliweb.model.bean.edition.Ouvrage;
import com.bibliotheques.appliweb.model.bean.edition.Photo;
import com.bibliotheques.appliweb.model.bean.edition.StatutEmprunt;
import com.bibliotheques.appliweb.model.bean.utilisateur.Utilisateur;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.beans package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.beans
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Edition }
     * 
     */
    public Edition createEdition() {
        return new Edition();
    }

    /**
     * Create an instance of {@link Editeur }
     * 
     */
    public Editeur createEditeur() {
        return new Editeur();
    }

    /**
     * Create an instance of {@link Photo }
     * 
     */
    public Photo createPhoto() {
        return new Photo();
    }

    /**
     * Create an instance of {@link Ouvrage }
     * 
     */
    public Ouvrage createOuvrage() {
        return new Ouvrage();
    }

    /**
     * Create an instance of {@link Auteur }
     * 
     */
    public Auteur createAuteur() {
        return new Auteur();
    }

    /**
     * Create an instance of {@link Genre }
     * 
     */
    public Genre createGenre() {
        return new Genre();
    }

    /**
     * Create an instance of {@link Utilisateur }
     * 
     */
    public Utilisateur createUtilisateur() {
        return new Utilisateur();
    }

    /**
     * Create an instance of {@link Bibliotheque }
     * 
     */
    public Bibliotheque createBibliotheque() {
        return new Bibliotheque();
    }

    /**
     * Create an instance of {@link Exemplaire }
     * 
     */
    public Exemplaire createExemplaire() {
        return new Exemplaire();
    }

    /**
     * Create an instance of {@link StatutEmprunt }
     * 
     */
    public StatutEmprunt createStatutEmprunt() {
        return new StatutEmprunt();
    }

    /**
     * Create an instance of {@link Emprunt }
     * 
     */
    public Emprunt createEmprunt() {
        return new Emprunt();
    }

}
