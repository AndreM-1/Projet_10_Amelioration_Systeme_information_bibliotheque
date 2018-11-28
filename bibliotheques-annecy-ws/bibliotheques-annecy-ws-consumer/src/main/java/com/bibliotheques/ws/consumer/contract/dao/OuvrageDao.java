package com.bibliotheques.ws.consumer.contract.dao;

import com.bibliotheques.ws.model.bean.edition.Ouvrage;

/**
 * Interface OuvrageDao 
 * @author André Monnier
 *
 */
public interface OuvrageDao {

	/**
	 * Méthode permettant de renvoyer un ouvrage en fonction de son identifiant.
	 * @param ouvrageId : L'identifiant de l'ouvrage
	 * @return Un objet de type {@link Ouvrage}
	 */
	Ouvrage getOuvrage(int ouvrageId);
}