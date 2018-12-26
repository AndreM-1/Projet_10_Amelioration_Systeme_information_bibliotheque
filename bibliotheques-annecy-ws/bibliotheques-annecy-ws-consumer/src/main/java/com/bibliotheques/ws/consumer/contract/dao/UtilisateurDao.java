package com.bibliotheques.ws.consumer.contract.dao;

import javax.xml.datatype.XMLGregorianCalendar;

import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

/**
 * Interface UtilisateurDao
 * @author André Monnier
 *
 */
public interface UtilisateurDao {

	/**
	 * Méthode permettant de renvoyer un utilisateur en fonction de son adresse mail.
	 * @param adresseMail : L'adresse mail de l'utilisateur
	 * @return Un objet de type {@link Utilisateur}
	 * @throws NotFoundException
	 */
	Utilisateur getUtilisateur(String adresseMail) throws NotFoundException;

	/**
	 * Méthode permettant d'enregistrer un utilisateur en base de données.
	 * @param utilisateur : L'utilisateur
	 * @throws FunctionalException
	 */
	void insertUtilisateur(Utilisateur utilisateur) throws FunctionalException;

	/**
	 * Méthode permettant de renvoyer un utilisateur en fonction de son identifiant.
	 * @param id : L'identifiant de l'utilisateur
	 * @return Un objet de type {@link Utilisateur}
	 * @throws NotFoundException
	 */
	Utilisateur getUtilisateur(int id) throws NotFoundException;

	/**
	 * Méthode permettant de mettre à jour les informations relatives à l'utilisateur.
	 * @param id : L'identifiant de l'utilisateur
	 * @param civilite : La civilité de l'utilisateur
	 * @param nom : Le nom de l'utilisateur
	 * @param prenom : Le prénom de l'utilisateur
	 * @param pseudo : Le pseudo de l'utilisateur
	 * @param adresseMail : L'adresse mail de l'utilisateur
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
	 * @param utilisateur : L'utilisateur
	 */
	void updateMdpUtilisateur(Utilisateur utilisateur);

	/**
	 * Méthode permettant de mettre à jour les paramètres de l'utilisateur. Pour l'instant, un seul paramètre est présent, celui permettant
	 * d'activer ou de désactiver le mail de rappel pour les prêts arrivant bientôt à expiration.
	 * @param id : L'identifiant de l'utilisateur
	 * @param mailRappelPret : Une variable de type booléenne permettant d'activer ou de désactiver le mail de rappel pour les prêts arrivant bientôt à expiration
	 * @throws TechnicalException
	 */
	void updateParametresUtilisateur(int id, boolean mailRappelPret) throws TechnicalException;
}