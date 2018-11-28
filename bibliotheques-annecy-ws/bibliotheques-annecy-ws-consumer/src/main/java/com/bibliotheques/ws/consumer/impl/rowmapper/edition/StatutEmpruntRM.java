package com.bibliotheques.ws.consumer.impl.rowmapper.edition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.model.bean.edition.StatutEmprunt;

/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link StatutEmprunt}
 * @author André Monnier
 */
public class StatutEmpruntRM implements RowMapper<StatutEmprunt> {
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(StatutEmpruntRM.class);

	@Override
	public StatutEmprunt mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.info("Web Service : EditionService - StatutEmpruntRM");
		StatutEmprunt vStatutEmprunt=new StatutEmprunt();
		vStatutEmprunt.setId(pRS.getInt("id"));
		vStatutEmprunt.setStatutEmprunt(pRS.getString("statut_emprunt"));
		return vStatutEmprunt;
	}
}