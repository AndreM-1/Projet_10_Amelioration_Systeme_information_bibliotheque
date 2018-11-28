package com.bibliotheques.ws.consumer.contract.dao;

import java.util.List;

import com.bibliotheques.ws.model.bean.edition.Exemplaire;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

/**
 * Interface ExemplaireDao
 * @author André Monnier
 *
 */
public interface ExemplaireDao {

	/**
	 * Méthode permettant de renvoyer la liste des exemplaires d'une édition donnée.
	 * @param editionId : L'identifiant de l'édition
	 * @return List
	 * @throws NotFoundException
	 */
	List<Exemplaire> getListExemplaire(int editionId) throws NotFoundException;

	/**
	 * Méthode permettant de renvoyer un objet de type {@link Exemplaire} en fonction 
	 * des identifiants de la bibliothèque et de l'edition.
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @return Un objet de type {@link Exemplaire}
	 * @throws NotFoundException
	 */
	Exemplaire getExemplaire(int bibliothequeId, int editionId) throws NotFoundException;

	/**
	 * Méthode permettant de mettre à jour le nombre d'exemplaires d'une édition dans une bibliothèque donnée.
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws TechnicalException
	 */
	void updateNbExemplaire(int bibliothequeId, int editionId) throws TechnicalException;

}