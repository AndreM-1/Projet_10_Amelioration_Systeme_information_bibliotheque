package com.bibliotheques.appliweb.model.exception;

/**
 * Classe d'exception levée quand une erreur technique est survenue
 *
 * @author André Monnier
 */
public class TechnicalException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * Constructeur.
     *
     * @param pMessage -
     */
    public TechnicalException(String pMessage) {
        super(pMessage);
    }


    /**
     * Constructeur.
     *
     * @param pMessage -
     * @param pCause -
     */
    public TechnicalException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }
}
