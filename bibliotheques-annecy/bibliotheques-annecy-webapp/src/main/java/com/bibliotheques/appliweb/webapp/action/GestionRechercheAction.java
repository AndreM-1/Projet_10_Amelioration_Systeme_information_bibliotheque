package com.bibliotheques.appliweb.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.business.contract.ManagerFactory;
import com.bibliotheques.appliweb.model.bean.edition.Edition;
import com.bibliotheques.appliweb.model.exception.RechercheAvanceeEditionFault_Exception;
import com.bibliotheques.appliweb.model.exception.RechercheEditionFault_Exception;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe d'action permettant de gérer la recherche d'une {@link Edition}
 * @author André Monnier
 *
 */
public class GestionRechercheAction extends ActionSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6835511641091538893L;
	
	
	@Inject
	private ManagerFactory managerFactory;
	
	// ----- Paramètres
	private String titre;
	private List<Edition> listEdition;
	private Boolean editionTrouve=false;
	private String nomAuteur;
	private String nomEditeur;
	private String anneeParution;
	private String genre;
	private List<String> listNomEditeur;
	private List<String> listAnneeParution;
	private List<String> listGenre;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(GestionRechercheAction.class);
	
	
	// ===================== Getters/Setters =====================
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Edition> getListEdition() {
		return listEdition;
	}


	public void setListEdition(List<Edition> listEdition) {
		this.listEdition = listEdition;
	}
	
	public Boolean getEditionTrouve() {
		return editionTrouve;
	}

	public void setEditionTrouve(Boolean editionTrouve) {
		this.editionTrouve = editionTrouve;
	}
	
	public String getNomAuteur() {
		return nomAuteur;
	}

	public void setNomAuteur(String nomAuteur) {
		this.nomAuteur = nomAuteur;
	}

	public String getNomEditeur() {
		return nomEditeur;
	}

	public void setNomEditeur(String nomEditeur) {
		this.nomEditeur = nomEditeur;
	}

	public String getAnneeParution() {
		return anneeParution;
	}

	public void setAnneeParution(String anneeParution) {
		this.anneeParution = anneeParution;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public List<String> getListNomEditeur() {
		return listNomEditeur;
	}

	public void setListNomEditeur(List<String> listNomEditeur) {
		this.listNomEditeur = listNomEditeur;
	}
	
	public List<String> getListAnneeParution() {
		return listAnneeParution;
	}

	public void setListAnneeParution(List<String> listAnneeParution) {
		this.listAnneeParution = listAnneeParution;
	}
	
	public List<String> getListGenre() {
		return listGenre;
	}

	public void setListGenre(List<String> listGenre) {
		this.listGenre = listGenre;
	}

	// ===================== Méthodes =====================

	/**
	 * Méthode permettant de récupérer une liste d'éditions
	 * suite au titre entré par l'utilisateur.
	 * @return success / error
	 */
	public String doRecherche() {
		LOGGER.info("GestionRechercheAction - Méthode doRecherche()");
		String vResult;
		LOGGER.info("Titre Edition : "+titre);
		if(titre==null) {
			this.addActionError("Vous n'avez pas accès à cette page.");
			vResult="error";
		}else {
			try {
				listEdition=managerFactory.getEditionManager().rechercheEdition(titre);
				editionTrouve=true;
				vResult=ActionSupport.SUCCESS;
			} catch (RechercheEditionFault_Exception e) {
				LOGGER.info(e.getMessage());
				this.addActionMessage(e.getMessage());
				vResult=ActionSupport.SUCCESS;
			}
		}
		return vResult;
	}
	
	/**
	 * Méthode permettant de récupérer une liste d'éditions
	 * en fonction de divers critères entrés par l'utilisateur.
	 * @return input/ success
	 */
	public String doRechercheAvancee() {
		LOGGER.info("GestionRechercheAction - Méthode doRechercheAvancee()");
		String vResult;
		LOGGER.info("Titre Edition : "+titre);
		LOGGER.info("Auteur: "+nomAuteur);
		LOGGER.info("Editeur : "+nomEditeur);
		LOGGER.info("Anneé de publication : "+anneeParution);
		LOGGER.info("Genre : "+genre);
		
		//Initialisation et remplissage des listes déroulantes.
		
		//listNomEditeur
		listNomEditeur=new ArrayList<>();
		String [] tabNomEditeur= {"Le Masque","Albert René","Dargaud","Kana","Glénat","Pocket","Gallimard","Pygmalion","Lgf","Flammarion","Eyrolles","Openclassrooms"};
		for (String str:tabNomEditeur) {
			listNomEditeur.add(str);
		}
		
		//listAnneeParution
		listAnneeParution=new ArrayList<>();
		for (int i=1975;i<=2018;i++) {
			listAnneeParution.add(String.valueOf(i));
		}
		
		//listGenre
		listGenre=new ArrayList<>();
		String [] tabGenre= {"Roman","BD","Manga","Fantasy","Fantastique","Science Fiction","Science"};
		for(String str:tabGenre) {
			listGenre.add(str);
		}
		
		//Première entrée dans le formulaire.
		//On peut tester uniquement la nullité du champ titre ou de tous les champs MAIS avec une relation logique OU de préférence
		//pour permettre de gérer le cas où l'utilisateur renseignerait n'importe quoi dans la barre d'adresse.
		if(titre==null || nomAuteur==null || nomEditeur==null || anneeParution==null || genre==null) {
			vResult=ActionSupport.INPUT;
		}else {
			try {
				listEdition=managerFactory.getEditionManager().rechercheAvanceeEdition(titre, nomAuteur, nomEditeur, anneeParution, genre);
				editionTrouve=true;
				vResult=ActionSupport.SUCCESS;
			} catch (RechercheAvanceeEditionFault_Exception e) {
				LOGGER.info(e.getMessage());
				if(e.getMessage().equals("Le champ titre est obligatoire.")) {
					this.addActionError(e.getMessage());
					vResult=ActionSupport.INPUT;
				}else {
					this.addActionMessage(e.getMessage());
					vResult=ActionSupport.SUCCESS;
				}
			}
			
		}
		
		return vResult;
		
	}
}