package com.bibliotheques.ws.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.EditeurDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.EditeurRM;
import com.bibliotheques.ws.model.bean.edition.Editeur;

@Named
public class EditeurDaoImpl extends AbstractDaoImpl implements EditeurDao{

	@Override
	public Editeur getEditeur(int editeurId) {
		String vSQL = "SELECT * FROM public.editeur WHERE id= "+editeurId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Editeur> vRowMapper= new EditeurRM();
		List<Editeur> vListEditeur = vJdbcTemplate.query(vSQL, vRowMapper);
		return vListEditeur.get(0);
	}
}