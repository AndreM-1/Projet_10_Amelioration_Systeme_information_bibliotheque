package com.bibliotheques.appliweb.consumer.impl.dao;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bibliotheques.appliweb.consumer.generated.editionservice.EditionService;
import com.bibliotheques.appliweb.consumer.generated.utilisateurservice.UtilisateurService;

/**
 * Classe permettant de définir des objets de type {@link EditionService} et {@link UtilisateurService}
 * qui seront utilisés par les implémentations de la couche Dao pour appeler les méthodes de ces web services.
 * @author André Monnier
 */
public abstract class AbstractDaoImpl {

	private EditionService editionService;
	
	private UtilisateurService utilisateurService;
	
	private static String adresseEditionService;
	
	private static String adresseUtilisateurService;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(AbstractDaoImpl.class);
	
	
	public static void setAdresseEditionService(String adresseEditionService) {
		AbstractDaoImpl.adresseEditionService = adresseEditionService;
	}

	public static void setAdresseUtilisateurService(String adresseUtilisateurService) {
		AbstractDaoImpl.adresseUtilisateurService = adresseUtilisateurService;
	}

	public EditionService getEditionService() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		LOGGER.info("Adresse Web service -  Edition Service : "+adresseEditionService);
        factory.setAddress(adresseEditionService);
        factory.setServiceClass(EditionService.class);
        editionService=(EditionService)factory.create();
		return editionService;
	}
	
	public UtilisateurService getUtilisateurService() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		LOGGER.info("Adresse Web service -  Utilisateur Service : "+adresseUtilisateurService);
		factory.setAddress(adresseUtilisateurService);
		factory.setServiceClass(UtilisateurService.class);
		utilisateurService=(UtilisateurService)factory.create();
		return utilisateurService;
	}
}