package com.bibliotheques.ws.consumer.impl.rowmapper.edition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.model.bean.edition.Genre;

/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Genre}
 * @author André Monnier
 */
public class GenreRM implements RowMapper<Genre> {

	@Override
	public Genre mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		Genre vGenre =new Genre();
		vGenre.setId(pRS.getInt("id"));
		vGenre.setGenre(pRS.getString("genre"));
		return vGenre;
	}
}