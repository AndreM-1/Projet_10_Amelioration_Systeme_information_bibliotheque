
package com.bibliotheques.ws.webapp.editionservice.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-08-13T10:51:27.281+02:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "rechercheAvanceeEditionFault", targetNamespace = "http://www.example.org/EditionService/")
public class RechercheAvanceeEditionFault_Exception extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6005863196135065893L;
	private RechercheAvanceeEditionFault rechercheAvanceeEditionFault;

    public RechercheAvanceeEditionFault_Exception() {
        super();
    }

    public RechercheAvanceeEditionFault_Exception(String message) {
        super(message);
    }

    public RechercheAvanceeEditionFault_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public RechercheAvanceeEditionFault_Exception(String message, RechercheAvanceeEditionFault rechercheAvanceeEditionFault) {
        super(message);
        this.rechercheAvanceeEditionFault = rechercheAvanceeEditionFault;
    }

    public RechercheAvanceeEditionFault_Exception(String message, RechercheAvanceeEditionFault rechercheAvanceeEditionFault, java.lang.Throwable cause) {
        super(message, cause);
        this.rechercheAvanceeEditionFault = rechercheAvanceeEditionFault;
    }

    public RechercheAvanceeEditionFault getFaultInfo() {
        return this.rechercheAvanceeEditionFault;
    }
}
