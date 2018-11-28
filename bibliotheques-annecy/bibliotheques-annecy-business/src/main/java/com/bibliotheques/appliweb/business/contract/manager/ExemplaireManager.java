package com.bibliotheques.appliweb.business.contract.manager;

import java.util.List;

import com.bibliotheques.appliweb.model.bean.edition.Exemplaire;
import com.bibliotheques.appliweb.model.exception.GetListExemplaireFault_Exception;

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
	 * @throws GetListExemplaireFault_Exception
	 */
	List<Exemplaire> getListExemplaire(int editionId) throws GetListExemplaireFault_Exception;

}