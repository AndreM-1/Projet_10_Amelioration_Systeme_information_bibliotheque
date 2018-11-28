package com.bibliotheques.ws.consumer.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.bibliotheques.ws.consumer.contract.DaoFactory;
import com.bibliotheques.ws.consumer.contract.dao.EditeurDao;
import com.bibliotheques.ws.consumer.contract.dao.EditionDao;
import com.bibliotheques.ws.consumer.contract.dao.EmpruntDao;
import com.bibliotheques.ws.consumer.contract.dao.ExemplaireDao;
import com.bibliotheques.ws.consumer.contract.dao.GenreDao;
import com.bibliotheques.ws.consumer.contract.dao.OuvrageDao;
import com.bibliotheques.ws.consumer.contract.dao.PhotoDao;
import com.bibliotheques.ws.consumer.contract.dao.UtilisateurDao;


/**
 * Implémentation de la {@link DaoFactory}
 */
@Named
public class DaoFactoryImpl implements DaoFactory{
	
	@Inject
	private EditeurDao editeurDao;
	
	@Inject
	private EditionDao editionDao;
	
	@Inject
	private GenreDao genreDao;
	
	@Inject
	private OuvrageDao ouvrageDao;
	
	@Inject
	private PhotoDao photoDao;
	
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
	public EditeurDao getEditeurDao() {
		return editeurDao;
	}

	@Override
	public void setEditeurDao(EditeurDao editeurDao) {
		this.editeurDao = editeurDao;
	}

	@Override
	public GenreDao getGenreDao() {
		return genreDao;
	}

	@Override
	public void setGenreDao(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	@Override
	public OuvrageDao getOuvrageDao() {
		return ouvrageDao;
	}

	@Override
	public void setOuvrageDao(OuvrageDao ouvrageDao) {
		this.ouvrageDao = ouvrageDao;
	}

	@Override
	public PhotoDao getPhotoDao() {
		return photoDao;
	}

	@Override
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
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