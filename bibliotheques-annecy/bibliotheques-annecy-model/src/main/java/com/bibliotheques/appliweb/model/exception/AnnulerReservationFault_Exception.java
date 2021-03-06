
package com.bibliotheques.appliweb.model.exception;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-12-06T16:49:27.700+01:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "annulerReservationFault", targetNamespace = "http://www.example.org/EditionService/")
public class AnnulerReservationFault_Exception extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3400185349857582225L;
	private AnnulerReservationFault annulerReservationFault;

    public AnnulerReservationFault_Exception() {
        super();
    }

    public AnnulerReservationFault_Exception(String message) {
        super(message);
    }

    public AnnulerReservationFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public AnnulerReservationFault_Exception(String message, AnnulerReservationFault annulerReservationFault) {
        super(message);
        this.annulerReservationFault = annulerReservationFault;
    }

    public AnnulerReservationFault_Exception(String message, AnnulerReservationFault annulerReservationFault, java.lang.Throwable cause) {
        super(message, cause);
        this.annulerReservationFault = annulerReservationFault;
    }

    public AnnulerReservationFault getFaultInfo() {
        return this.annulerReservationFault;
    }
}
