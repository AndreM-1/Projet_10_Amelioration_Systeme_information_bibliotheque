package com.bibliotheques.appliweb.consumer.contract;

import com.bibliotheques.appliweb.consumer.contract.dao.EditionDao;
import com.bibliotheques.appliweb.consumer.contract.dao.EmpruntDao;
import com.bibliotheques.appliweb.consumer.contract.dao.ExemplaireDao;
import com.bibliotheques.appliweb.consumer.contract.dao.UtilisateurDao;

/**
 * Factory des DAO
 * @author André Monnier
 */
public interface DaoFactory {

	void setEditionDao(EditionDao editionDao);

	EditionDao getEditionDao();

	void setUtilisateurDao(UtilisateurDao utilisateurDao);

	UtilisateurDao getUtilisateurDao();

	void setExemplaireDao(ExemplaireDao exemplaireDao);

	ExemplaireDao getExemplaireDao();

	void setEmpruntDao(EmpruntDao empruntDao);

	EmpruntDao getEmpruntDao();
}