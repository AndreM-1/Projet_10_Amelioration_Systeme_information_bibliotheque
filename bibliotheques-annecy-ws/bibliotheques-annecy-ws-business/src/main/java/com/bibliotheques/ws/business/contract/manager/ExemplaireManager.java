package com.bibliotheques.ws.business.contract.manager;

import java.util.List;

import com.bibliotheques.ws.model.bean.edition.Exemplaire;
import com.bibliotheques.ws.model.exception.NotFoundException;

/**
 * Interface ExemplaireManager
 * @author André Monnier
 *
 */
public interface ExemplaireManager {

	/**
	 * Méthode permettant de renvoyer la liste des exemplaires d'une édition donnée.
	 * @param editionId : L'identifiant de l'édition
	 * @return List
	 * @throws NotFoundException
	 */
	List<Exemplaire> getListExemplaire(int editionId) throws NotFoundException;

}