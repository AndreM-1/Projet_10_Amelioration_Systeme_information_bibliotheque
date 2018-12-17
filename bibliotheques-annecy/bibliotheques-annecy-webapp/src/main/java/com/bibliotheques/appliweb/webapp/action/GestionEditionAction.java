package com.bibliotheques.appliweb.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.bibliotheques.appliweb.business.contract.ManagerFactory;
import com.bibliotheques.appliweb.model.bean.edition.Edition;
import com.bibliotheques.appliweb.model.bean.edition.Emprunt;
import com.bibliotheques.appliweb.model.bean.edition.Exemplaire;
import com.bibliotheques.appliweb.model.bean.edition.Reservation;
import com.bibliotheques.appliweb.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.appliweb.model.exception.AnnulerReservationFault_Exception;
import com.bibliotheques.appliweb.model.exception.EmprunterEditionFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListEmpruntFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListExemplaireFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListReservationFault_Exception;
import com.bibliotheques.appliweb.model.exception.ProlongerEmpruntFault_Exception;
import com.bibliotheques.appliweb.model.exception.ReserverEditionFault_Exception;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe d'action relative à la gestion des éditions.
 * @author André Monnier
 */
public class GestionEditionAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -43521009919435581L;

	@Inject
	private ManagerFactory managerFactory;

	// ----- Paramètres
	private List<Edition> listEdition;
	private String moisCourant;
	private String anneeCourante;
	private List<Exemplaire> listExemplaire;
	private int id;
	private List<Reservation> listReservation;
	private List<Emprunt> listEmprunt;
	private int bibliothequeId;
	private int editionId;
	private String notification;
	private int nbExemplairesInit;
	private int nbReservation;

	// ----- Eléments Struts
	private Map<String, Object> session;

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(GestionEditionAction.class);

	// ===================== Getters/Setters =====================
	public List<Edition> getListEdition() {
		return listEdition;
	}

	public void setListEdition(List<Edition> listEdition) {
		this.listEdition = listEdition;
	}

	public String getMoisCourant() {
		return moisCourant;
	}

	public void setMoisCourant(String moisCourant) {
		this.moisCourant = moisCourant;
	}

	public String getAnneeCourante() {
		return anneeCourante;
	}

	public void setAnneeCourante(String anneeCourante) {
		this.anneeCourante = anneeCourante;
	}

	public List<Exemplaire> getListExemplaire() {
		return listExemplaire;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBibliothequeId() {
		return bibliothequeId;
	}

	public void setBibliothequeId(int bibliothequeId) {
		this.bibliothequeId = bibliothequeId;
	}

	public int getEditionId() {
		return editionId;
	}

	public void setEditionId(int editionId) {
		this.editionId = editionId;
	}

	public List<Emprunt> getListEmprunt() {
		return listEmprunt;
	}

	public String getNotification() {
		return notification;
	}

	public List<Reservation> getListReservation() {
		return listReservation;
	}

	public void setNbExemplairesInit(int nbExemplairesInit) {
		this.nbExemplairesInit = nbExemplairesInit;
	}
	
	public int getNbReservation() {
		return nbReservation;
	}

	@Override
	public void setSession(Map<String, Object> pSession) {
		this.session=pSession;
	}

	/**
	 * Méthode permettant de renvoyer une liste d'éditions
	 * qui seront affichées sur la page d'accueil
	 * @return success
	 */
	public String doList() {
		LOGGER.info("GestionEditionAction - Méthode doList()");
		Date date =new Date();
		DateFormat dfMois = new SimpleDateFormat("MMMMMMMMM");
		DateFormat dfAnnee = new SimpleDateFormat("yyyy");
		moisCourant=dfMois.format(date);
		anneeCourante=dfAnnee.format(date);
		listEdition=managerFactory.getEditionManager().getListEdition(12);
		return ActionSupport.SUCCESS;
	}

	/**
	 * Méthode permettant d'afficher le détail d'une {@link Edition}
	 * avec notamment le nombre d'exemplaires disponibles par bibliothèque
	 * @return success /error
	 */
	public String doDetail() {
		LOGGER.info("GestionEditionAction - Méthode doDetail()");
		String vResult;

		try {
			listExemplaire=managerFactory.getExemplaireManager().getListExemplaire(id);
			vResult=ActionSupport.SUCCESS;
		} catch (GetListExemplaireFault_Exception e) {
			LOGGER.info(e.getMessage());
			this.addActionError(e.getMessage());
			vResult=ActionSupport.ERROR;
		}
		return vResult;
	}

	/**
	 * Méthode permettant d'emprunter une {@link Edition}
	 * @return success /error
	 */
	public String doEmprunt() {
		LOGGER.info("GestionEditionAction - Méthode doEmprunt()");
		String vResult;
		//Récupération de la variable de session relative à l'utilisateur.
		Utilisateur vUtilisateurSession= (Utilisateur)this.session.get("user");
		LOGGER.info("Utilisateur Id : "+vUtilisateurSession.getId());
		LOGGER.info("Bibliothèque Id : "+bibliothequeId);
		LOGGER.info("Edition Id : "+editionId);

		try {
			managerFactory.getEmpruntManager().emprunterEdition(vUtilisateurSession.getId(),bibliothequeId,editionId);
			vResult=ActionSupport.SUCCESS;
		} catch (EmprunterEditionFault_Exception e) {
			LOGGER.info(e.getMessage());
			this.addActionError(e.getMessage());
			vResult=ActionSupport.ERROR;
		}
		return vResult;
	}

	/**
	 * Méthode permettant de prolonger l'emprunt d'une {@link Edition}
	 * @return success /error
	 */
	public String doProlongerEmprunt() {
		LOGGER.info("GestionEditionAction - Méthode doProlongerEmprunt()");
		String vResult;
		//Récupération de la variable de session relative à l'utilisateur.
		Utilisateur vUtilisateurSession= (Utilisateur)this.session.get("user");
		LOGGER.info("Utilisateur Id : "+vUtilisateurSession.getId());
		LOGGER.info("Bibliothèque Id : "+bibliothequeId);
		LOGGER.info("Edition Id : "+editionId);

		try {
			managerFactory.getEmpruntManager().prolongerEmprunt(vUtilisateurSession.getId(),bibliothequeId,editionId);
			vResult=ActionSupport.SUCCESS;
		} catch (ProlongerEmpruntFault_Exception e) {
			LOGGER.info(e.getMessage());
			this.addActionError(e.getMessage());
			vResult=ActionSupport.ERROR;
		}
		return vResult;
	}

	/**
	 * Méthode permettant d'afficher des informations détaillées sur l'{@link Edition} à réserver
	 * @return success /error
	 */
	public String doReservation() {
		LOGGER.info("GestionEditionAction - Méthode doReservation()");
		String vResult;
		//Récupération de la variable de session relative à l'utilisateur.
		Utilisateur vUtilisateurSession= (Utilisateur)this.session.get("user");
		LOGGER.info("Utilisateur Id : "+vUtilisateurSession.getId());
		LOGGER.info("Bibliothèque Id : "+bibliothequeId);
		LOGGER.info("Edition Id : "+editionId);
		LOGGER.info("Nb Exemplaires Init: "+nbExemplairesInit);

		try {
			listEmprunt=managerFactory.getEmpruntManager().getListEmprunt(vUtilisateurSession.getId(),bibliothequeId, editionId);
			LOGGER.info(listEmprunt.size());
			if(listEmprunt.get(0).getStatutEmprunt().getId()==2) {
				notification="(En attente du retour de l'utilisateur)";
			}
			try {
				listReservation=managerFactory.getReservationManager().getListReservation(vUtilisateurSession.getId(), bibliothequeId, editionId, nbExemplairesInit);
				LOGGER.info("Liste Reservation :"+listReservation);
				if(listReservation==null) {
					nbReservation=0;
				}else {
					nbReservation=listReservation.size();
				}
				vResult=ActionSupport.SUCCESS;
			} catch (GetListReservationFault_Exception e) {
				LOGGER.info(e.getMessage());
				this.addActionError(e.getMessage());
				vResult=ActionSupport.ERROR;
			}

		} catch (GetListEmpruntFault_Exception e) {
			LOGGER.info(e.getMessage());
			this.addActionError(e.getMessage());
			vResult=ActionSupport.ERROR;
		}
		return vResult;
	}
	
	/**
	 * Méthode permettant de confirmer la réservation de l'{@link Edition}
	 * @return success /error
	 */
	public String doConfirmerReservation() {
		LOGGER.info("GestionEditionAction - Méthode doConfirmerReservation()");
		String vResult;
		//Récupération de la variable de session relative à l'utilisateur.
		Utilisateur vUtilisateurSession= (Utilisateur)this.session.get("user");
		LOGGER.info("Utilisateur Id : "+vUtilisateurSession.getId());
		LOGGER.info("Bibliothèque Id : "+bibliothequeId);
		LOGGER.info("Edition Id : "+editionId);
		LOGGER.info("Nb Exemplaires Init: "+nbExemplairesInit);
		
		try {
			listEmprunt=managerFactory.getEmpruntManager().getListEmprunt(vUtilisateurSession.getId(),bibliothequeId, editionId);
			LOGGER.info(listEmprunt.size());
			if(listEmprunt.get(0).getStatutEmprunt().getId()==2) {
				notification="(En attente du retour de l'utilisateur)";
			}
			try {
				listReservation=managerFactory.getReservationManager().getListReservation(vUtilisateurSession.getId(), bibliothequeId, editionId, nbExemplairesInit);
				LOGGER.info("Liste Reservation :"+listReservation);
				try {
					managerFactory.getReservationManager().reserverEdition(vUtilisateurSession.getId(), bibliothequeId, editionId);
					vResult=ActionSupport.SUCCESS;
				} catch (ReserverEditionFault_Exception e) {
					LOGGER.info(e.getMessage());
					this.addActionError(e.getMessage());
					vResult=ActionSupport.ERROR;
				}
				
			} catch (GetListReservationFault_Exception e) {
				LOGGER.info(e.getMessage());
				this.addActionError(e.getMessage());
				vResult=ActionSupport.ERROR;
			}

		} catch (GetListEmpruntFault_Exception e) {
			LOGGER.info(e.getMessage());
			this.addActionError(e.getMessage());
			vResult=ActionSupport.ERROR;
		}
		return vResult;
	}
	
	/**
	 * Méthode permettant d'annuler une réservation
	 * @return success /error
	 */
	public String doAnnulerReservation() {
		LOGGER.info("GestionEditionAction - Méthode doAnnulerReservation()");
		String vResult;
		//Récupération de la variable de session relative à l'utilisateur.
		Utilisateur vUtilisateurSession= (Utilisateur)this.session.get("user");
		LOGGER.info("Utilisateur Id : "+vUtilisateurSession.getId());
		LOGGER.info("Bibliothèque Id : "+bibliothequeId);
		LOGGER.info("Edition Id : "+editionId);
		
		try {
			managerFactory.getReservationManager().annulerReservation(vUtilisateurSession.getId(), bibliothequeId, editionId);
			vResult=ActionSupport.SUCCESS;
		} catch (AnnulerReservationFault_Exception e) {
			LOGGER.info(e.getMessage());
			this.addActionError(e.getMessage());
			vResult=ActionSupport.ERROR;
		}	
		return vResult;
	}
}