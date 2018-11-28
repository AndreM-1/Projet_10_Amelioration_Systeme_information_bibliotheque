package com.bibliotheques.ws.business.impl.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bibliotheques.ws.business.contract.manager.EmpruntManager;
import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

@Named
public class EmpruntManagerImpl extends AbstractManager implements EmpruntManager {

	private List<Emprunt> listEmprunt= new ArrayList<>();
	private Emprunt emprunt;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EmpruntManagerImpl.class);
	
	@Override
	public List<Emprunt> getListEmprunt(int utilisateurId) throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListEmprunt()");
		try {
			listEmprunt=getDaoFactory().getEmpruntDao().getListEmprunt(utilisateurId);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		return listEmprunt;
	}
	
	@Override
	public void emprunterEdition(int utilisateurId, int bibliothequeId, int editionId) throws FunctionalException, TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode emprunterEdition()");
		
		//Utilisation d'un TransactionStatus. On a besoin de lever une FunctionalException et une TechnicalException,
		//ce qui n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		
		//En premier, on va mettre à jour la table exemplaire.
		try {
			getDaoFactory().getExemplaireDao().updateNbExemplaire(bibliothequeId, editionId);
		} catch (TechnicalException e) {
			LOGGER.info(e.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new TechnicalException(e.getMessage());
		}
		
		//Si la mise à jour de la table exemplaire a marché, on va mettre à jour la table emprunt.
		Date dateDeDebut;
		Date dateDeFin;
		
		//On définit un SimpleDateFormat.
		DateFormat dfTest = new SimpleDateFormat("dd/MM/yyyy");
		
		//Calendar.getInstance() permet de récupérer la date d'aujourd'hui.
		Calendar calendarDebutFin = Calendar.getInstance();
		dateDeDebut=calendarDebutFin.getTime();
		
		//Grâce au SimpleDateFormat, on affiche la date en format chaîne de caractères pour vérifier si on a le résultat attendu.
		//On vérifie que la date de début est correcte.
		LOGGER.info("Date de début :"+dfTest.format(dateDeDebut));
		
		//On récupère la durée max de l'emprunt convertit en jours.
		int dureeMaxEmpruntJours=this.getConversionJourDureeMaxEmprunt();
		LOGGER.info("Durée max de l'emprunt convertit en jours : "+dureeMaxEmpruntJours);
		
		//On ajoute la durée de l'emprunt à la date de début.
		calendarDebutFin.add(Calendar.DATE,dureeMaxEmpruntJours);
		
		//On vérifie que la date de fin est correcte.
		dateDeFin=calendarDebutFin.getTime();
		LOGGER.info("Date de fin :"+dfTest.format(dateDeFin));
		
		//Par défaut, lors de l'emprunt d'une édition, prolongation est égal à true et statutEmpruntId à 1.
		boolean prolongation=true;
		int statutEmpruntId=1;
		
		try {
			getDaoFactory().getEmpruntDao().insertEmprunt(dateDeDebut,dateDeFin,prolongation, utilisateurId,statutEmpruntId, bibliothequeId,editionId);
			getPlatformTransactionManager().commit(vTransactionStatus);
		} catch (FunctionalException e) {
			LOGGER.info(e.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new FunctionalException (e.getMessage());
		}
	}
	
	@Override
	public void prolongerEmprunt(int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException  {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode prolongerEmprunt()");
		
		//Utilisation d'un TransactionStatus. On a besoin de lever une TechnicalException, ce qui
		//n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		
		//On récupère tout d'abord un objet de type Emprunt à partir des variables utilisateurId et editionId, ce qui est suffisant
		//vu qu'on a défini un index unique sur les colonnes correspondantes dans la BDD.
		try {
			emprunt=getDaoFactory().getEmpruntDao().getEmprunt(utilisateurId,editionId);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new TechnicalException ("Erreur technique lors de l'accès en base de données.");	
		}
		
		//Si on arrive a récupérer un objet de type Emprunt, alors on va mettre à jour la même table après modifications de certains champs.
		Date dateDeFin;
		
		//On définit un SimpleDateFormat.
		DateFormat dfTest = new SimpleDateFormat("dd/MM/yyyy");
		
		//On convertit l'objet XMLGregorianCalendar en GregorianCalendar qui hérite de la classe Calendar.
		Calendar calendar=emprunt.getDateDeFin().toGregorianCalendar();
		
		//On récupère la durée max de l'emprunt convertit en jours.
		int dureeMaxEmpruntJours=this.getConversionJourDureeMaxEmprunt();
		LOGGER.info("Durée max de l'emprunt convertit en jours : "+dureeMaxEmpruntJours);
		
		//La date de fin après prolongation sera égale à la date de fin initiale + la durée de la prolongation.
		calendar.add(Calendar.DATE, dureeMaxEmpruntJours);
		
		//On vérifie que la date de fin est correcte.
		dateDeFin=calendar.getTime();
		LOGGER.info("Date de fin recalculé :"+dfTest.format(dateDeFin));
		
		//Par défaut, lors de la prolongation de l'emprunt d'une édition, on a les valeurs ci-dessous :
		boolean prolongation=false;
		Date dateDeProlongation=new Date();
		String dureeProlongation=getDureeMaxEmprunt();
		int statutEmpruntId=1;
		
		try {
			getDaoFactory().getEmpruntDao().updateEmprunt(dateDeFin,prolongation,dateDeProlongation,dureeProlongation,utilisateurId, statutEmpruntId,bibliothequeId, editionId);
			getPlatformTransactionManager().commit(vTransactionStatus);
		} catch (TechnicalException e) {
			LOGGER.info(e.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new TechnicalException(e.getMessage());
		}	
	}
	
	@Override
	public List<Emprunt> getListEmpruntEnRetard() throws NotFoundException, TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListEmpruntEnRetard()");
		
		//Utilisation d'un TransactionStatus. On a besoin de lever une TechnicalException, ce qui
		//n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());

		try {
			listEmprunt=getDaoFactory().getEmpruntDao(). getListEmpruntAvtUpd();
		} catch (NotFoundException e1) {
			LOGGER.info(e1.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new NotFoundException(e1.getMessage());
		}
		
		//Avant de renvoyer la liste des emprunts en retard, on va d'abord mettre à jour le champs statut_emprunt_id et également 
		//le champs prolongation dans le cas où on dépasse la date de fin de prêt de plus de la durée max du prêt.
		Calendar calDateDuJour=Calendar.getInstance();
		Date dateDuJour=new Date();
		//On définit un SimpleDateFormat.
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		//On récupère la durée max de l'emprunt convertit en jours.
		int dureeMaxEmpruntJours=this.getConversionJourDureeMaxEmprunt();
		LOGGER.info("Durée max de l'emprunt convertit en jours : "+dureeMaxEmpruntJours);
		
		//On ajoute 1 jour à la durée max de l'emprunt pour se faciliter la vie lors de la comparaison.
		dureeMaxEmpruntJours+=1;
		
		LOGGER.info("Taille de la liste d'emprunt avant update : "+listEmprunt.size());
		LOGGER.info("Date du jour : "+df.format(dateDuJour));
		int statutEmpruntId=2;
		boolean prolongation=false;
		for(Emprunt emprunt:listEmprunt) {
			LOGGER.info("Date de fin : "+df.format(emprunt.getDateDeFin().toGregorianCalendar().getTime()));
			LOGGER.info("Résultat de la comparaison de dates : "+dateDuJour.compareTo(emprunt.getDateDeFin().toGregorianCalendar().getTime()));

			//Si la date du jour dépasse STRICTEMENT la date de fin de prêt, alors une mise à jour du champ statut_emprunt_id est nécessaire.
			//La fonction compareTo ne suffisait pas à elle seule, on a donc du ajouter une comparaison entre les DateFormat en String, tout
			//cela pour exclure le cas où la date du jour est égale la date de fin.
			if(dateDuJour.compareTo(emprunt.getDateDeFin().toGregorianCalendar().getTime())>0&&
					!df.format(dateDuJour).equals(df.format(emprunt.getDateDeFin().toGregorianCalendar().getTime()))) {
				LOGGER.info("Mise à jour du champ statut_emprunt_id requis pour Emprunt id = "+emprunt.getId());
				
				try {
					getDaoFactory().getEmpruntDao().updateEmprunt(statutEmpruntId,emprunt.getUtilisateur().getId(),emprunt.getExemplaire().getBibliotheque().getId(), 
							emprunt.getExemplaire().getEdition().getId());
				} catch (TechnicalException e) {
					LOGGER.info(e.getMessage());
					getPlatformTransactionManager().rollback(vTransactionStatus);
					throw new TechnicalException(e.getMessage());
				}
				
				//La fonction compareTo peut être utilisée pour comparer les objets Date mais aussi Calendar.
				//Cela peut même s'avérer plus simple en ajoutant juste 1 jour à la durée max de l'emprunt.
				Calendar calDateFin=emprunt.getDateDeFin().toGregorianCalendar();
				calDateFin.add(Calendar.DATE, dureeMaxEmpruntJours);
				LOGGER.info("Date de fin + durée max prêt + 1 : "+df.format(calDateFin.getTime()));
				if(calDateDuJour.compareTo(calDateFin)>0) {
					LOGGER.info("Mise à jour du champ prolongation à false requis pour Emprunt id = "+emprunt.getId());
					try {
						getDaoFactory().getEmpruntDao().updateEmprunt(prolongation,emprunt.getUtilisateur().getId(),emprunt.getExemplaire().getBibliotheque().getId(),
								emprunt.getExemplaire().getEdition().getId());
					} catch (TechnicalException e) {
						LOGGER.info(e.getMessage());
						getPlatformTransactionManager().rollback(vTransactionStatus);
						throw new TechnicalException(e.getMessage());
					}
				}
			}
		}
		
		//Maintenant que l'on a réactualisé les champs statut_emprunt_id et prolongation, on peut renvoyer la liste des emprunts en retard.
		try {
			listEmprunt=getDaoFactory().getEmpruntDao().getListEmpruntEnRetard();
			getPlatformTransactionManager().commit(vTransactionStatus);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new NotFoundException(e.getMessage());
		}
		return listEmprunt;
	}
	
	/**
	 * Méthode qui permet de convertir la durée de l'emprunt en jours.
	 * @return La durée de l'emprunt en jours
	 */
	private int getConversionJourDureeMaxEmprunt(){
		int dureeMaxEmpruntJours=0;
		//La durée de l'emprunt peut être égale à 1, 2, 3 ou 4 semaines. On convertit cette durée en jours.
		switch(getDureeMaxEmprunt()){
			case "1 semaine":
				dureeMaxEmpruntJours=7;
				break;
			case "1semaine":
				dureeMaxEmpruntJours=7;
				break;	
			case "2 semaines":
				dureeMaxEmpruntJours=14;
				break;
			case "2semaines":
				dureeMaxEmpruntJours=14;
				break;	
			case "3 semaines":
				dureeMaxEmpruntJours=21;
				break;
			case "3semaines":
				dureeMaxEmpruntJours=21;
				break;
			case "4 semaines":
				dureeMaxEmpruntJours=28;
				break;
			case "4semaines":
				dureeMaxEmpruntJours=28;
				break;
			default:LOGGER.info("Erreur lors de la conversion de la durée de l'emprunt en jours.");	
		}
		return dureeMaxEmpruntJours;
	}
}
