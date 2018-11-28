package com.bibliotheques.ws.webapp.utilisateurservice.impl;

import javax.inject.Inject;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.ws.business.contract.ManagerFactory;
import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.AuthentifierUtilisateurFault;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.AuthentifierUtilisateurFault_Exception;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.CreerCompteUtilisateurFault;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.CreerCompteUtilisateurFault_Exception;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.UpdateCoordUtilisateurFault;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.UpdateCoordUtilisateurFault_Exception;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.UpdateMdpUtilisateurFault;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.UpdateMdpUtilisateurFault_Exception;
import com.bibliotheques.ws.webapp.utilisateurservice.generated.UtilisateurService;

public class UtilisateurServiceImpl implements UtilisateurService {

	@Inject
	private ManagerFactory managerFactory;

	// ----- Paramètres
	private Utilisateur utilisateur;

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(UtilisateurServiceImpl.class);

	@Override
	public Utilisateur authentifierUtilisateur(String adresseMail, String motDePasse)
			throws AuthentifierUtilisateurFault_Exception {
		
		LOGGER.info("Web service - Méthode authentifierUtilisateur()");
		LOGGER.info("Adresse mail :"+adresseMail);
		LOGGER.info("Mot de Passe :"+motDePasse);

		if (!StringUtils.isAllEmpty(adresseMail, motDePasse)) {
			try {
				utilisateur=managerFactory.getUtilisateurManager().getUtilisateur(adresseMail,motDePasse);
			} catch (NotFoundException pEx) {
				LOGGER.info(pEx.getMessage());
				AuthentifierUtilisateurFault authentifierUtilisateurFault=new AuthentifierUtilisateurFault();
				authentifierUtilisateurFault.setFaultMessageErreur(pEx.getMessage());
				throw new AuthentifierUtilisateurFault_Exception("Identifiant ou mot de passe invalide !",authentifierUtilisateurFault);
			}
		}else {
			AuthentifierUtilisateurFault authentifierUtilisateurFault=new AuthentifierUtilisateurFault();
			authentifierUtilisateurFault.setFaultMessageErreur("L'identifiant et le mot de passe ne sont pas renseignés.");
			throw new AuthentifierUtilisateurFault_Exception("Champs non renseignés.",authentifierUtilisateurFault);
		}

		return utilisateur;
	}

	@Override
	public Utilisateur creerCompteUtilisateur(String civilite, String nom, String prenom, String pseudo,
			String adresseMail, String motDePasse, String confirmationMotDePasse, boolean conditionsUtilisation)
					throws CreerCompteUtilisateurFault_Exception {
		
		LOGGER.info("Web service - creerCompteUtilisateur()");

		try {
			managerFactory.getUtilisateurManager().insertUtilisateur(civilite,nom,prenom,pseudo,adresseMail,motDePasse,confirmationMotDePasse,conditionsUtilisation);

		} catch (FunctionalException vFex) {
			LOGGER.info(vFex.getMessage());
			CreerCompteUtilisateurFault creerCompteUtilisateurFault=new CreerCompteUtilisateurFault(); 
			creerCompteUtilisateurFault.setFaultMessageErreur(vFex.getMessage());
			
			if(vFex.getMessage().equals("Veuillez accepter les conditions d'utilisation.")) {
				throw new CreerCompteUtilisateurFault_Exception(vFex.getMessage(),creerCompteUtilisateurFault);
			} else if(vFex.getMessage().contains("champ")){
				throw new CreerCompteUtilisateurFault_Exception("Certains paramètres ne sont pas renseignés correctement.",creerCompteUtilisateurFault);
			}else if(vFex.getMessage().contains("pseudo")) {
				throw new CreerCompteUtilisateurFault_Exception(vFex.getMessage(),creerCompteUtilisateurFault);
			}
		}

		try {
			utilisateur=managerFactory.getUtilisateurManager().getUtilisateur(adresseMail,motDePasse);
			LOGGER.info("Utilisateur id : "+utilisateur.getId());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return utilisateur;
	}

	@Override
	public void updateCoordUtilisateur(int id, String civilite, String nom, String prenom, String pseudo,
			String adresseMail, String telephone, XMLGregorianCalendar dateNaissance, String adresse, String codePostal,
			String ville, String pays) throws UpdateCoordUtilisateurFault_Exception {
		
		LOGGER.info("Web service - updateCoordUtilisateur()");
		
		try {
			managerFactory.getUtilisateurManager().updateCoordUtilisateur(id, civilite, nom, prenom, pseudo,
					adresseMail, telephone, dateNaissance, adresse, codePostal, ville, pays);
		} catch (FunctionalException vFex) {
			LOGGER.info(vFex.getMessage());
			UpdateCoordUtilisateurFault updateCoordUtilisateurFault=new UpdateCoordUtilisateurFault();
			updateCoordUtilisateurFault.setFaultMessageErreur(vFex.getMessage());
			
			if(vFex.getMessage().contains("champ")) {
				throw new UpdateCoordUtilisateurFault_Exception("Certains paramètres ne sont pas renseignés correctement.",updateCoordUtilisateurFault);
			}else if(vFex.getMessage().contains("pseudo")) {
				throw new UpdateCoordUtilisateurFault_Exception(vFex.getMessage(),updateCoordUtilisateurFault);
			}
		}
	}
	
	@Override
	public void updateMdpUtilisateur(int id, String ancienMdp, String nouveauMdp, String confirmationNouveauMdp)
			throws UpdateMdpUtilisateurFault_Exception {
		
		LOGGER.info("Web service - updateMdpUtilisateur()");
		
		try {
			managerFactory.getUtilisateurManager().updateMdpUtilisateur(id, ancienMdp, nouveauMdp, confirmationNouveauMdp);
		} 
		
		catch (NotFoundException vNFEx) {
			LOGGER.info(vNFEx.getMessage());
			UpdateMdpUtilisateurFault updateMdpUtilisateurFault=new UpdateMdpUtilisateurFault();
			updateMdpUtilisateurFault.setFaultMessageErreur(vNFEx.getMessage());
			throw new UpdateMdpUtilisateurFault_Exception(vNFEx.getMessage(),updateMdpUtilisateurFault);
		}
		
		catch (FunctionalException vFEx) {
			LOGGER.info(vFEx.getMessage());
			UpdateMdpUtilisateurFault updateMdpUtilisateurFault=new UpdateMdpUtilisateurFault();
			updateMdpUtilisateurFault.setFaultMessageErreur(vFEx.getMessage());
			throw new UpdateMdpUtilisateurFault_Exception(vFEx.getMessage(),updateMdpUtilisateurFault);
		}
	}
}