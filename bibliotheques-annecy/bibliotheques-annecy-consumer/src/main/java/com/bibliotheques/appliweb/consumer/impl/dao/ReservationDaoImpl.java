package com.bibliotheques.appliweb.consumer.impl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.consumer.contract.dao.ReservationDao;
import com.bibliotheques.appliweb.model.bean.edition.Reservation;
import com.bibliotheques.appliweb.model.exception.AnnulerReservationFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListReservationFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListReservationUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.ReserverEditionFault_Exception;

@Named
public class ReservationDaoImpl extends AbstractDaoImpl implements ReservationDao{
	
	private List<Reservation> listReservation= new ArrayList<>();
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ReservationDaoImpl.class);
	
	@Override
	public List <Reservation> getListReservation(int utilisateurId, int bibliothequeId, int editionId, int nbExemplairesInit) throws GetListReservationFault_Exception{
		LOGGER.info("Couche Consumer - Méthode getListReservation()");
		try {
			listReservation=getEditionService().getListReservation(utilisateurId, bibliothequeId, editionId, nbExemplairesInit);
		} catch (GetListReservationFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new GetListReservationFault_Exception(e.getMessage());
		}
		return listReservation;
	}
	
	@Override
	public void reserverEdition(int utilisateurId, int bibliothequeId, int editionId) throws ReserverEditionFault_Exception{
		LOGGER.info("Couche Consumer - Méthode reserverEdition()");
		try {
			getEditionService().reserverEdition(utilisateurId, bibliothequeId, editionId);
		} catch (ReserverEditionFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new ReserverEditionFault_Exception(e.getMessage());
		}
	}
	
	@Override
	public List <Reservation> getListReservationUtilisateur(int utilisateurId) throws GetListReservationUtilisateurFault_Exception{
		LOGGER.info("Couche Consumer - Méthode getListReservationUtilisateur()");
		try {
			listReservation=getEditionService().getListReservationUtilisateur(utilisateurId);
		} catch (GetListReservationUtilisateurFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new GetListReservationUtilisateurFault_Exception(e.getMessage());
		}
		return listReservation;
	}
	
	@Override
	public void annulerReservation(int utilisateurId, int bibliothequeId, int editionId) throws AnnulerReservationFault_Exception {
		LOGGER.info("Couche Consumer - Méthode annulerReservation()");
		try {
			getEditionService().annulerReservation(utilisateurId, bibliothequeId, editionId);
		} catch (AnnulerReservationFault_Exception e) {
			LOGGER.info(e.getMessage());
			throw new AnnulerReservationFault_Exception(e.getMessage());
		}
	}
}
