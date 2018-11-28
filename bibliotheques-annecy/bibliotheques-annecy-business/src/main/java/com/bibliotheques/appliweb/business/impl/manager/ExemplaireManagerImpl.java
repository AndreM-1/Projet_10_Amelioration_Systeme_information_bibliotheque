package com.bibliotheques.appliweb.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.business.contract.manager.ExemplaireManager;
import com.bibliotheques.appliweb.model.bean.edition.Exemplaire;
import com.bibliotheques.appliweb.model.exception.GetListExemplaireFault_Exception;

@Named
public class ExemplaireManagerImpl extends AbstractManager implements ExemplaireManager {
	
	private List<Exemplaire> listExemplaire = new ArrayList<>();
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ExemplaireManagerImpl.class);

	@Override
	public List<Exemplaire> getListExemplaire(int editionId) throws GetListExemplaireFault_Exception{
		LOGGER.info("Couche Business - Méthode getListExemplaire()");
		try {
			listExemplaire= getDaoFactory().getExemplaireDao().getListExemplaire(editionId);
		} catch (GetListExemplaireFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new GetListExemplaireFault_Exception(e.getMessage());
		}
		return listExemplaire;
	}
}