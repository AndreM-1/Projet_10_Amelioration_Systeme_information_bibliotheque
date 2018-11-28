package com.bibliotheques.appliweb.consumer.contract.dao;

import java.util.List;

import com.bibliotheques.appliweb.model.bean.edition.Exemplaire;
import com.bibliotheques.appliweb.model.exception.GetListExemplaireFault_Exception;

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
	 * @throws GetListExemplaireFault_Exception
	 */
	List<Exemplaire> getListExemplaire(int editionId) throws GetListExemplaireFault_Exception;

}