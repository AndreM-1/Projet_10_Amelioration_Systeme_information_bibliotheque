package com.bibliotheques.ws.consumer.contract.dao;

import java.util.Date;
import java.util.List;

import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

/**
 * Interface EmpruntDao
 * @author André Monnier
 *
 */
public interface EmpruntDao {

	/**
	 * Méthode permettant de renvoyer la liste des emprunts d'un utilisateur.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @return List
	 * @throws NotFoundException
	 */
	List<Emprunt> getListEmprunt(int utilisateurId) throws NotFoundException;

	/**
	 * Méthode permettant d'enregistrer en BDD l'emprunt effectué par un utilisateur.
	 * @param dateDeDebut : La date de début de l'emprunt
	 * @param dateDeFin : La date de fin de l'emprunt
	 * @param prolongation : Un booléen indiquant si l'utilisateur a prolongé l'emprunt
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param statutEmpruntId : L'identifiant du statut de l'emprunt
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws FunctionalException
	 */
	void insertEmprunt(Date dateDeDebut, Date dateDeFin, boolean prolongation, int utilisateurId, int statutEmpruntId, int bibliothequeId, int editionId)
			throws FunctionalException;

	/**
	 * Méthode permettant de renvoyer un emprunt d'une édition effectué par un utilisateur.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param editionId : L'identifiant de l'édition
	 * @return : Un objet de type {@link Emprunt}
	 * @throws NotFoundException
	 */
	Emprunt getEmprunt(int utilisateurId, int editionId) throws NotFoundException;

	/**
	 * Méthode permettant de mettre à jour les informations relatives à un emprunt lorsque celui-ci a fait l'objet d'une prolongation.
	 * @param dateDeFin : La date de fin de l'emprunt recalculée suite à la prolongation
	 * @param prolongation : Un booléen indiquant si l'utilisateur a prolongé l'emprunt
	 * @param dateDeProlongation : La date à laquelle a été effectuée la prolongation
	 * @param dureeProlongation : La durée de la prolongation
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param statutEmpruntId : L'identifiant du statut de l'emprunt
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws TechnicalException
	 */
	void updateEmprunt(Date dateDeFin, boolean prolongation, Date dateDeProlongation, String dureeProlongation, int utilisateurId, int statutEmpruntId, int bibliothequeId,
			int editionId) throws TechnicalException;

	/**
	 * Méthode permettant de renvoyer la liste de l'ensemble des emprunts en retard.
	 * @return List
	 * @throws NotFoundException
	 */
	List<Emprunt> getListEmpruntEnRetard() throws NotFoundException;

	/**
	 * Méthode permettant de renvoyer la liste de l'ensemble des emprunts en cours ou non rendu à temps avant update quotidien.
	 * @return List
	 * @throws NotFoundException
	 */
	List<Emprunt>  getListEmpruntAvtUpd() throws NotFoundException;

	/**
	 * Méthode permettant de mettre à jour le champ prolongation de la table Emprunt.
	 * @param prolongation : Un booléen indiquant si l'emprunt peut être prolongé
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws TechnicalException
	 */
	void updateEmprunt(boolean prolongation, int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException;

	/**
	 * Méthode permettant de mettre à jour le champ statutEmpruntId de la table Emprunt.
	 * @param statutEmpruntId : L'identifiant du statut de l'emprunt
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws TechnicalException
	 */
	void updateEmprunt(int statutEmpruntId, int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException;

}