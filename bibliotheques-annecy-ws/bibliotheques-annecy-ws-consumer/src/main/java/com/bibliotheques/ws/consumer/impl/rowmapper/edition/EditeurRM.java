package com.bibliotheques.ws.consumer.impl.rowmapper.edition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.model.bean.edition.Editeur;

/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Editeur}
 * @author André Monnier
 */
public class EditeurRM implements RowMapper<Editeur> {

	@Override
	public Editeur mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		Editeur vEditeur=new Editeur();
		vEditeur.setId(pRS.getInt("id"));
		vEditeur.setNomEditeur(pRS.getString("nom_editeur"));
		return vEditeur;
	}
}