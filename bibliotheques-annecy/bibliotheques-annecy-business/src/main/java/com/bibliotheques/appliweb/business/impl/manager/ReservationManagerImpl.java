package com.bibliotheques.appliweb.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.business.contract.manager.ReservationManager;
import com.bibliotheques.appliweb.model.bean.edition.Reservation;
import com.bibliotheques.appliweb.model.exception.AnnulerReservationFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListReservationFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListReservationUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.ReserverEditionFault_Exception;

@Named
public class ReservationManagerImpl extends AbstractManager implements ReservationManager{
	
	private List<Reservation> listReservation= new ArrayList<>();
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ReservationManagerImpl.class);
	
	@Override
	public List <Reservation> getListReservation(int utilisateurId, int bibliothequeId, int editionId, int nbExemplairesInit) throws GetListReservationFault_Exception{
		LOGGER.info("Couche Business - Méthode getListReservation()");
		try {
			listReservation=getDaoFactory().getReservationDao().getListReservation(utilisateurId, bibliothequeId, editionId, nbExemplairesInit);
		} catch (GetListReservationFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new GetListReservationFault_Exception(e.getMessage());
		}
		return listReservation;
	}
	
	@Override
	public void reserverEdition(int utilisateurId, int bibliothequeId, int editionId) throws ReserverEditionFault_Exception {
		LOGGER.info("Couche Business - Méthode reserverEdition()");
		try {
			getDaoFactory().getReservationDao().reserverEdition(utilisateurId, bibliothequeId, editionId);
		} catch (ReserverEditionFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new ReserverEditionFault_Exception(e.getMessage());
		}
	}
	
	@Override
	public List <Reservation> getListReservationUtilisateur(int utilisateurId) throws GetListReservationUtilisateurFault_Exception{
		LOGGER.info("Couche Business - Méthode getListReservationUtilisateur()");
		try {
			listReservation=getDaoFactory().getReservationDao().getListReservationUtilisateur(utilisateurId);
		} catch (GetListReservationUtilisateurFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new GetListReservationUtilisateurFault_Exception(e.getMessage());
		}
		return listReservation;
	}
	
	@Override
	public void annulerReservation(int utilisateurId, int bibliothequeId, int editionId) throws AnnulerReservationFault_Exception {
		LOGGER.info("Couche Business - Méthode annulerReservation()");
		try {
			getDaoFactory().getReservationDao().annulerReservation(utilisateurId, bibliothequeId, editionId);
		} catch (AnnulerReservationFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new AnnulerReservationFault_Exception(e.getMessage());
		}	
	}
}