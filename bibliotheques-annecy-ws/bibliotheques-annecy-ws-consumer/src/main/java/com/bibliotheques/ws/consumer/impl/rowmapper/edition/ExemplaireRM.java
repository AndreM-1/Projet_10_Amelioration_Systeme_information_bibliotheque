package com.bibliotheques.ws.consumer.impl.rowmapper.edition;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.BibliothequeDao;
import com.bibliotheques.ws.consumer.contract.dao.EditionDao;
import com.bibliotheques.ws.model.bean.edition.Exemplaire;


/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Exemplaire}
 * @author André Monnier
 */
public class ExemplaireRM implements RowMapper<Exemplaire> {
	
	private BibliothequeDao bibliothequeDao;
	private EditionDao editionDao;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ExemplaireRM.class);
	
	public ExemplaireRM(BibliothequeDao bibliothequeDao, EditionDao editionDao) {
		this.bibliothequeDao=bibliothequeDao;
		this.editionDao=editionDao;
	}

	@Override
	public Exemplaire mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.info("Web Service : EditionService -ExemplaireRM");
		Exemplaire vExemplaire=new Exemplaire();
		vExemplaire.setBibliotheque(bibliothequeDao.getBibliotheque(pRS.getInt("bibliotheque_id")));
		vExemplaire.setEdition(editionDao.getEdition(pRS.getInt("edition_id")));
		vExemplaire.setNbExemplaires(pRS.getInt("nb_exemplaires"));
		return vExemplaire;
	}
}