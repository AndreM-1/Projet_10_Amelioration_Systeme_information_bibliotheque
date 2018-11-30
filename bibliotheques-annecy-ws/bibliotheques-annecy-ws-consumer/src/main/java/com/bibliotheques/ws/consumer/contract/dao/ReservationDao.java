package com.bibliotheques.ws.consumer.contract.dao;

import java.util.Date;
import java.util.List;

import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

/**
 * Interface ReservationDao
 * @author André Monnier
 *
 */
public interface ReservationDao {
	
	/**
	 * Méthode permettant de renvoyer la réservation d'une édition effectuée par un utilisateur.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param editionId : L'identifiant de l'édition
	 * @return Un objet de type {@link Reservation}
	 * @throws NotFoundException
	 */
	Reservation getReservation(int utilisateurId,int editionId) throws NotFoundException;

	/**
	 * Méthode permettant de renvoyer la liste des réservations d'une édition dans une bibliothèque.
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @return List
	 * @throws NotFoundException
	 */
	List<Reservation> getListReservation(int bibliothequeId, int editionId) throws NotFoundException;

	/**
	 * Méthode permettant d'enregistrer en BDD la réservation effectuée par un utilisateur.
	 * @param dateReservation : La date à laquelle a été effectuée la réservation
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @param prioriteReservation : La priorité de la réservation
	 * @throws TechnicalException
	 */
	void insertReservation(Date dateReservation, int utilisateurId, int bibliothequeId, int editionId, int prioriteReservation)
			throws TechnicalException;

	/**
	 * Méthode permettant de renvoyer la liste des réservations effectuée par un utilisateur.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @return List
	 * @throws NotFoundException
	 */
	List<Reservation> getListReservationUtilisateur(int utilisateurId) throws NotFoundException;

	/**
	 * Méthode permettant de supprimer une réservation en base de données.
	 * @param utilisateurId : L'identifiant de l'utilisateur
	 * @param bibliothequeId : L'identifiant de la bibliothèque
	 * @param editionId : L'identifiant de l'édition
	 * @throws TechnicalException
	 */
	void deleteReservation(int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException;

	
	/**
	 * Méthode permettant de mettre à jour le champ priorite_reservation à partir de l'id de la réservation.
	 * @param id : L'id de la réservation
	 * @param prioriteReservation : La priorité de la réservation
	 * @throws TechnicalException
	 */
	void updateReservation(int id, int prioriteReservation) throws TechnicalException;

	/**
	 * Méthode permettant de renvoyer la liste de l'ensemble des réservations effectué sur le réseau de bibliothèque.
	 * @return List
	 * @throws NotFoundException
	 */
	List<Reservation> getListAllReservation() throws NotFoundException;

}