package com.bibliotheques.ws.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.AuteurDao;
import com.bibliotheques.ws.consumer.contract.dao.OuvrageDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.OuvrageRM;
import com.bibliotheques.ws.model.bean.edition.Ouvrage;

@Named
public class OuvrageDaoImpl extends AbstractDaoImpl implements OuvrageDao {
	
	@Inject
	private AuteurDao auteurDao;

	@Override
	public Ouvrage getOuvrage(int ouvrageId) {
		String vSQL = "SELECT * FROM public.ouvrage WHERE id= "+ouvrageId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Ouvrage> vRowMapper= new OuvrageRM(auteurDao);
		List<Ouvrage> vListOuvrage = vJdbcTemplate.query(vSQL, vRowMapper);
		
		return vListOuvrage.get(0);
	}
}
