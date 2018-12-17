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

import com.bibliotheques.ws.consumer.contract.dao.ExemplaireDao;
import com.bibliotheques.ws.consumer.contract.dao.UtilisateurDao;
import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.exception.NotFoundException;

/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Reservation}
 * @author André Monnier
 */
public class ReservationRM implements RowMapper<Reservation>{
	
	private UtilisateurDao utilisateurDao;
	private ExemplaireDao exemplaireDao;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ReservationRM.class);
	
	public ReservationRM(UtilisateurDao utilisateurDao,ExemplaireDao exemplaireDao) {
		this.utilisateurDao=utilisateurDao;
		this.exemplaireDao=exemplaireDao;
	}
	
	
	@Override
	public Reservation mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.info("Web Service : EditionService - ReservationRM");
		Reservation vReservation=new Reservation();
		vReservation.setId(pRS.getInt("id"));
		
		//Conversion du format Timestamp en format XMLGregorianCalendar pour le champ "date_reservation"
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(pRS.getTimestamp("date_reservation"));
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            LOGGER.info("Web Service : EditionService -ReservationRM - xmlCalendar :"+xmlCalendar);
            vReservation.setDateReservation(xmlCalendar);
        } catch (DatatypeConfigurationException ex) {
            LOGGER.info("Problème de conversion du format Date en format XMLGregorianCalendar");
        }
        
        try {
			vReservation.setUtilisateur(utilisateurDao.getUtilisateur(pRS.getInt("utilisateur_id")));
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
		}
        
        try {
			vReservation.setExemplaire(exemplaireDao.getExemplaire(pRS.getInt("exemplaire_bibliotheque_id"), pRS.getInt("exemplaire_edition_id")));
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
		}
		
        vReservation.setPrioriteReservation(pRS.getInt("priorite_reservation"));
        
		//Conversion du format Date en format XMLGregorianCalendar pour le champ "date_reception_mail"
        if(pRS.getDate("date_reception_mail")!=null) {
	        GregorianCalendar gCalendarDrm = new GregorianCalendar();
	        gCalendarDrm.setTime(pRS.getDate("date_reception_mail"));
	        XMLGregorianCalendar xmlCalendarDrm = null;
	        try {
	            xmlCalendarDrm = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendarDrm);
	            LOGGER.info("Web Service : EditionService -ReservationRM - xmlCalendar :"+xmlCalendarDrm);
	            vReservation.setDateReceptionMail(xmlCalendarDrm);
	        } catch (DatatypeConfigurationException ex) {
	            LOGGER.info("Problème de conversion du format Date en format XMLGregorianCalendar");
	        }
        }
        
		return vReservation;
	}

}
