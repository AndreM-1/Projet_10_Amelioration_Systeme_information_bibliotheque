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
import com.bibliotheques.ws.consumer.contract.dao.StatutEmpruntDao;
import com.bibliotheques.ws.consumer.contract.dao.UtilisateurDao;

import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.exception.NotFoundException;

/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Emprunt}
 * @author André Monnier
 */
public class EmpruntRM implements RowMapper<Emprunt>{
	
	private UtilisateurDao utilisateurDao;
	private StatutEmpruntDao statutEmpruntDao;
	private ExemplaireDao exemplaireDao;
	
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EmpruntRM.class);
	
	public EmpruntRM(UtilisateurDao utilisateurDao,StatutEmpruntDao statutEmpruntDao,ExemplaireDao exemplaireDao) {
		this.utilisateurDao=utilisateurDao;
		this.statutEmpruntDao=statutEmpruntDao;
		this.exemplaireDao=exemplaireDao;
	}

	@Override
	public Emprunt mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		LOGGER.info("Web Service : EditionService - EmpruntRM");
		Emprunt vEmprunt=new Emprunt();
		vEmprunt.setId(pRS.getInt("id"));
		
		//Conversion du format Date en format XMLGregorianCalendar pour le champ "date_de_debut"
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(pRS.getDate("date_de_debut"));
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            LOGGER.info("Web Service : EditionService -Emprunt RM - xmlCalendar :"+xmlCalendar);
            vEmprunt.setDateDeDebut(xmlCalendar);
        } catch (DatatypeConfigurationException ex) {
            LOGGER.info("Problème de conversion du format Date en format XMLGregorianCalendar");
        }
		
        //Conversion du format Date en format XMLGregorianCalendar pour le champ "date_de_fin"
        GregorianCalendar gCalendarDdf = new GregorianCalendar();
        gCalendarDdf.setTime(pRS.getDate("date_de_fin"));
        XMLGregorianCalendar xmlCalendarDdf = null;
        try {
            xmlCalendarDdf = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendarDdf);
            LOGGER.info("Web Service : EditionService -Emprunt RM - xmlCalendarDdf :"+xmlCalendarDdf);
            vEmprunt.setDateDeFin(xmlCalendarDdf);
        } catch (DatatypeConfigurationException ex) {
            LOGGER.info("Problème de conversion du format Date en format XMLGregorianCalendar");
        }
        
		vEmprunt.setProlongation(pRS.getBoolean("prolongation"));
		
		if(pRS.getDate("date_de_prolongation")!=null) {
			//Conversion du format Date en format XMLGregorianCalendar pour le champ "date_de_prolongation"
			GregorianCalendar gCalendarDdp = new GregorianCalendar();
			gCalendarDdp.setTime(pRS.getDate("date_de_prolongation"));
			XMLGregorianCalendar xmlCalendarDdp = null;
			try {
			    xmlCalendarDdp = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendarDdp);
			    LOGGER.info("Web Service : EditionService -Emprunt RM - xmlCalendarDdp :"+xmlCalendarDdp);
			    vEmprunt.setDateDeProlongation(xmlCalendarDdp);
			} catch (DatatypeConfigurationException ex) {
			    LOGGER.info("Problème de conversion du format Date en format XMLGregorianCalendar");
			}
       }
		
		if(pRS.getString("duree_prolongation")!=null)
			vEmprunt.setDureeProlongation(pRS.getString("duree_prolongation"));
		 
		if(pRS.getDate("date_de_retour_effective")!=null) {
			//Conversion du format Date en format XMLGregorianCalendar pour le champ "date_de_retour_effective"
			GregorianCalendar gCalendarDdr = new GregorianCalendar();
	        gCalendarDdr.setTime(pRS.getDate("date_de_retour_effective"));
	        XMLGregorianCalendar xmlCalendarDdr = null;
	        try {
	        	xmlCalendarDdr = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendarDdr);
	            LOGGER.info("Web Service : EditionService -Emprunt RM - xmlCalendarDdr :"+xmlCalendarDdr);
	            vEmprunt.setDateDeRetourEffective(xmlCalendarDdr);
	        } catch (DatatypeConfigurationException ex) {
	            LOGGER.info("Problème de conversion du format Date en format XMLGregorianCalendar");
	        }
		}
		
		try {
			vEmprunt.setUtilisateur(utilisateurDao.getUtilisateur(pRS.getInt("utilisateur_id")));
		} catch (NotFoundException e1) {
			LOGGER.info(e1.getMessage());
		}
		
		vEmprunt.setStatutEmprunt(statutEmpruntDao.getStatutEmprunt(pRS.getInt("statut_emprunt_id")));
		
		try {
			vEmprunt.setExemplaire(exemplaireDao.getExemplaire(pRS.getInt("exemplaire_bibliotheque_id"), pRS.getInt("exemplaire_edition_id")));
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
		}
		
		return vEmprunt;
	}
}