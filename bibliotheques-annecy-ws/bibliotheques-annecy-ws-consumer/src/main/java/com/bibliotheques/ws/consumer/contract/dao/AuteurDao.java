package com.bibliotheques.ws.consumer.contract.dao;

import com.bibliotheques.ws.model.bean.edition.Auteur;

/**
 * Interface AuteurDao
 * @author André Monnier
 *
 */
public interface AuteurDao {

	/**
	 * Méthode permettant de renvoyer un auteur en fonction de son identifiant.
	 * @param auteurId : L'identifiant de l'auteur
	 * @return Un objet de type {@link Auteur}
	 */
	Auteur getAuteur(int auteurId);

}