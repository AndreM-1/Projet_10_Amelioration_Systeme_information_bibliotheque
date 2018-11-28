package com.bibliotheques.appliweb.consumer.impl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.consumer.contract.dao.ExemplaireDao;
import com.bibliotheques.appliweb.model.bean.edition.Exemplaire;
import com.bibliotheques.appliweb.model.exception.GetListExemplaireFault_Exception;

@Named
public class ExemplaireDaoImpl extends AbstractDaoImpl implements ExemplaireDao {
	
	private List<Exemplaire> listExemplaire = new ArrayList<>();
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ExemplaireDaoImpl.class);
	
	@Override
	public List<Exemplaire> getListExemplaire(int editionId) throws GetListExemplaireFault_Exception{
		LOGGER.info("Couche Consumer - Méthode getListExemplaire()");
		try {
			listExemplaire=getEditionService().getListExemplaire(editionId);
		} catch (GetListExemplaireFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new GetListExemplaireFault_Exception(e.getMessage());
		}
		return listExemplaire;
	}
}