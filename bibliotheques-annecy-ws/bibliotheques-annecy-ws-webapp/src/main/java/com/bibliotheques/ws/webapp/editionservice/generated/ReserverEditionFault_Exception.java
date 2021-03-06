
package com.bibliotheques.ws.webapp.editionservice.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-12-04T10:23:48.190+01:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "reserverEditionFault", targetNamespace = "http://www.example.org/EditionService/")
public class ReserverEditionFault_Exception extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3568163905317262796L;
	private ReserverEditionFault reserverEditionFault;

    public ReserverEditionFault_Exception() {
        super();
    }

    public ReserverEditionFault_Exception(String message) {
        super(message);
    }

    public ReserverEditionFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public ReserverEditionFault_Exception(String message, ReserverEditionFault reserverEditionFault) {
        super(message);
        this.reserverEditionFault = reserverEditionFault;
    }

    public ReserverEditionFault_Exception(String message, ReserverEditionFault reserverEditionFault, java.lang.Throwable cause) {
        super(message, cause);
        this.reserverEditionFault = reserverEditionFault;
    }

    public ReserverEditionFault getFaultInfo() {
        return this.reserverEditionFault;
    }
}
