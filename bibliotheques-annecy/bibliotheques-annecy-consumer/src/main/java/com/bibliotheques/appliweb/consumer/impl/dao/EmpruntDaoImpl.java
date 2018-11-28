package com.bibliotheques.appliweb.consumer.impl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.consumer.contract.dao.EmpruntDao;
import com.bibliotheques.appliweb.model.bean.edition.Emprunt;
import com.bibliotheques.appliweb.model.exception.EmprunterEditionFault_Exception;
import com.bibliotheques.appliweb.model.exception.GestionPretFault_Exception;
import com.bibliotheques.appliweb.model.exception.ProlongerEmpruntFault_Exception;

@Named
public class EmpruntDaoImpl extends AbstractDaoImpl implements EmpruntDao{
	
	private List<Emprunt> listEmprunt = new ArrayList<>();
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EmpruntDaoImpl.class);
	
	
	@Override
	public List<Emprunt> gestionPret(int utilisateurId) throws GestionPretFault_Exception{
		LOGGER.info("Couche Consumer - Méthode gestionPret()");
		try {
			listEmprunt=getEditionService().gestionPret(utilisateurId);
		} catch (GestionPretFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new GestionPretFault_Exception(e.getMessage());
		}
		return listEmprunt;
	}
	
	@Override
	public void emprunterEdition(int utilisateurId, int bibliothequeId, int editionId) throws EmprunterEditionFault_Exception {
		LOGGER.info("Couche Consumer - Méthode emprunterEdition()");
		try {
			getEditionService().emprunterEdition(utilisateurId, bibliothequeId, editionId);
		} catch (EmprunterEditionFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new EmprunterEditionFault_Exception(e.getMessage());
		}
	}
	
	@Override
	public void prolongerEmprunt(int utilisateurId, int bibliothequeId, int editionId) throws ProlongerEmpruntFault_Exception{
		LOGGER.info("Couche Consumer - Méthode prolongerEmprunt()");
		try {
			getEditionService().prolongerEmprunt(utilisateurId, bibliothequeId, editionId);
		} catch (ProlongerEmpruntFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new ProlongerEmpruntFault_Exception(e.getMessage());
		}
	}
}