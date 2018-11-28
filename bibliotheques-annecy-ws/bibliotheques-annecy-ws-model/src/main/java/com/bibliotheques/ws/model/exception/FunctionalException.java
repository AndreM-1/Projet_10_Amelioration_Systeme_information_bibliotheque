package com.bibliotheques.ws.model.exception;

/**
 * Classe d'exception levée quand une erreur fonctionnelle survient
 * (champs obligatoires non remplis ou pas remplis correctement,
 * informations à caractère unique déjà renseignées en BDD par exemple)
 * @author André Monnier
 */
public class FunctionalException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * Constructeur.
     *
     * @param pMessage -
     */
    public FunctionalException(String pMessage) {
        super(pMessage);
    }


    /**
     * Constructeur.
     *
     * @param pMessage -
     * @param pCause -
     */
    public FunctionalException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }
}
