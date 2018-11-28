package com.bibliotheques.ws.business.contract.manager;

import javax.xml.datatype.XMLGregorianCalendar;

import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;

/**
 * Interface UtilisateurManager
 * @author André Monnier
 *
 */
public interface UtilisateurManager {

	/**
	 * Méthode permettant de renvoyer un utilisateur en fonction de son adresse mail et de son mot de passe.
	 * @param adresseMail : L'adresse mail de l'utilisateur
	 * @param motDePasse : Le mot de passe de l'utilisateur
	 * @return Un objet de type {@link Utilisateur}
	 * @throws NotFoundException
	 */
	Utilisateur getUtilisateur(String adresseMail, String motDePasse) throws NotFoundException;

	/**
	 * Méthode permettant d'enregistrer un utilisateur en base de données.
	 * @param civilite : La civilité de l'utilisateur
	 * @param nom : Le nom de l'utilisateur
	 * @param prenom : Le prénom de l'utilisateur
	 * @param pseudo : Le pseudo de l'utilisateur
	 * @param adresseMail : L'adresse Mail de l'utilisateur
	 * @param motDePasse : Le mot de passe de l'utilisateur
	 * @param confirmationMotDePasse : La confirmation du mot de passe
	 * @param conditionsUtilisation : Variable de type booléenne
	 * @throws FunctionalException
	 */
	void insertUtilisateur(String civilite, String nom, String prenom, String pseudo, String adresseMail, String motDePasse, String confirmationMotDePasse,
			boolean conditionsUtilisation) throws FunctionalException;

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
	 * @throws FunctionalException
	 */
	void updateCoordUtilisateur(int id, String civilite, String nom, String prenom, String pseudo, String adresseMail,
			String telephone, XMLGregorianCalendar dateNaissance, String adresse, String codePostal, String ville, String pays) throws FunctionalException;

	/**
	 * Méthode permettant de mettre à jour le mot de passe de l'utilisateur.
	 * A noter que le mot de passe est crypté en base de données.
	 * @param id : L'id de l'utilisateur
	 * @param ancienMdp : Le mot de passe actuel de l'utilisateur
	 * @param nouveauMdp : Le nouveau mot de passe de l'utilisateur
	 * @param confirmationNouveauMdp : La confirmation du mot de passe
	 * @throws FunctionalException
	 * @throws NotFoundException
	 */
	void updateMdpUtilisateur(int id, String ancienMdp, String nouveauMdp, String confirmationNouveauMdp) throws FunctionalException,NotFoundException;

}