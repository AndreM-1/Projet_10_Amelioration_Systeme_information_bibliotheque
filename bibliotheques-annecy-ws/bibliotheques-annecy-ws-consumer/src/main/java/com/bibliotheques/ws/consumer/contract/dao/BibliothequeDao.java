package com.bibliotheques.ws.consumer.contract.dao;

import com.bibliotheques.ws.model.bean.edition.Bibliotheque;

/**
 * Interface BibliothequeDao
 * @author André Monnier
 *
 */
public interface BibliothequeDao {

	/**
	 * Méthode permettant de renvoyer un objet de type Bibliotheque en fonction de son identifiant.
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @return Un objet de type {@link Bibliotheque}
	 */
	Bibliotheque getBibliotheque(int bibliothequeId);

}