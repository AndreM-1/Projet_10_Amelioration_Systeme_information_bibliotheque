package com.bibliotheques.ws.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.AuteurDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.AuteurRM;
import com.bibliotheques.ws.model.bean.edition.Auteur;


@Named
public class AuteurDaoImpl extends AbstractDaoImpl implements AuteurDao{

	@Override
	public Auteur getAuteur(int auteurId) {
		String vSQL = "SELECT * FROM public.auteur WHERE id= "+auteurId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Auteur> vRowMapper= new AuteurRM();
		List<Auteur> vListAuteur = vJdbcTemplate.query(vSQL, vRowMapper);
		return vListAuteur.get(0);
	}
}
