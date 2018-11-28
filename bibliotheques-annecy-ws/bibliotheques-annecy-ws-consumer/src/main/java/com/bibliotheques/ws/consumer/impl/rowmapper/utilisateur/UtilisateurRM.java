package com.bibliotheques.ws.consumer.impl.rowmapper.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;


/**
 * Classe de type RowMapper permettant de mapper des
 * lignes de résultats (du resultSet en BDD) en objet
 * de type {@link Utilisateur}
 * @author André Monnier
 */
public class UtilisateurRM implements RowMapper<Utilisateur>{
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(UtilisateurRM.class);

	@Override
	public Utilisateur mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		Utilisateur vUtilisateur=new Utilisateur();
		vUtilisateur.setId(pRS.getInt("id"));
		vUtilisateur.setCivilite(pRS.getString("civilite"));
		vUtilisateur.setNom(pRS.getString("nom"));
		vUtilisateur.setPrenom(pRS.getString("prenom"));
		vUtilisateur.setPseudo(pRS.getString("pseudo"));
		vUtilisateur.setAdresseMail(pRS.getString("adresse_mail"));
		vUtilisateur.setSalt(pRS.getString("salt"));
		vUtilisateur.setMotDePasseSecurise(pRS.getString("mot_de_passe_securise"));
		vUtilisateur.setTelephone(pRS.getString("telephone"));
		
		//Conversion du format Date en format XMLGregorianCalendar
		if(pRS.getDate("date_naissance")!=null) {
	        GregorianCalendar gCalendar = new GregorianCalendar();
	        gCalendar.setTime(pRS.getDate("date_naissance"));
	        XMLGregorianCalendar xmlCalendar = null;
	        try {
	            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
	            vUtilisateur.setDateNaissance(xmlCalendar);
	        } catch (DatatypeConfigurationException ex) {
	            LOGGER.info("Problème de conversion du format Date en format XMLGregorianCalendar");
	        }
		}

		vUtilisateur.setAdresse(pRS.getString("adresse"));
		vUtilisateur.setCodePostal(pRS.getString("code_postal"));
		vUtilisateur.setVille(pRS.getString("ville"));
		vUtilisateur.setPays(pRS.getString("pays"));
		return vUtilisateur;
	}
}