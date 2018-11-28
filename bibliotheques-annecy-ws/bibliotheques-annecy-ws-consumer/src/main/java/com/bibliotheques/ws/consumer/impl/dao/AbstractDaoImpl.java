package com.bibliotheques.ws.consumer.impl.dao;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

/**
 * Classe permettant de définir un objet de type {@link DataSource}
 * qui sera utilisé par les implémentations de la couche Dao pour se
 * connecter à la base de données. 
 * @author André Monnier
 */
public abstract class AbstractDaoImpl {

	@Inject
	@Named("dataSourceBibliotheque")
	private DataSource dataSource; 

	protected DataSource getDataSource() {
		return dataSource;
	}
}
