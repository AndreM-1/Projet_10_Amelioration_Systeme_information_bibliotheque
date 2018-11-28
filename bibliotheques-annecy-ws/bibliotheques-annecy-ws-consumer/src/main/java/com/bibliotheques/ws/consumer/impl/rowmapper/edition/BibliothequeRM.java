package com.bibliotheques.ws.consumer.impl.rowmapper.edition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.model.bean.edition.Bibliotheque;

/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Bibliotheque}
 * @author André Monnier
 */
public class BibliothequeRM implements RowMapper<Bibliotheque>{

	@Override
	public Bibliotheque mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		Bibliotheque vBibliotheque=new Bibliotheque();
		vBibliotheque.setId(pRS.getInt("id"));
		vBibliotheque.setNomBibliotheque(pRS.getString("nom_bibliotheque"));
		vBibliotheque.setTelephone(pRS.getString("telephone"));
		vBibliotheque.setAdresse(pRS.getString("adresse"));
		vBibliotheque.setCodePostal(pRS.getString("code_postal"));
		vBibliotheque.setVille(pRS.getString("ville"));
		vBibliotheque.setPays(pRS.getString("pays"));
		return vBibliotheque;
	}
}