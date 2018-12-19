package com.bibliotheques.ws.testbusiness.business;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

import com.bibliotheques.ws.business.contract.ManagerFactory;
import com.bibliotheques.ws.consumer.contract.DaoFactory;


/**
 * Registre des Beans Spring.
 * @author André Monnier
 */
public final class SpringRegistry {

    /** Logger Log4j pour la classe */
    private static final Logger LOGGER = LogManager.getLogger(SpringRegistry.class);


    /** Instance unique de la classe (design pattern Singleton) */
    private static final SpringRegistry INSTANCE = new SpringRegistry();


    /** Nom des fichiers de contexte de l'application */
    private static final String CONTEXT_APPLI_LOCATION = "classpath:/applicationContextTestIntegration.xml";

    /** Le context spring de l'application */
    private ApplicationContext contextAppli;


    // ==================== ID des Beans Spring ====================


    /**
     * Constructeur.
     */
    private SpringRegistry() {
        super();
        SpringRegistry.LOGGER.info("[DEBUT] SpringRegistry() - Initialisation du contexte Spring");
        this.contextAppli = new ClassPathXmlApplicationContext(SpringRegistry.CONTEXT_APPLI_LOCATION);
        SpringRegistry.LOGGER.info("[FIN] SpringRegistry() - Initialisation du contexte Spring");
    }

    /**
     * Renvoie l'instance unique de la classe (design pattern Singleton).
     *
     * @return SpringRegistry
     */
    protected static final SpringRegistry getInstance() {
        return SpringRegistry.INSTANCE;
    }

    /**
     * Initialise et charge le contexte Spring
     *
     * @return ApplicationContext
     */
    public static final ApplicationContext init() {
        // le fait d'appeler cette méthode, déclanche l'appel des initialisation static et donc le chargement du context
        return getInstance().contextAppli;
    }

    /**
     * Récupération d'un bean via Spring.
     *
     * @param pBeanId ID du bean
     * @return Object
     */
    protected static Object getBean(String pBeanId) {
        SpringRegistry.LOGGER.info("[DEBUT] SpringRegistry.getBean() - Bean ID : " + pBeanId);
        Object vBean = SpringRegistry.getInstance().contextAppli.getBean(pBeanId);
        SpringRegistry.LOGGER.info("[FIN] SpringRegistry.getBean() - Bean ID : " + pBeanId);
        return vBean;
    }


    /**
     * Renvoie l'instance de {@link ManagerFactory} de l'application
     * @return {@link ManagerFactory}
     */
    public static ManagerFactory getManagerFactory() {
        return (ManagerFactory) SpringRegistry.getBean("ManagerFactory");
    }


    /**
     * Renvoie l'instance de {@link DataSource} de l'application
     * @return {@link DataSource}
     */
    public static DataSource getDataSourceTest() {
    	return (DataSource) SpringRegistry.getBean("dataSourceBibliotheque");
    }
    
    /**
     * Renvoie l'instance de {@link PlatformTransactionManager} de l'application
     * @return {@link PlatformTransactionManager}
     */
    public static PlatformTransactionManager getPlatformTransactionManager() {
    	return (PlatformTransactionManager) SpringRegistry.getBean("txManagerBibliotheque");
    }
    
    
    /**
     * Renvoie l'instance de {@link DaoFactory} de l'application
     * @return {@link DaoFactory}
     */
    public static DaoFactory getDaoFactory() {
        return (DaoFactory) SpringRegistry.getBean("DaoFactory");
    }
}
