package com.bibliotheques.appliweb.webapp.action;

import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.bibliotheques.appliweb.business.contract.ManagerFactory;
import com.bibliotheques.appliweb.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.appliweb.model.exception.CreerCompteUtilisateurFault_Exception;
import com.opensymphony.xwork2.ActionSupport;


/**
 * Classe d'action permettant de créer un compte {@link Utilisateur}
 * @author André Monnier
 */
public class GestionCreationCompteAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4250681548714234862L;

	@Inject
	private ManagerFactory managerFactory;

	// ----- Paramètres
	private String civilite;
	private String nom;
	private String prenom;
	private String pseudo;
	private String adresseMail;
	private String motDePasse;
	private String confirmationMotDePasse;
	private boolean conditionsUtilisation=false;

	// ----- Eléments Struts
	private Map<String, Object> session;

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(GestionCreationCompteAction.class);



	// ===================== Getters/Setters =====================
	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getConfirmationMotDePasse() {
		return confirmationMotDePasse;
	}

	public void setConfirmationMotDePasse(String confirmationMotDePasse) {
		this.confirmationMotDePasse = confirmationMotDePasse;
	}

	public boolean isConditionsUtilisation() {
		return conditionsUtilisation;
	}

	public void setConditionsUtilisation(boolean conditionsUtilisation) {
		this.conditionsUtilisation = conditionsUtilisation;
	}

	@Override
	public void setSession(Map<String, Object> pSession) {
		this.session=pSession;
	}

	// ===================== Méthodes =====================

	/**
	 * Méthode permettant la création d'un compte utilisateur
	 * @return input / success
	 */
	public String doCreationCompte() {
		LOGGER.info("GestionCreationCompteAction - Méthode doCreationCompte()");
		String vResult;
		LOGGER.info("Civilite : "+civilite);
		LOGGER.info("Nom : "+nom);
		LOGGER.info("Prénom : "+prenom);
		LOGGER.info("Pseudo : "+pseudo);
		LOGGER.info("Adresse Mail : "+adresseMail);
		LOGGER.info("Mot de passe :"+motDePasse);
		LOGGER.info("Confirmation du nouveau mot de passe :"+confirmationMotDePasse);
		LOGGER.info("Conditions d'Utilisation :"+conditionsUtilisation);

		//Si les champs ci-dessous sont null, c'est que l'on entre dans le formulaire de création de compte utilisateur.
		//Sinon, c'est que l'on vient de valider le formulaire de création de compte.
		//Par défaut, le result est "input".
		if(civilite==null && nom==null && prenom==null && pseudo==null &&adresseMail==null && motDePasse==null && confirmationMotDePasse==null && conditionsUtilisation==false) {
			vResult = ActionSupport.INPUT;
		}else {
			try {
				Utilisateur vUtilisateur=managerFactory.getUtilisateurManager().creerCompteUtilisateur(civilite, nom, prenom, pseudo,
						adresseMail, motDePasse, confirmationMotDePasse, conditionsUtilisation);
				this.session.put("user", vUtilisateur);
				vResult=ActionSupport.SUCCESS;
			} catch (CreerCompteUtilisateurFault_Exception e) {
				LOGGER.info(e.getMessage());
				this.addActionError(e.getMessage());
				vResult=ActionSupport.INPUT;
			}
		}
		return vResult;
	}

}