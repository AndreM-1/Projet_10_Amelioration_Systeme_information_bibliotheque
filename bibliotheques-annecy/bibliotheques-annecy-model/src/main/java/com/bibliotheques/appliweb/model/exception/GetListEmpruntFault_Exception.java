
package com.bibliotheques.appliweb.model.exception;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-12-06T16:49:27.649+01:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "getListEmpruntFault", targetNamespace = "http://www.example.org/EditionService/")
public class GetListEmpruntFault_Exception extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8072846886532340450L;
	private GetListEmpruntFault getListEmpruntFault;

    public GetListEmpruntFault_Exception() {
        super();
    }

    public GetListEmpruntFault_Exception(String message) {
        super(message);
    }

    public GetListEmpruntFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public GetListEmpruntFault_Exception(String message, GetListEmpruntFault getListEmpruntFault) {
        super(message);
        this.getListEmpruntFault = getListEmpruntFault;
    }

    public GetListEmpruntFault_Exception(String message, GetListEmpruntFault getListEmpruntFault, java.lang.Throwable cause) {
        super(message, cause);
        this.getListEmpruntFault = getListEmpruntFault;
    }

    public GetListEmpruntFault getFaultInfo() {
        return this.getListEmpruntFault;
    }
}