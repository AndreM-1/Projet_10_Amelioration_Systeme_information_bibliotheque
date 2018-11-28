package com.bibliotheques.ws.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.StatutEmpruntDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.StatutEmpruntRM;
import com.bibliotheques.ws.model.bean.edition.StatutEmprunt;

@Named
public class StatutEmpruntDaoImpl extends AbstractDaoImpl implements StatutEmpruntDao  {
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(StatutEmpruntDaoImpl.class);
	
	@Override
	public StatutEmprunt getStatutEmprunt(int statutEmpruntId) {
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getStatutEmprunt()");
		String vSQL = "SELECT * FROM public.statut_emprunt WHERE id= "+statutEmpruntId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<StatutEmprunt> vRowMapper= new StatutEmpruntRM();
		List<StatutEmprunt> vListStatutEmprunt = vJdbcTemplate.query(vSQL, vRowMapper);
		return vListStatutEmprunt.get(0);
	}
}