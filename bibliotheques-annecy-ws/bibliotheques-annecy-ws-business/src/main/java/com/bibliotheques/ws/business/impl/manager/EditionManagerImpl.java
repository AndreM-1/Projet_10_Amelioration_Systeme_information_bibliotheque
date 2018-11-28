package com.bibliotheques.ws.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.ws.business.contract.manager.EditionManager;
import com.bibliotheques.ws.model.bean.edition.Edition;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;

@Named
public class EditionManagerImpl extends AbstractManager implements EditionManager {
	
	private List<Edition> listEdition = new ArrayList<>();

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EditionManagerImpl.class);
	
	@Override
	public List<Edition> getListEdition(int nbEdition) {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListEdition()");
		LOGGER.info("Web Service : EditionService - Couche Business - dureeMaxEmprunt : "+getDureeMaxEmprunt());
		listEdition=getDaoFactory().getEditionDao().getListEdition(nbEdition);
		return this.listEdition;
	}
	
	@Override
	public List<Edition> rechercheEdition(String titre) throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode rechercheEdition()");
		try {
			LOGGER.info("Web Service : EditionService - Couche Business - Titre entré par l'utilisateur : "+titre);
			titre=titre.trim().replaceAll(" ", "").toLowerCase();
			LOGGER.info("Web Service : EditionService - Couche Business - Titre modifié pour la recherche en BDD : "+titre);
			listEdition=getDaoFactory().getEditionDao().rechercheEdition(titre);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		return this.listEdition;
	}
	
	@Override
	public List<Edition> rechercheAvanceeEdition(String titre, String nomAuteur, String nomEditeur,
			String anneeParution, String genre) throws NotFoundException,FunctionalException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode rechercheAvanceeEdition()");
		
		if(titre.trim().isEmpty())
				throw new FunctionalException("Le champ titre est obligatoire.");
			
		try {
			LOGGER.info("Web Service : EditionService - Couche Business - Titre entré par l'utilisateur : "+titre);
			LOGGER.info("Web Service : EditionService - Couche Business - Nom auteur entré par l'utilisateur : "+nomAuteur);
			
			titre=titre.trim().replaceAll(" ", "").toLowerCase();
			nomAuteur=nomAuteur.trim().replaceAll(" ", "").toLowerCase();
			
			LOGGER.info("Web Service : EditionService - Couche Business - Titre modifié pour la recherche en BDD : "+titre);
			LOGGER.info("Web Service : EditionService - Couche Business - Nom auteur modifié pour la recherche en BDD : "+nomAuteur);
			
			//Dans les listes déroulantes de la JSP relatives à l'éditeur, à l'année de publication et au genre, si on ne sélectionne rien, c'est à dire
			//que l'on garde l'en-tête par défaut, cela revient à avoir -1. Dans ce cas, c'est comme si les champs correspondants étaient non remplis.
			if (nomEditeur.equals("-1")) {
				nomEditeur="";
			}
			if (anneeParution.equals("-1")) {
				anneeParution="";
			}
			if (genre.equals("-1")) {
				genre="";
			}
			
			listEdition=getDaoFactory().getEditionDao().rechercheAvanceeEdition(titre, nomAuteur, nomEditeur, anneeParution, genre);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		return this.listEdition;
	}
}