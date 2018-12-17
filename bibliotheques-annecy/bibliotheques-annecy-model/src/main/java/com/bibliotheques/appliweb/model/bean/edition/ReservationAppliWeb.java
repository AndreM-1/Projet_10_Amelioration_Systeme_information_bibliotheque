package com.bibliotheques.appliweb.model.bean.edition;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Classe du modèle lié au bean ReservationAppliWeb
 * @author André Monnier
 *
 */
public class ReservationAppliWeb {

	// ==================== Attributs ====================
	protected Reservation reservation;
	protected XMLGregorianCalendar dateRetour;
	protected String notificationDate;
   
	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 */
	public ReservationAppliWeb() {

	}    
    // ==================== Getters/Setters ====================
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public XMLGregorianCalendar getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(XMLGregorianCalendar dateRetour) {
		this.dateRetour = dateRetour;
	}
	
    public String getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}
}