package com.bibliotheques.appliweb.business.impl.manager;

import javax.inject.Inject;

import com.bibliotheques.appliweb.consumer.contract.DaoFactory;

/**
 * Classe permettant de définir une DaoFactory qui sera utilisée par
 * les implémentations de la couche business.
 * @author André Monnier
 */
public abstract class AbstractManager {

	@Inject
    private DaoFactory daoFactory; 
	

	protected DaoFactory getDaoFactory() {
		return daoFactory; 
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
}