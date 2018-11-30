package com.bibliotheques.ws.business.impl.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bibliotheques.ws.business.contract.manager.ReservationManager;
import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

@Named
public class ReservationManagerImpl extends AbstractManager implements ReservationManager {
	
	private List<Reservation> listReservation;
	private Reservation reservation;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ReservationManagerImpl.class);

	@Override
	public List<Reservation> getListReservation(int utilisateurId, int bibliothequeId, int editionId,
			int nbExemplairesInit) throws FunctionalException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListReservation()");
		listReservation= new ArrayList<>();
		//On vérifie que l'utilisateur n'a pas déjà réservé l'édition.
		//Vu que l'on a définit un index unique sur les colonnes utilisateur_id et exemplaire_edition_id,
		//on n'a pas besoin du champ bibliothequeId.
		try {
			//Si l'utilisateur a déjà réservé l'édition, on lève une FunctionalException.
			reservation=getDaoFactory().getReservationDao().getReservation(utilisateurId, editionId);
			throw new FunctionalException("Vous avez déjà réservé cette édition dans une des bibliothèques de notre réseau.");
		} catch (NotFoundException e) {
			//Si l'utilisateur ne l'a pas déjà réservée, on vérifie que la liste d'attente des réservations n'est pas complète.
			//Si la liste d'attente n'est pas complète, on retourne la liste des réservations.
			LOGGER.info(e.getMessage());
			try {
				listReservation=getDaoFactory().getReservationDao().getListReservation(bibliothequeId,editionId);
				
				if(listReservation.size()>=2*nbExemplairesInit) {
					//Cas où la liste d'attente est complète, on lève donc une FunctionalException.
					throw new FunctionalException("Désolé, mais la liste d'attente des réservations pour cette édition est complète.");
				}else {
					//Cas où la liste d'attente n'est pas complète.
					return listReservation;
				}
			} catch (NotFoundException e1) {
				//Dans ce cas là, personne n'a réservé l'édition dans une bibliothèque donnée, donc c'est bon, la liste d'attente est vide.
				//On retourne une liste de réservation vide.
				LOGGER.info(e1.getMessage());
				LOGGER.info("Liste réservations :"+listReservation);
				return listReservation;
			}	
		}
	}
	
	
	@Override
	public void reserverEdition(int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode reserverEdition()");
		
		//Utilisation d'un TransactionStatus. On a besoin de lever une TechnicalException,
		//ce qui n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		
		//A ce stade, soit on a une liste d'attente de réservations qui n'est pas complète, soit on a une liste d'attente vide.
		//Dans les 2 cas, on peut effectuer une réservation.
		int prioriteReservation=1;
		Date dateReservation= new Date();
		try {
			listReservation=getDaoFactory().getReservationDao().getListReservation(bibliothequeId,editionId);
			prioriteReservation=listReservation.size()+1;
			try {
				getDaoFactory().getReservationDao().insertReservation(dateReservation,utilisateurId,bibliothequeId,editionId,prioriteReservation);
				getPlatformTransactionManager().commit(vTransactionStatus);
			} catch (TechnicalException e) {
				LOGGER.info(e.getMessage());
				getPlatformTransactionManager().rollback(vTransactionStatus);
				throw new TechnicalException(e.getMessage());
			}
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			try {
				getDaoFactory().getReservationDao().insertReservation(dateReservation,utilisateurId,bibliothequeId,editionId,prioriteReservation);
				getPlatformTransactionManager().commit(vTransactionStatus);
			} catch (TechnicalException e1) {
				LOGGER.info(e1.getMessage());
				getPlatformTransactionManager().rollback(vTransactionStatus);
				throw new TechnicalException(e1.getMessage());
			}
		}	
	}
	
	@Override
	public List<Reservation> getListReservationUtilisateur(int utilisateurId) throws NotFoundException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListReservationUtilisateur()");
		listReservation= new ArrayList<>();
		try {
			listReservation= getDaoFactory().getReservationDao().getListReservationUtilisateur(utilisateurId);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		return listReservation;
	}
	
	@Override
	public void annulerReservation(int utilisateurId,int bibliothequeId,int editionId) throws TechnicalException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode annulerReservation()");
		
		//Utilisation d'un TransactionStatus. On a besoin de lever une TechnicalException,
		//ce qui n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		
		//Tout d'abord, on supprime la réservation de la base de données
		try {
			getDaoFactory().getReservationDao().deleteReservation(utilisateurId,bibliothequeId,editionId);	
		} catch (TechnicalException e) {
			LOGGER.info(e.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new TechnicalException(e.getMessage());
		}
		
		//On récupère la liste des réservations d'une édition dans une bibliothèque.
		listReservation= new ArrayList<>();
		
		try {
			//On récupère une liste qui a minimum 1 élément.
			listReservation=getDaoFactory().getReservationDao().getListReservation(bibliothequeId,editionId);
			int prioriteReservationMax=listReservation.size();
			
			//On met à jour le champ priorite_reservation.
			for(int prioriteReservation=1;prioriteReservation<=prioriteReservationMax;prioriteReservation++) {
				try {
					getDaoFactory().getReservationDao().updateReservation(listReservation.get(prioriteReservation-1).getId(),prioriteReservation);
				} catch (TechnicalException e) {
					LOGGER.info(e.getMessage());
					getPlatformTransactionManager().rollback(vTransactionStatus);
					throw new TechnicalException(e.getMessage());
				}
			}	
		} catch (NotFoundException e) {
			//Dans ce là, il n'y a rien à faire, pas besoin de mettre à jour le champ priorite_reservation.
			LOGGER.info(e.getMessage());
		}
		
		getPlatformTransactionManager().commit(vTransactionStatus);
	}
	
	@Override
	public List<Reservation> getListAllReservation() throws NotFoundException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListAllReservation()");
		listReservation= new ArrayList<>();
		try {
			listReservation= getDaoFactory().getReservationDao().getListAllReservation();
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		return listReservation;
	}
}
