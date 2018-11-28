package com.bibliotheques.ws.business.impl.manager;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.transaction.PlatformTransactionManager;

import com.bibliotheques.ws.consumer.contract.DaoFactory;

/**
 * Classe permettant de définir une DaoFactory, un platformTransactionManager (utilisé
 * dans un contexte transactionnel) et un validateur de contraintes qui seront utilisés par
 * les implémentations de la couche business.
 * @author André Monnier
 */
public abstract class AbstractManager {

	@Inject
    private DaoFactory daoFactory; 
	
	@Inject
	@Named("txManagerBibliotheque")
	private PlatformTransactionManager platformTransactionManager;
	
	private static String dureeMaxEmprunt;

	protected DaoFactory getDaoFactory() {
		return daoFactory; 
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public PlatformTransactionManager getPlatformTransactionManager() {
		return platformTransactionManager;
	}

	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}
	
	 public static String getDureeMaxEmprunt() {
		return dureeMaxEmprunt;
	}

	public static void setDureeMaxEmprunt(String dureeMaxEmprunt) {
		AbstractManager.dureeMaxEmprunt = dureeMaxEmprunt;
	}

	/**
     * Méthode permettant de renvoyer un {@link Validator} de contraintes
     * @return Validator
     */
    protected Validator getConstraintValidator() {
        ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();		
        Validator vValidator = vFactory.getValidator();
        return vValidator;
    }
}