package com.bibliotheques.ws.consumer.contract;

import com.bibliotheques.ws.consumer.contract.dao.EditeurDao;
import com.bibliotheques.ws.consumer.contract.dao.EditionDao;
import com.bibliotheques.ws.consumer.contract.dao.EmpruntDao;
import com.bibliotheques.ws.consumer.contract.dao.ExemplaireDao;
import com.bibliotheques.ws.consumer.contract.dao.GenreDao;
import com.bibliotheques.ws.consumer.contract.dao.OuvrageDao;
import com.bibliotheques.ws.consumer.contract.dao.PhotoDao;
import com.bibliotheques.ws.consumer.contract.dao.UtilisateurDao;


/**
 * Factory des DAO
 * @author André Monnier
 */
public interface DaoFactory {

	void setEditionDao(EditionDao editionDao);

	EditionDao getEditionDao();

	void setPhotoDao(PhotoDao photoDao);

	PhotoDao getPhotoDao();

	void setOuvrageDao(OuvrageDao ouvrageDao);

	OuvrageDao getOuvrageDao();

	void setGenreDao(GenreDao genreDao);

	GenreDao getGenreDao();

	void setEditeurDao(EditeurDao editeurDao);

	EditeurDao getEditeurDao();

	void setUtilisateurDao(UtilisateurDao utilisateurDao);

	UtilisateurDao getUtilisateurDao();

	void setExemplaireDao(ExemplaireDao exemplaireDao);

	ExemplaireDao getExemplaireDao();

	void setEmpruntDao(EmpruntDao empruntDao);

	EmpruntDao getEmpruntDao();
}