package com.bibliotheques.ws.consumer.contract.dao;

import com.bibliotheques.ws.model.bean.edition.Editeur;

/**
 * Interface EditeurDao
 * @author André Monnier
 *
 */
public interface EditeurDao {

	/**
	 * Méthode permettant de renvoyer un éditeur en fonction de son identifiant.
	 * @param editeurId : L'identifiant de l'éditeur
	 * @return Un objet de type {@link Editeur}
	 */
	Editeur getEditeur(int editeurId);

}