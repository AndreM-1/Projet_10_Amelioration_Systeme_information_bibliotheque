package com.bibliotheques.ws.testbusiness.business;


import javax.sql.DataSource;

import org.springframework.transaction.PlatformTransactionManager;

import com.bibliotheques.ws.business.contract.ManagerFactory;
import com.bibliotheques.ws.consumer.contract.DaoFactory;


/**
 * Classe mère des classes de test d'intégration de la couche Business.
 * @author André Monnier
 */
public abstract class BusinessTestCase {

    static {
        SpringRegistry.init();
    }
    
    /** {@link ManagerFactory} */
    private static final ManagerFactory MANAGER_FACTORY = SpringRegistry.getManagerFactory();
    

    /** {@link DataSource} */
    private static final DataSource DATA_SOURCE_TEST = SpringRegistry.getDataSourceTest();
    
    /** {@link PlatformTransactionManager} */
    private static final PlatformTransactionManager PLATFORM_TRANSACTION_MANAGER=SpringRegistry.getPlatformTransactionManager(); 
    
    /** {@link DaoFactory} */
    private static final DaoFactory DAO_FACTORY=SpringRegistry.getDaoFactory();
    
	// ==================== Constructeurs ====================
    /**
     * Constructeur.
     */
    public BusinessTestCase() {}

    // ==================== Getters/Setters ====================
	public static ManagerFactory getManagerFactory() {
		return MANAGER_FACTORY;
	}

	public static DataSource getDataSourceTest() {
		return DATA_SOURCE_TEST;
	}
	
    public static PlatformTransactionManager getPlatformTransactionManager() {
		return PLATFORM_TRANSACTION_MANAGER;
	}
    
    public static DaoFactory getDaoFactory() {
    	return DAO_FACTORY;
    }
}
