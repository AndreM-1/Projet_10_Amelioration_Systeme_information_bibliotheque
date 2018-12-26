
package com.bibliotheques.appliweb.model.exception;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-12-21T17:12:42.219+01:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "updateParametresUtilisateurFault", targetNamespace = "http://www.example.org/UtilisateurService/")
public class UpdateParametresUtilisateurFault_Exception extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8429802960707760185L;
	private UpdateParametresUtilisateurFault updateParametresUtilisateurFault;

    public UpdateParametresUtilisateurFault_Exception() {
        super();
    }

    public UpdateParametresUtilisateurFault_Exception(String message) {
        super(message);
    }

    public UpdateParametresUtilisateurFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public UpdateParametresUtilisateurFault_Exception(String message, UpdateParametresUtilisateurFault updateParametresUtilisateurFault) {
        super(message);
        this.updateParametresUtilisateurFault = updateParametresUtilisateurFault;
    }

    public UpdateParametresUtilisateurFault_Exception(String message, UpdateParametresUtilisateurFault updateParametresUtilisateurFault, java.lang.Throwable cause) {
        super(message, cause);
        this.updateParametresUtilisateurFault = updateParametresUtilisateurFault;
    }

    public UpdateParametresUtilisateurFault getFaultInfo() {
        return this.updateParametresUtilisateurFault;
    }
}
