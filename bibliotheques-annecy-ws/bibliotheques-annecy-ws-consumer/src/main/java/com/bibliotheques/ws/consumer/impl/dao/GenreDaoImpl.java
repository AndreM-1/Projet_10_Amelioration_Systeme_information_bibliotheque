package com.bibliotheques.ws.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.GenreDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.GenreRM;
import com.bibliotheques.ws.model.bean.edition.Genre;

@Named
public class GenreDaoImpl extends AbstractDaoImpl implements GenreDao {

	@Override
	public Genre getGenre(int genreId) {
		String vSQL = "SELECT * FROM public.genre WHERE id= "+genreId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Genre> vRowMapper= new GenreRM();
		List<Genre> vListGenre = vJdbcTemplate.query(vSQL, vRowMapper);
		return vListGenre.get(0);
	}
}