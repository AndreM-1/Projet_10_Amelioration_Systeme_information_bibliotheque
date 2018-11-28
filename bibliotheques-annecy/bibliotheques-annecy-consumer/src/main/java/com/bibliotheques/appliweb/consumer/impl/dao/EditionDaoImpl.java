package com.bibliotheques.appliweb.consumer.impl.dao;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.consumer.contract.dao.EditionDao;
import com.bibliotheques.appliweb.model.bean.edition.Edition;
import com.bibliotheques.appliweb.model.exception.RechercheAvanceeEditionFault_Exception;
import com.bibliotheques.appliweb.model.exception.RechercheEditionFault_Exception;


@Named
public class EditionDaoImpl extends AbstractDaoImpl implements EditionDao{
	
	private List<Edition> listEdition=new ArrayList<>();
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EditionDaoImpl.class);

	
	@Override
	public List<Edition> getListEdition(int nbEdition){
		LOGGER.info("Couche Consumer - Méthode getListEdition()");
		listEdition=getEditionService().getListEdition(nbEdition);
    	LOGGER.info("Réponse du web service : "+listEdition);
		return listEdition;
	}
	
	@Override
	public List<Edition> rechercheEdition(String titre) throws RechercheEditionFault_Exception{
		LOGGER.info("Couche Consumer - Méthode rechercheEdition()");
		try {
			listEdition=getEditionService().rechercheEdition(titre);
		} catch (RechercheEditionFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new RechercheEditionFault_Exception(e.getMessage());
		}
		return listEdition;
	}
	
	@Override
	public List<Edition> rechercheAvanceeEdition(String titre, String nomAuteur, String nomEditeur, String anneeParution, String genre) 
			throws RechercheAvanceeEditionFault_Exception{
		LOGGER.info("Couche Consumer - Méthode rechercheAvanceeEdition()");
		try {
			listEdition=getEditionService().rechercheAvanceeEdition(titre, nomAuteur, nomEditeur, anneeParution, genre);
		} catch (RechercheAvanceeEditionFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new RechercheAvanceeEditionFault_Exception (e.getMessage());
		}
		return listEdition;
	}
}