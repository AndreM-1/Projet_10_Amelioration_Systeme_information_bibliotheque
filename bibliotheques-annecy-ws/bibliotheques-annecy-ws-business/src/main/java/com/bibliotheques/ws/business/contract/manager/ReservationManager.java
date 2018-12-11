package com.bibliotheques.ws.business.contract.manager;

import java.util.List;

import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

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
	 * @throws FunctionalException
	 */
	List<Reservation> getListReservation(int utilisateurId, int bibliothequeId, int editionId, int nbExemplairesInit) throws FunctionalException;

	/**
	 * Méthode permettant de réserver une édition, en ajoutant une ligne dans la table reservation correspondant à la réservation
	 * effectuée. A noter que cette méthode sera appelée dans l'un des cas suivants : soit on a une liste d'attente de réservations qui n'est
	 * pas complète, soit on a une liste d'attente vide.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws TechnicalException
	 */
	void reserverEdition(int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException;

	/**
	 * Méthode permettant de renvoyer la liste des réservations effectuée par un utilisateur.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @return List
	 * @throws NotFoundException
	 */
	List<Reservation> getListReservationUtilisateur(int utilisateurId) throws NotFoundException;

	
	/**
	 * Méthode permettant de supprimer une réservation en base de données et de mettre à jour le champ priorite_reservation.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws TechnicalException
	 */
	void annulerReservation(int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException;

	/**
	 * Méthode permettant de renvoyer la liste de l'ensemble des réservations effectué sur le réseau de bibliothèque.
	 * @return List
	 * @throws NotFoundException
	 */
	List<Reservation> getListAllReservation() throws NotFoundException;

	
	/**
	 * Méthode permettant de mettre à jour la table reservation et renvoyant une liste de réservation relatifs 
	 * aux utilisateurs qui doivent recevoir un mail leur indiquant qu'ils peuvent venir emprunter leur édition
	 * car l'utilisateur précédent n'est pas venu emprunter l'édition dans le délai de 48h.
	 * @return List
	 * @throws NotFoundException
	 * @throws TechnicalException
	 */
	List<Reservation> getListReservationUpdated() throws NotFoundException, TechnicalException;

	/**
	 * Méthode permettant de mettre à jour la table reservation et renvoyant une liste de réservation relatifs 
	 * aux utilisateurs qui doivent recevoir un mail leur indiquant qu'ils peuvent venir emprunter leur édition
	 * qui a été restituée le jour d'avant à la bibliothèque.
	 * @return List
	 * @throws NotFoundException
	 * @throws TechnicalException
	 */
	List<Reservation> getListReservationUpdatedRetourEmprunt() throws NotFoundException, TechnicalException;

}
