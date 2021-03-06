
package com.bibliotheques.ws.batch.generated.editionservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-12-12T09:25:39.857+01:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "getListReservationFault", targetNamespace = "http://www.example.org/EditionService/")
public class GetListReservationFault_Exception extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8286735086272460044L;
	private GetListReservationFault getListReservationFault;

    public GetListReservationFault_Exception() {
        super();
    }

    public GetListReservationFault_Exception(String message) {
        super(message);
    }

    public GetListReservationFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public GetListReservationFault_Exception(String message, GetListReservationFault getListReservationFault) {
        super(message);
        this.getListReservationFault = getListReservationFault;
    }

    public GetListReservationFault_Exception(String message, GetListReservationFault getListReservationFault, java.lang.Throwable cause) {
        super(message, cause);
        this.getListReservationFault = getListReservationFault;
    }

    public GetListReservationFault getFaultInfo() {
        return this.getListReservationFault;
    }
}
