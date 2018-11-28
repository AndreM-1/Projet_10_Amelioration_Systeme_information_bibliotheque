package com.bibliotheques.ws.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

/**
 * Classe principale contenant la méthode main.
 * @author André Monnier
 *
 */
@SpringBootApplication
@EnableScheduling
public class Application {
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(Application.class);
	
	@Bean
	public TaskScheduler taskScheduler() {
	    return new ConcurrentTaskScheduler(); //single threaded by default
	}
	
	/**
	 * Lors de l'exécution de la méthode main, toutes les classes avec l'annotation @Component vont être vérifiées.
	 * Et les méthodes de ces classes vont être executées suivant la périodicité indiquée via l'annotation @Scheduled
	 * @param args : Paramètre par défaut
	 */
	public static void main( String[] args ) {
		LOGGER.info("************************Exécution de la méthode main.****************************");
		ApplicationContext vApplicationContext=new ClassPathXmlApplicationContext("classpath:/batchContext.xml");
		Configuration configuration=vApplicationContext.getBean("configuration", Configuration.class);		
		//Je mets cette ligne ci-dessous en commentaire sinon j'ai des erreurs dans la console même si cela fonctionne.
		//SpringApplication.run(Application.class);
    }
}