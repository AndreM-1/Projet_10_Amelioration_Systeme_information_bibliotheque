package com.bibliotheques.appliweb.business.contract.manager;

import java.util.List;

import com.bibliotheques.appliweb.model.bean.edition.Reservation;
import com.bibliotheques.appliweb.model.exception.AnnulerReservationFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListReservationFault_Exception;
import com.bibliotheques.appliweb.model.exception.GetListReservationUtilisateurFault_Exception;
import com.bibliotheques.appliweb.model.exception.ReserverEditionFault_Exception;

/**
 * Interface ReservationManager
 * @author André Monnier
 *
 */
public interface ReservationManager {

	/**
	 * Méthode permettant de renvoyer la liste des réservations d'une édition dans une bibliothèque si l'utilisateur ne l'a pas déjà réservée
	 * et si la liste d'attente des réservations n'est pas complète. A noter que cette liste de réservations peut être vide dans le cas où
	 * personne n'a réservée l'édition dans une bibliothèque donnée.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @param nbExemplairesInit : Le nombre d'exemplaires initial
	 * @return List
	 * @throws GetListReservationFault_Exception
	 */
	List <Reservation> getListReservation(int utilisateurId, int bibliothequeId, int editionId, int nbExemplairesInit)
			throws GetListReservationFault_Exception;

	/**
	 * Méthode permettant de réserver une édition, en ajoutant une ligne dans la table reservation correspondant à la réservation
	 * effectuée. A noter que cette méthode sera appelée dans l'un des cas suivants : soit on a une liste d'attente de réservations qui n'est
	 * pas complète, soit on a une liste d'attente vide.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws ReserverEditionFault_Exception
	 */
	void reserverEdition(int utilisateurId, int bibliothequeId, int editionId) throws ReserverEditionFault_Exception;

	/**
	 * Méthode permettant de renvoyer la liste des réservations effectuée par un utilisateur.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @return List
	 * @throws GetListReservationUtilisateurFault_Exception
	 */
	List <Reservation> getListReservationUtilisateur(int utilisateurId) throws GetListReservationUtilisateurFault_Exception;

	/**
	 * Méthode permettant de supprimer une réservation en base de données et de mettre à jour le champ priorite_reservation.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws AnnulerReservationFault_Exception
	 */
	void annulerReservation(int utilisateurId, int bibliothequeId, int editionId) throws AnnulerReservationFault_Exception;

}