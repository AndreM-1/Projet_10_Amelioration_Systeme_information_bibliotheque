package com.bibliotheques.ws.consumer.impl.rowmapper.edition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.AuteurDao;
import com.bibliotheques.ws.model.bean.edition.Ouvrage;

/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Ouvrage}
 * @author André Monnier
 */
public class OuvrageRM implements RowMapper<Ouvrage> {
	
	private AuteurDao auteurDao;
	
	public OuvrageRM(AuteurDao auteurDao) {
		this.auteurDao=auteurDao;
	}

	@Override
	public Ouvrage mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		Ouvrage vOuvrage=new Ouvrage();
		vOuvrage.setId(pRS.getInt("id"));
		vOuvrage.setTitre(pRS.getString("titre"));
		vOuvrage.setResume(pRS.getString("resume"));
		vOuvrage.setAuteur(auteurDao.getAuteur(pRS.getInt("auteur_id")));
		return vOuvrage;
	}
}