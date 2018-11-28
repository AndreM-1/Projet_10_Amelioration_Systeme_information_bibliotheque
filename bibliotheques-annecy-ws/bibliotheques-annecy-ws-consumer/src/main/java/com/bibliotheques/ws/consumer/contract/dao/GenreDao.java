package com.bibliotheques.ws.consumer.contract.dao;

import com.bibliotheques.ws.model.bean.edition.Genre;

/**
 * Interface GenreDao
 * @author André Monnier
 *
 */
public interface GenreDao {

	/**
	 * Méthode permettant de renvoyer un genre en fonction de son identifiant.
	 * @param genreId : L'identifiant du genre
	 * @return Un objet de type {@link Genre}
	 */
	Genre getGenre(int genreId);

}