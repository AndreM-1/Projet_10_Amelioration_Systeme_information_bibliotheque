package com.bibliotheques.appliweb.consumer.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.bibliotheques.appliweb.consumer.contract.DaoFactory;
import com.bibliotheques.appliweb.consumer.contract.dao.EditionDao;
import com.bibliotheques.appliweb.consumer.contract.dao.EmpruntDao;
import com.bibliotheques.appliweb.consumer.contract.dao.ExemplaireDao;
import com.bibliotheques.appliweb.consumer.contract.dao.UtilisateurDao;


/**
 * Implémentation de la {@link DaoFactory}
 */
@Named
public class DaoFactoryImpl implements DaoFactory{
	
	@Inject
	private EditionDao editionDao;
	
	@Inject
	private UtilisateurDao utilisateurDao;
	
	@Inject
	private ExemplaireDao exemplaireDao;
	
	@Inject
	private EmpruntDao empruntDao;

	@Override
	public EditionDao getEditionDao() {
		return editionDao;
	}

	@Override
	public void setEditionDao(EditionDao editionDao) {
		this.editionDao = editionDao;
	}

	@Override
	public UtilisateurDao getUtilisateurDao() {
		return utilisateurDao;
	}

	@Override
	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	@Override
	public ExemplaireDao getExemplaireDao() {
		return exemplaireDao;
	}

	@Override
	public void setExemplaireDao(ExemplaireDao exemplaireDao) {
		this.exemplaireDao = exemplaireDao;
	}

	@Override
	public EmpruntDao getEmpruntDao() {
		return empruntDao;
	}

	@Override
	public void setEmpruntDao(EmpruntDao empruntDao) {
		this.empruntDao = empruntDao;
	}
}