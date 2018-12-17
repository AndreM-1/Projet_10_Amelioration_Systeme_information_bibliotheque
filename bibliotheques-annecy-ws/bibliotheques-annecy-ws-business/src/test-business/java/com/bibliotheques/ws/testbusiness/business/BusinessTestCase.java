package com.bibliotheques.ws.testbusiness.business;


import javax.sql.DataSource;

import com.bibliotheques.ws.business.contract.ManagerFactory;


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
    
}
