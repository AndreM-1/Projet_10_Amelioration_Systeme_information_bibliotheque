package com.bibliotheques.appliweb.business.contract.manager;

import java.util.List;

import com.bibliotheques.appliweb.model.bean.edition.Emprunt;
import com.bibliotheques.appliweb.model.exception.EmprunterEditionFault_Exception;
import com.bibliotheques.appliweb.model.exception.GestionPretFault_Exception;
import com.bibliotheques.appliweb.model.exception.ProlongerEmpruntFault_Exception;

/**
 * Interface EmpruntManager
 * @author André Monnier
 *
 */
public interface EmpruntManager {

	/**
	 * Méthode permettant de visualiser les prêts effectués par un utilisateur
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @return List
	 * @throws GestionPretFault_Exception
	 */
	List<Emprunt> gestionPret(int utilisateurId) throws GestionPretFault_Exception;

	/**
	 * Méthode permettant à un utilisateur d'emprunter une édition dans une bibliothèque.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws EmprunterEditionFault_Exception
	 */
	void emprunterEdition(int utilisateurId, int bibliothequeId, int editionId) throws EmprunterEditionFault_Exception;

	/**
	 * Méthode permettant à un utilisateur de prolonger un emprunt.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws ProlongerEmpruntFault_Exception
	 */
	void prolongerEmprunt(int utilisateurId, int bibliothequeId, int editionId) throws ProlongerEmpruntFault_Exception;

}