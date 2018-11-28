package com.bibliotheques.ws.business.contract.manager;

import java.util.List;

import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

/**
 * Interface EmpruntManager
 * @author André Monnier
 *
 */
public interface EmpruntManager {

	/**
	 * Méthode permettant de renvoyer la liste des emprunts d'un utilisateur.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @return List
	 * @throws NotFoundException
	 */
	List<Emprunt> getListEmprunt(int utilisateurId) throws NotFoundException;

	/**
	 * Méthode permettant à un utilisateur d'emprunter une édition dans une bibliothèque.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws FunctionalException
	 * @throws TechnicalException
	 */
	void emprunterEdition(int utilisateurId, int bibliothequeId, int editionId) throws FunctionalException, TechnicalException;

	/**
	 * Méthode permettant à un utilisateur de prolonger un emprunt.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws TechnicalException
	 */
	void prolongerEmprunt(int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException;

	/**
	 * Méthode permettant de renvoyer la liste de l'ensemble des emprunts en retard.
	 * @return List
	 * @throws NotFoundException
	 * @throws TechnicalException
	 */
	List<Emprunt> getListEmpruntEnRetard() throws NotFoundException,TechnicalException;
}