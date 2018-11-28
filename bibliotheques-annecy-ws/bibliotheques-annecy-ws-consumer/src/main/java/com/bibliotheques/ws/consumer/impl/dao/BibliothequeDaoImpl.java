package com.bibliotheques.ws.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.BibliothequeDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.BibliothequeRM;
import com.bibliotheques.ws.model.bean.edition.Bibliotheque;

@Named
public class BibliothequeDaoImpl extends AbstractDaoImpl implements BibliothequeDao{

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(BibliothequeDaoImpl.class);
	
	@Override
	public Bibliotheque getBibliotheque(int bibliothequeId) {
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getBibliotheque()");
		String vSQL="SELECT * FROM public.bibliotheque WHERE id="+bibliothequeId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Bibliotheque> vRowMapper=new BibliothequeRM();
		List<Bibliotheque> vListBibliotheque=vJdbcTemplate.query(vSQL, vRowMapper);
		return vListBibliotheque.get(0);
	}
}
