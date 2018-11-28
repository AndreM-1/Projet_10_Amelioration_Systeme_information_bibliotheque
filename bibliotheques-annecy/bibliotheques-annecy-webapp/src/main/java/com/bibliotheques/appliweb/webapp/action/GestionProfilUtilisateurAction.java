package com.bibliotheques.appliweb.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.bibliotheques.appliweb.business.contract.ManagerFactory;
import com.bibliotheques.appliweb.model.bean.edition.Emprunt;
import com.bibliotheques.appliweb.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.appliweb.model.exception.GestionPretFault_Exception;
import com.bibliotheques.appliweb.model.exception.UpdateCoordUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.UpdateMdpUtilisateurFault_Exception;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe d'action permettant de gérer le profil d'un {@link Utilisateur}
 * @author André Monnier
 */
public class GestionProfilUtilisateurAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1874477340570996712L;


	@Inject
	private ManagerFactory managerFactory;
	
	// ----- Paramètres
	private int id;
	private String civilite;
	private String nom;
	private String prenom;
	private String pseudo;
	private String adresseMail;
	private String telephone;
	private XMLGregorianCalendar dateNaissance;
	private String adresse;
	private String codePostal;
	private String ville;
	private String pays;
	private boolean validationFormulaire=false;
	
	private String ancienMotDePasse;
	private String nouveauMotDePasse;
	private String confirmationNouveauMotDePasse;
	
	private List<Emprunt> listEmprunt;
	private List<Emprunt> listEmpruntNonRendu=new ArrayList<>();
	private List<Emprunt> listEmpruntEnCours=new ArrayList<>();
	private List<Emprunt> listEmpruntRendu=new ArrayList<>();
	
	private boolean bEmpruntNonRendu=false;
	private boolean bEmpruntEnCours =false;
	private boolean bEmpruntRendu =false;
	
	// ----- Eléments Struts
	private Map<String, Object> session;

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(GestionProfilUtilisateurAction.class);
	
	
	// ===================== Getters/Setters =====================
	public boolean isValidationFormulaire() {
		return validationFormulaire;
	}

	public void setValidationFormulaire(boolean validationFormulaire) {
		this.validationFormulaire = validationFormulaire;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public XMLGregorianCalendar getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(XMLGregorianCalendar dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getAncienMotDePasse() {
		return ancienMotDePasse;
	}

	public void setAncienMotDePasse(String ancienMotDePasse) {
		this.ancienMotDePasse = ancienMotDePasse;
	}

	public String getNouveauMotDePasse() {
		return nouveauMotDePasse;
	}

	public void setNouveauMotDePasse(String nouveauMotDePasse) {
		this.nouveauMotDePasse = nouveauMotDePasse;
	}

	public String getConfirmationNouveauMotDePasse() {
		return confirmationNouveauMotDePasse;
	}

	public void setConfirmationNouveauMotDePasse(String confirmationNouveauMotDePasse) {
		this.confirmationNouveauMotDePasse = confirmationNouveauMotDePasse;
	}
	
	public List<Emprunt> getListEmpruntNonRendu() {
		return listEmpruntNonRendu;
	}

	public void setListEmpruntNonRendu(List<Emprunt> listEmpruntNonRendu) {
		this.listEmpruntNonRendu = listEmpruntNonRendu;
	}

	public List<Emprunt> getListEmpruntEnCours() {
		return listEmpruntEnCours;
	}

	public void setListEmpruntEnCours(List<Emprunt> listEmpruntEnCours) {
		this.listEmpruntEnCours = listEmpruntEnCours;
	}

	public List<Emprunt> getListEmpruntRendu() {
		return listEmpruntRendu;
	}

	public void setListEmpruntRendu(List<Emprunt> listEmpruntRendu) {
		this.listEmpruntRendu = listEmpruntRendu;
	}
	
	public boolean isbEmpruntNonRendu() {
		return bEmpruntNonRendu;
	}

	public void setbEmpruntNonRendu(boolean bEmpruntNonRendu) {
		this.bEmpruntNonRendu = bEmpruntNonRendu;
	}

	public boolean isbEmpruntEnCours() {
		return bEmpruntEnCours;
	}

	public void setbEmpruntEnCours(boolean bEmpruntEnCours) {
		this.bEmpruntEnCours = bEmpruntEnCours;
	}

	public boolean isbEmpruntRendu() {
		return bEmpruntRendu;
	}

	public void setbEmpruntRendu(boolean bEmpruntRendu) {
		this.bEmpruntRendu = bEmpruntRendu;
	}

	@Override
	public void setSession(Map<String, Object> pSession) {
		this.session=pSession;
	}
	
	public Map<String, Object> getSession() {
		return this.session;
	}
	
	
	// ===================== Méthodes =====================
	

	/**
	 * Méthode permettant de mettre à jour les informations
	 * relatives à un {@link Utilisateur}
	 * @return input / success
	 */
	public String doUpdateCoord() {
		LOGGER.info("GestionProfilUtilisateurAction - Méthode doUpdateCoord()");
		String vResult;
		
		LOGGER.info("Validation formulaire : "+validationFormulaire);
		
		//Récupération de la variable de session relative à l'utilisateur.
		Utilisateur vUtilisateurSession= (Utilisateur)this.session.get("user");
		LOGGER.info("Utilisateur Id : "+id);
		
		//Si le champ validationFormulaire est égal à false, c'est que l'on entre dans le formulaire.
		//Sinon, c'est que l'on vient de valider le formulaire de mise à jour des coordonnées.
		if(!validationFormulaire) {
			vResult=ActionSupport.INPUT;
			
			//Récupération des attributs de la variable de session relative à l'utilisateur.
			id=vUtilisateurSession.getId();
			civilite=vUtilisateurSession.getCivilite();
			nom=vUtilisateurSession.getNom();
			prenom=vUtilisateurSession.getPrenom();
			pseudo=vUtilisateurSession.getPseudo();
			adresseMail=vUtilisateurSession.getAdresseMail();
			telephone=vUtilisateurSession.getTelephone();
			dateNaissance=vUtilisateurSession.getDateNaissance();
			LOGGER.info("Date naissance : "+dateNaissance);
			adresse=vUtilisateurSession.getAdresse();
			codePostal=vUtilisateurSession.getCodePostal();
			ville=vUtilisateurSession.getVille();
			pays=vUtilisateurSession.getPays();
			
			validationFormulaire=true;
		}
		else {
			//Appel au web service. 
			LOGGER .info("Date de Naissance :"+dateNaissance);
			LOGGER .info("Pays :"+pays);
			try {
				managerFactory.getUtilisateurManager().updateCoordUtilisateur(id, civilite, nom, prenom, pseudo, adresseMail, telephone, dateNaissance, adresse, codePostal,
						ville, pays);
				
				//En cas de succès, on met à jour les attributs de la variable de session pour être cohérent avec la base de données.
				vUtilisateurSession.setCivilite(civilite);
				vUtilisateurSession.setNom(nom);
				vUtilisateurSession.setPrenom(prenom);
				vUtilisateurSession.setPseudo(pseudo);
				vUtilisateurSession.setAdresseMail(adresseMail);
				vUtilisateurSession.setTelephone(telephone);
				LOGGER.info("Date naissance : "+dateNaissance);
				vUtilisateurSession.setDateNaissance(dateNaissance);
				vUtilisateurSession.setAdresse(adresse);
				vUtilisateurSession.setCodePostal(codePostal);
				vUtilisateurSession.setVille(ville);
				vUtilisateurSession.setPays(pays);
				vResult=ActionSupport.SUCCESS;
			} catch (UpdateCoordUtilisateurFault_Exception e) {
				LOGGER.info(e.getMessage());
				this.addActionError(e.getMessage());
				//En cas d'échec, on affiche bien les valeurs précédentes.
				civilite=vUtilisateurSession.getCivilite();
				nom=vUtilisateurSession.getNom();
				prenom=vUtilisateurSession.getPrenom();
				pseudo=vUtilisateurSession.getPseudo();
				adresseMail=vUtilisateurSession.getAdresseMail();
				telephone=vUtilisateurSession.getTelephone();
				dateNaissance=vUtilisateurSession.getDateNaissance();
				LOGGER.info("Date naissance : "+dateNaissance);
				adresse=vUtilisateurSession.getAdresse();
				codePostal=vUtilisateurSession.getCodePostal();
				ville=vUtilisateurSession.getVille();
				pays=vUtilisateurSession.getPays();
				vResult=ActionSupport.INPUT;
			}		
		}
		
		LOGGER.info("Utilisateur Id : "+id);
		LOGGER.info("vResult : "+vResult);
		return vResult;
	}
	
	
	/**
	 * Méthode permettant de mettre à jour le mot de passe
	 * d'un {@link Utilisateur}
	 * @return input / success /error
	 */
	public String doUpdateMdp() {
		LOGGER.info("GestionProfilUtilisateurAction - Méthode doUpdateMdp()");
		String vResult;
		
		//Récupération de la variable de session relative à l'utilisateur.
		Utilisateur vUtilisateurSession= (Utilisateur)this.session.get("user");
		LOGGER.info("Utilisateur Id : "+id);
		
		if(ancienMotDePasse==null&&nouveauMotDePasse==null&&confirmationNouveauMotDePasse==null) {
			id=vUtilisateurSession.getId();
			vResult=ActionSupport.INPUT;
		}else {
			try {
				managerFactory.getUtilisateurManager().updateMdpUtilisateur(id, ancienMotDePasse, nouveauMotDePasse, confirmationNouveauMotDePasse);
				this.addActionMessage("La mise à jour de votre mot de passe a bien été prise en compte.");
				vResult=ActionSupport.SUCCESS;
			} catch (UpdateMdpUtilisateurFault_Exception e) {
				LOGGER.info(e.getMessage());
				this.addActionError(e.getMessage());
			
				if(e.getMessage().equals("Aucun utilisateur correspondant à l'id demandé.")) {
					vResult=ActionSupport.ERROR;
				}else {
					vResult=ActionSupport.INPUT;
				}
			}
		}
		
		return vResult;
	}
	
	/**
	 * Méthode permettant de visualiser les prêts effectués par un utilisateur
	 * @return success
	 */
	public String doGestionPret() {
		LOGGER.info("GestionProfilUtilisateurAction - Méthode doGestionPret()");
		
		//Récupération de la variable de session relative à l'utilisateur.
		Utilisateur vUtilisateurSession= (Utilisateur)this.session.get("user");
		id=vUtilisateurSession.getId();
		LOGGER.info("Utilisateur Id : "+id);
		try {
			listEmprunt=managerFactory.getEmpruntManager().gestionPret(id);
			
			for(Emprunt emprunt:listEmprunt) {
				if(emprunt.getStatutEmprunt().getStatutEmprunt().equals("Non rendu à temps")) {
					listEmpruntNonRendu.add(emprunt);
				}else if(emprunt.getStatutEmprunt().getStatutEmprunt().equals("En cours")) {
					listEmpruntEnCours.add(emprunt);
				}else {
					listEmpruntRendu.add(emprunt);
				}
			}
			
			if(listEmpruntNonRendu.size()==0) {
				bEmpruntNonRendu=true;
			}
				
			if(listEmpruntEnCours.size()==0) {
				bEmpruntEnCours=true;
			}
				
			if(listEmpruntRendu.size()==0) {
				bEmpruntRendu=true;
			}
				
		} catch (GestionPretFault_Exception e) {
			LOGGER.info(e.getMessage());
			bEmpruntNonRendu=true;
			bEmpruntEnCours=true;
			bEmpruntRendu=true;
		}
		
		return ActionSupport.SUCCESS;
	}
	
}