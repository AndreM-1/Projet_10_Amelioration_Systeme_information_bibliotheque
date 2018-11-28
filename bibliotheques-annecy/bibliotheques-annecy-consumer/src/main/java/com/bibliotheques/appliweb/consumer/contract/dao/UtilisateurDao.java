package com.bibliotheques.appliweb.consumer.contract.dao;

import javax.xml.datatype.XMLGregorianCalendar;

import com.bibliotheques.appliweb.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.appliweb.model.exception.AuthentifierUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.CreerCompteUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.UpdateCoordUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.UpdateMdpUtilisateurFault_Exception;

/**
 * Interface UtilisateurDao
 * @author André Monnier
 *
 */
public interface UtilisateurDao {

	/**
	 * Méthode permettant d'authentifier un utilisateur.
	 * @param adresseMail : L'adresse mail de l'utilisateur
	 * @param motDePasse : Le mot de passe de l'utilisateur
	 * @return Un objet de type {@link Utilisateur}
	 * @throws AuthentifierUtilisateurFault_Exception
	 */
	Utilisateur authentifierUtilisateur(String adresseMail, String motDePasse) throws AuthentifierUtilisateurFault_Exception;

	/**
	 * Méthode permettant de créer un compte utilisateur.
	 * @param civilite : La civilité de l'utilisateur
	 * @param nom : Le nom de l'utilisateur
	 * @param prenom : Le prénom de l'utilisateur
	 * @param pseudo : Le pseudo de l'utilisateur
	 * @param adresseMail : L'adresse Mail de l'utilisateur
	 * @param motDePasse : Le mot de passe de l'utilisateur
	 * @param confirmationMotDePasse : La confirmation du mot de passe de l'utilisateur
	 * @param conditionsUtilisation : Variable de type booléenne
	 * @return Un objet de type {@link Utilisateur}
	 * @throws CreerCompteUtilisateurFault_Exception
	 */
	Utilisateur creerCompteUtilisateur(String civilite, String nom, String prenom, String pseudo, String adresseMail, String motDePasse,
			String confirmationMotDePasse, boolean conditionsUtilisation) throws CreerCompteUtilisateurFault_Exception;

	/**
	 * Méthode permettant de mettre à jour les informations relatives à l'utilisateur.
	 * @param id : L'id de l'utilisateur
	 * @param civilite : La civilité de l'utilisateur
	 * @param nom : Le nom de l'utilisateur
	 * @param prenom : Le prénom de l'utilisateur
	 * @param pseudo : Le pseudo de l'utilisateur
	 * @param adresseMail : L'adresse Mail de l'utilisateur
	 * @param telephone : Le téléphone de l'utilisateur
	 * @param dateNaissance : La date de naissance de l'utilisateur
	 * @param adresse : L'adresse postale de l'utilisateur
	 * @param codePostal : Le code postal de l'utilisateur
	 * @param ville : La ville de l'utilisateur
	 * @param pays : Le pays de l'utilisateur
	 * @throws UpdateCoordUtilisateurFault_Exception
	 */
	void updateCoordUtilisateur(int id, String civilite, String nom, String prenom, String pseudo, String adresseMail,
			String telephone, XMLGregorianCalendar dateNaissance, String adresse, String codePostal, String ville, String pays)
			throws UpdateCoordUtilisateurFault_Exception;

	/**
	 * Méthode permettant de mettre à jour le mot de passe de l'utilisateur.
	 * @param id : L'id de l'utilisateur
	 * @param ancienMotDePasse : Le mot de passe actuel de l'utilisateur
	 * @param nouveauMotDePasse : Le nouveau mot de passe de l'utilisateur
	 * @param confirmationNouveauMotDePasse : La confirmation du mot de passe
	 * @throws UpdateMdpUtilisateurFault_Exception
	 */
	void updateMdpUtilisateur(int id, String ancienMotDePasse, String nouveauMotDePasse, String confirmationNouveauMotDePasse)
			throws UpdateMdpUtilisateurFault_Exception;

}