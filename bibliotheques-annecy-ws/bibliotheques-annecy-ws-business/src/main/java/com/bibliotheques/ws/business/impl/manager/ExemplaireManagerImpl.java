package com.bibliotheques.ws.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.ws.business.contract.manager.ExemplaireManager;
import com.bibliotheques.ws.model.bean.edition.Exemplaire;
import com.bibliotheques.ws.model.exception.NotFoundException;

@Named
public class ExemplaireManagerImpl extends AbstractManager implements ExemplaireManager{
	
	private List<Exemplaire> listExemplaire= new ArrayList<>();
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ExemplaireManagerImpl.class);

	@Override
	public List<Exemplaire> getListExemplaire(int editionId) throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListExemplaire()");
		
		try {
			listExemplaire=getDaoFactory().getExemplaireDao().getListExemplaire(editionId);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		
		return listExemplaire;
	}
}
