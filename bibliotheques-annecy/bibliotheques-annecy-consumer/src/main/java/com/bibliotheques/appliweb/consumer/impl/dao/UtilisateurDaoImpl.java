package com.bibliotheques.appliweb.consumer.impl.dao;

import javax.inject.Named;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.consumer.contract.dao.UtilisateurDao;
import com.bibliotheques.appliweb.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.appliweb.model.exception.AuthentifierUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.CreerCompteUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.UpdateCoordUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.UpdateMdpUtilisateurFault_Exception;

@Named
public class UtilisateurDaoImpl extends AbstractDaoImpl implements UtilisateurDao {
	
	private Utilisateur utilisateur;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(UtilisateurDaoImpl.class);
	
	@Override
	public Utilisateur authentifierUtilisateur(String adresseMail, String motDePasse) throws AuthentifierUtilisateurFault_Exception {
		LOGGER.info("Couche Consumer - Méthode authentifierUtilisateur()");
		try {
			utilisateur=getUtilisateurService().authentifierUtilisateur(adresseMail, motDePasse);
		} catch (AuthentifierUtilisateurFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new AuthentifierUtilisateurFault_Exception(e.getMessage());
		}
		LOGGER.info("Réponse du web service : "+utilisateur);
		return utilisateur;
	}
	
	@Override
	public Utilisateur creerCompteUtilisateur(String civilite, String nom, String prenom, String pseudo,
			String adresseMail, String motDePasse, String confirmationMotDePasse, boolean conditionsUtilisation) throws CreerCompteUtilisateurFault_Exception{
		LOGGER.info("Couche Consumer - Méthode creerCompteUtilisateur()");
		try {
			utilisateur=getUtilisateurService().creerCompteUtilisateur(civilite, nom, prenom, pseudo, adresseMail, motDePasse, confirmationMotDePasse, conditionsUtilisation);
		} catch (CreerCompteUtilisateurFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new CreerCompteUtilisateurFault_Exception(e.getMessage());
		}
		LOGGER.info("Réponse du web service : "+utilisateur);
		return utilisateur;
	}
	
	@Override
	public void updateCoordUtilisateur(int id, String civilite, String nom, String prenom, String pseudo,
			String adresseMail, String telephone, XMLGregorianCalendar dateNaissance, String adresse, String codePostal,
			String ville, String pays) throws UpdateCoordUtilisateurFault_Exception{
		LOGGER.info("Couche Consumer - Méthode updateCoordUtilisateur()");
		
		try {
			getUtilisateurService().updateCoordUtilisateur(id, civilite, nom, prenom, pseudo, adresseMail, telephone, dateNaissance, adresse, codePostal, ville, pays);
		} catch (UpdateCoordUtilisateurFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new UpdateCoordUtilisateurFault_Exception(e.getMessage());
		}
	}
	
	@Override
	public void updateMdpUtilisateur(int id, String ancienMotDePasse, String nouveauMotDePasse, String confirmationNouveauMotDePasse) 
			throws UpdateMdpUtilisateurFault_Exception{
		LOGGER.info("Couche Consumer - Méthode updateMdpUtilisateur()");
		
		try {
			getUtilisateurService().updateMdpUtilisateur(id, ancienMotDePasse, nouveauMotDePasse, confirmationNouveauMotDePasse);
		} catch (UpdateMdpUtilisateurFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new UpdateMdpUtilisateurFault_Exception (e.getMessage());
		}
	}
}