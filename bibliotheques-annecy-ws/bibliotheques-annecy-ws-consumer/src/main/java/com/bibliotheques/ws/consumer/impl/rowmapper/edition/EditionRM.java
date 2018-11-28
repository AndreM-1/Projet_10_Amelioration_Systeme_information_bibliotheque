package com.bibliotheques.ws.consumer.impl.rowmapper.edition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.EditeurDao;
import com.bibliotheques.ws.consumer.contract.dao.GenreDao;
import com.bibliotheques.ws.consumer.contract.dao.OuvrageDao;
import com.bibliotheques.ws.consumer.contract.dao.PhotoDao;
import com.bibliotheques.ws.model.bean.edition.Edition;

/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Edition}
 * @author André Monnier
 */
public class EditionRM implements RowMapper<Edition>{
	
	private OuvrageDao ouvrageDao;
	private PhotoDao photoDao;
	private EditeurDao editeurDao;
	private GenreDao genreDao;

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EditionRM.class);
	
	public EditionRM(OuvrageDao ouvrageDao,PhotoDao photoDao,EditeurDao editeurDao,GenreDao genreDao) {
		this.ouvrageDao=ouvrageDao;
		this.photoDao=photoDao;
		this.editeurDao=editeurDao;
		this.genreDao=genreDao;
	}

	@Override
	public Edition mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.info("Web Service : EditionService -Edition RM");
		Edition vEdition=new Edition();
		vEdition.setId(pRS.getInt("id"));
		vEdition.setIsbn(pRS.getString("isbn"));
		
		//Conversion du format Date en format XMLGregorianCalendar
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(pRS.getDate("date_parution"));
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            LOGGER.info("Web Service : EditionService -Edition RM - xmlCalendar :"+xmlCalendar);
            vEdition.setDateParution(xmlCalendar);
        } catch (DatatypeConfigurationException ex) {
            LOGGER.info("Problème de conversion du format Date en format XMLGregorianCalendar");
        }
		
		vEdition.setNbPages(pRS.getInt("nb_pages"));
		vEdition.setFormat(pRS.getString("format"));
		vEdition.setOuvrage(ouvrageDao.getOuvrage(pRS.getInt("ouvrage_id")));
		vEdition.setPhoto(photoDao.getPhoto(pRS.getInt("photo_id")));
		vEdition.setEditeur(editeurDao.getEditeur(pRS.getInt("editeur_id")));
		vEdition.setGenre(genreDao.getGenre(pRS.getInt("genre_id")));
		return vEdition;
	}
}
