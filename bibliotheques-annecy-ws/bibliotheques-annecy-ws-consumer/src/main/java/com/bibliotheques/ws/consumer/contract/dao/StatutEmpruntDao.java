package com.bibliotheques.ws.consumer.contract.dao;

import com.bibliotheques.ws.model.bean.edition.StatutEmprunt;

/**
 * Interface StatutEmpruntDao
 * @author André Monnier
 *
 */
public interface StatutEmpruntDao {

	/**
	 * Méthode permettant de renvoyer le statut d'un emprunt en fonction de son identifiant.
	 * @param statutEmpruntId : L'identifiant du statut de l'emprunt
	 * @return Un objet de type {@link StatutEmprunt}
	 */
	StatutEmprunt getStatutEmprunt(int statutEmpruntId);

}