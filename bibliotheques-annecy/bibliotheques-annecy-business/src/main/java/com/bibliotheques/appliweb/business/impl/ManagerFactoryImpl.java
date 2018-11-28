package com.bibliotheques.appliweb.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.bibliotheques.appliweb.business.contract.ManagerFactory;
import com.bibliotheques.appliweb.business.contract.manager.EditionManager;
import com.bibliotheques.appliweb.business.contract.manager.EmpruntManager;
import com.bibliotheques.appliweb.business.contract.manager.ExemplaireManager;
import com.bibliotheques.appliweb.business.contract.manager.UtilisateurManager;


/**
 * Implémentation de la {@link ManagerFactory}.
 */
@Named
public class ManagerFactoryImpl implements ManagerFactory {
	
	@Inject
	private EditionManager editionManager;
	
	@Inject
	private UtilisateurManager utilisateurManager;
	
	@Inject
	private ExemplaireManager exemplaireManager;
	
	@Inject
	private EmpruntManager empruntManager;

	@Override
	public EditionManager getEditionManager() {
		return editionManager;
	}

	@Override
	public void setEditionManager(EditionManager editionManager) {
		this.editionManager = editionManager;
	}

	@Override
	public UtilisateurManager getUtilisateurManager() {
		return utilisateurManager;
	}

	@Override
	public void setUtilisateurManager(UtilisateurManager utilisateurManager) {
		this.utilisateurManager = utilisateurManager;
	}

	@Override
	public ExemplaireManager getExemplaireManager() {
		return exemplaireManager;
	}

	@Override
	public void setExemplaireManager(ExemplaireManager exemplaireManager) {
		this.exemplaireManager = exemplaireManager;
	}

	@Override
	public EmpruntManager getEmpruntManager() {
		return empruntManager;
	}

	@Override
	public void setEmpruntManager(EmpruntManager empruntManager) {
		this.empruntManager = empruntManager;
	}
}