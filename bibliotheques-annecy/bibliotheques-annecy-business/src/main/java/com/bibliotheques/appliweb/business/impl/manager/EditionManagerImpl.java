package com.bibliotheques.appliweb.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.business.contract.manager.EditionManager;
import com.bibliotheques.appliweb.model.bean.edition.Edition;
import com.bibliotheques.appliweb.model.exception.RechercheAvanceeEditionFault_Exception;
import com.bibliotheques.appliweb.model.exception.RechercheEditionFault_Exception;

@Named
public class EditionManagerImpl extends AbstractManager implements EditionManager {

	private List<Edition> listEdition = new ArrayList<>();

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EditionManagerImpl.class);

	@Override
	public List<Edition> getListEdition(int nbEdition) {
		LOGGER.info("Couche Business - Méthode getListEdition()");
		listEdition=getDaoFactory().getEditionDao().getListEdition(nbEdition);
		return this.listEdition;
	}

	@Override
	public List<Edition> rechercheEdition(String titre) throws RechercheEditionFault_Exception{
		LOGGER.info("Couche Business - Méthode rechercheEdition()");
		try {
			listEdition=getDaoFactory().getEditionDao().rechercheEdition(titre);
		} catch (RechercheEditionFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new RechercheEditionFault_Exception(e.getMessage());
		}
		return this.listEdition;
	}

	@Override
	public List<Edition> rechercheAvanceeEdition(String titre, String nomAuteur, String nomEditeur, String anneeParution, String genre) 
			throws RechercheAvanceeEditionFault_Exception{
		LOGGER.info("Couche Business - Méthode  rechercheAvanceeEdition()");
		try {
			listEdition=getDaoFactory().getEditionDao().rechercheAvanceeEdition(titre, nomAuteur, nomEditeur, anneeParution, genre);
		} catch (RechercheAvanceeEditionFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new RechercheAvanceeEditionFault_Exception(e.getMessage());
		}
		return this.listEdition;
	}
}