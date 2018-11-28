package com.bibliotheques.ws.consumer.contract.dao;

import com.bibliotheques.ws.model.bean.edition.Photo;

/**
 * Interface PhotoDao
 * @author André Monnier
 *
 */
public interface PhotoDao {

	/**
	 * Méthode permettant de renvoyer un objet de type photo en fonction de son identifiant.
	 * @param photoId : L'identifiant de la photo
	 * @return Un objet de type {@link Photo}
	 */
	Photo getPhoto(int photoId);

}