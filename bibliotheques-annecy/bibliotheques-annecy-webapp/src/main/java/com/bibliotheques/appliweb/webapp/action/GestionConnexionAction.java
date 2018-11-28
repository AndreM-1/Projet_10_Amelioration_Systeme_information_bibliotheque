package com.bibliotheques.appliweb.webapp.action;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.bibliotheques.appliweb.business.contract.ManagerFactory;
import com.bibliotheques.appliweb.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.appliweb.model.exception.AuthentifierUtilisateurFault_Exception;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe d'action permettant de gérer la connexion
 * et la déconnexion d'un {@link Utilisateur}
 * @author André Monnier
 */
public class GestionConnexionAction extends ActionSupport implements SessionAware,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998580473169565846L;
	
	@Inject
	private ManagerFactory managerFactory;
	
	// ----- Paramètres
	private String adresseMail;
	private String motDePasse;
	
	// ----- Eléments Struts
	private Map<String, Object> session;
	private HttpServletRequest servletRequest;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(GestionConnexionAction.class);
	
	// ===================== Getters/Setters =====================
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
	
	@Override
	public void setSession(Map<String, Object> pSession) {
		this.session=pSession;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest pRequest) {
		this.servletRequest=pRequest;
	}

	
	// ===================== Méthodes ======================

	/**
	 * Méthode permettant la connexion d'un utilisateur. Le but est de ressortir de cette méthode
	 * avec un objet de type {@link Utilisateur} en session
	 * @return input / success
	 */
	public String doLogin()  {
		LOGGER.info("GestionConnexionAction - Méthode doLogin()");
		String vResult;
		LOGGER.info("Adresse Mail : "+adresseMail);
		LOGGER.info("Mot de passe :"+motDePasse);
		if(adresseMail==null &&motDePasse==null) {
			vResult=ActionSupport.INPUT;
		}else {
			try {
				Utilisateur vUtilisateur=managerFactory.getUtilisateurManager().authentifierUtilisateur(adresseMail,motDePasse);
				this.session.put("user", vUtilisateur);
				vResult=ActionSupport.SUCCESS;
			} catch (AuthentifierUtilisateurFault_Exception e) {
				LOGGER.info(e.getMessage());
				this.addActionError(e.getMessage());
				vResult=ActionSupport.INPUT;
			}
		}
		
		return vResult;
	}
	

	/**
	 * Méthode permettant la déconnexion d'un utilisateur
	 * @return success
	 */
	public String doLogout() {

		//Invalidation de la session
		this.servletRequest.getSession().invalidate();
		return ActionSupport.SUCCESS;
	}

}
