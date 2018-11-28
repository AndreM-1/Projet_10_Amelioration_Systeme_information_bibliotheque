package com.bibliotheques.ws.business.impl.manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Named;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bibliotheques.ws.business.contract.manager.UtilisateurManager;
import com.bibliotheques.ws.business.passwordutils.PasswordUtils;
import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;

@Named
public class UtilisateurManagerImpl extends AbstractManager implements UtilisateurManager {

	private Utilisateur utilisateur;
	private static final int SALT_LENGTH = 30;

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(UtilisateurManagerImpl.class);

	@Override
	public Utilisateur getUtilisateur(String adresseMail, String motDePasse) throws NotFoundException {

		try {
			utilisateur=getDaoFactory().getUtilisateurDao().getUtilisateur(adresseMail);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException("Aucun utilisateur correspondant à l'adresse mail demandée.");
		}

		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode getUtilisateur - Mot de passe non sécurisé : "+motDePasse);
		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode getUtilisateur - Salt : "+utilisateur.getSalt());
		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode getUtilisateur - Mot de passe sécurisé : "+utilisateur.getMotDePasseSecurise());

		boolean passwordMatch=PasswordUtils.verifyUserPassword(motDePasse,utilisateur.getMotDePasseSecurise(), utilisateur.getSalt());

		if (passwordMatch) {
			return utilisateur;

		}else {
			LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode getUtilisateur - Aucun utilisateur correspondant au password fourni.");
			throw new NotFoundException("Mot de passe erroné.");	
		}
	}

	@Override
	public void insertUtilisateur(String civilite, String nom, String prenom, String pseudo,
			String adresseMail, String motDePasse, String confirmationMotDePasse, boolean conditionsUtilisation) throws FunctionalException {

		LOGGER.info("Conditions d'utilisation : "+conditionsUtilisation);
		//On lève une exception si l'un des champs saisis dans le formulaire n'est pas bon.
		//Il s'agit de l'équivalent de la validation de bean du projet 6.
		if(!conditionsUtilisation) {
			throw new FunctionalException("Veuillez accepter les conditions d'utilisation.");
		}

		if(civilite.trim().isEmpty()||civilite.length()>8) {
			throw new FunctionalException("Le champ civilité n'est pas renseigné correctement.");
		}

		if(nom.trim().isEmpty()|| nom.length()>100) {
			throw new FunctionalException("Le champ nom n'est pas renseigné correctement.");
		}
		
		if(prenom.trim().isEmpty()|| prenom.length()>100) {
			throw new FunctionalException("Le champ prénom n'est pas renseigné correctement.");
		}
		
		if(pseudo.trim().isEmpty()||pseudo.length()>100) {
			throw new FunctionalException("Le champ pseudo n'est pas renseigné correctement.");
		}
		
		if(adresseMail.trim().isEmpty()||adresseMail.length()>100) {
			throw new FunctionalException("Le champ adresse e-mail n'est pas renseigné correctement.");
		}
		
		//Pour l'adresse mail, on vérifie en plus la validité du format.
		if(!EmailValidator.getInstance().isValid(adresseMail)) {
			throw new FunctionalException("Le format saisi pour le champ adresse e-mail est incorrect.");
		}
		
		if(motDePasse.trim().isEmpty()||motDePasse.length()>100 ) {
			throw new FunctionalException("Le champ mot de passe n'est pas renseigné correctement.");
		}
		
		if(confirmationMotDePasse.trim().isEmpty()||confirmationMotDePasse.length()>100) {
			throw new FunctionalException("Le champ confirmationMotDePasse n'est pas renseigné correctement.");
		}
		
		if(!confirmationMotDePasse.equals(motDePasse)) {
			throw new FunctionalException("Le champ confirmationMotDePasse n'est pas renseigné correctement.");
		}
		
		//Si le formulaire est correctement renseigné, alors on lance la transaction.
		//Utilisation d'un TransactionStatus. On a besoin de lever une FunctionalException,
		//ce qui n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {

		//Cryptage Mot de passe.
		String salt=PasswordUtils.getSalt(SALT_LENGTH);
		String mySecurePassword=PasswordUtils.generateSecurePassword(motDePasse, salt);
		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode insertUtilisateur - Salt :"+salt);
		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode insertUtilisateur - Mot de passe sécurisé : "+mySecurePassword);
		
		//Instanciation et remplissage du bean utilisateur
		utilisateur=new Utilisateur();
		utilisateur.setCivilite(civilite);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setPseudo(pseudo);
		utilisateur.setAdresseMail(adresseMail);
		utilisateur.setSalt(salt);
		utilisateur.setMotDePasseSecurise(mySecurePassword);	
		utilisateur.setPays("France");
	
		//Appel à la DaoFactory
		getDaoFactory().getUtilisateurDao().insertUtilisateur(utilisateur);
		
		
		} catch (FunctionalException vEx) {
			LOGGER.info(vEx.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new FunctionalException(vEx.getMessage());
		} 
		getPlatformTransactionManager().commit(vTransactionStatus);
	}
	
	@Override
	public void updateCoordUtilisateur(int id, String civilite, String nom, String prenom, String pseudo,
			String adresseMail, String telephone, XMLGregorianCalendar dateNaissance, String adresse, String codePostal,
			String ville, String pays) throws FunctionalException {
		
		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode updateCoordUtilisateur");
		
		//On lève une exception si l'un des champs saisis dans le formulaire n'est pas bon.
		//Il s'agit de l'équivalent de la validation de bean du projet 6.
		if(civilite.trim().isEmpty()||civilite.length()>8) {
			throw new FunctionalException("Le champ civilité n'est pas renseigné correctement.");
		}

		if(nom.trim().isEmpty()|| nom.length()>100) {
			throw new FunctionalException("Le champ nom n'est pas renseigné correctement.");
		}
		
		if(prenom.trim().isEmpty()|| prenom.length()>100) {
			throw new FunctionalException("Le champ prénom n'est pas renseigné correctement.");
		}
		
		if(pseudo.trim().isEmpty()||pseudo.length()>100) {
			throw new FunctionalException("Le champ pseudo n'est pas renseigné correctement.");
		}
		
		if(adresseMail.trim().isEmpty()||adresseMail.length()>100) {
			throw new FunctionalException("Le champ adresse e-mail n'est pas renseigné correctement.");
		}
		
		//Pour l'adresse mail, on vérifie en plus la validité du format.
		if(!EmailValidator.getInstance().isValid(adresseMail)) {
			throw new FunctionalException("Le format saisi pour le champ adresse e-mail est incorrect.");
		}
		
		//Pour le téléphone, on utilise une expression régulière pour définir le format accepté.	
		if(!telephone.trim().isEmpty()) {
			Pattern pattern=Pattern.compile("^0[1-9](-[0-9]{2}){4}$");
			Matcher matcher=pattern.matcher(telephone);
			
			if(!matcher.matches()) {
				throw new FunctionalException("Le champ téléphone n'est pas renseigné correctement.");
			}
		}
	
		if(adresse.length()>100) {
			throw new FunctionalException("Le champ adresse n'est pas renseigné correctement.");
		}
		
		if(codePostal.length()>5) {
			throw new FunctionalException("Le champ code postal n'est pas renseigné correctement.");
		}
		
		if(ville.length()>100) {
			throw new FunctionalException("Le champ ville n'est pas renseigné correctement.");
		}
		
		//Facultatif
		if(pays.length()>6) {
			throw new FunctionalException("Le champ pays n'est pas renseigné correctement.");
		}
		
		//Si le formulaire est correctement renseigné, alors on lance la transaction.
		//Utilisation d'un TransactionStatus. On a besoin de lever une FunctionalException,
		//ce qui n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		try {
			
		//Appel à la DaoFactory
		getDaoFactory().getUtilisateurDao().updateCoordUtilisateur(id, civilite, nom, prenom, pseudo,adresseMail, telephone, dateNaissance, adresse, codePostal,
			 ville, pays);
		} catch (FunctionalException vEx) {
			LOGGER.info(vEx.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new FunctionalException(vEx.getMessage());
		}
		getPlatformTransactionManager().commit(vTransactionStatus);
	}
	
	@Override
	public void updateMdpUtilisateur(int id, String ancienMdp, String nouveauMdp, String confirmationNouveauMdp) throws FunctionalException,NotFoundException {
		
		//On lève une exception si l'un des champs saisis dans le formulaire n'est pas bon.
		
		//On récupère un objet de type Utilisateur à partir de son id.
		try {
			utilisateur=getDaoFactory().getUtilisateurDao().getUtilisateur(id);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException("Aucun utilisateur correspondant à l'id demandé.");
		}
		
		//On vérifie si le mot de passe actuel correspond au mot de passe enregistré en base de données.
		//Si ce n'est pas le cas, on lève une FunctionalException.
		boolean passwordMatch=PasswordUtils.verifyUserPassword(ancienMdp,utilisateur.getMotDePasseSecurise(), utilisateur.getSalt());
		
		if (!passwordMatch) {
			//Dans ce cas-là, on va plutôt lever une FunctionalException plutôt qu'une NotFoundException.
			throw new FunctionalException("Le champ mot de passe actuel n'est pas correct.");
		}
		
		
		if(nouveauMdp.trim().isEmpty()||nouveauMdp.length()>100 ) {
			throw new FunctionalException("Le champ nouveau mot de passe n'est pas renseigné correctement.");
		}
		
		if(confirmationNouveauMdp.trim().isEmpty()||confirmationNouveauMdp.length()>100) {
			throw new FunctionalException("Le champ confirmation du nouveau mot de passe n'est pas renseigné correctement.");
		}
		
		if(!confirmationNouveauMdp.equals(nouveauMdp)) {
			throw new FunctionalException("Le champ confirmation du nouveau mot de passe n'est pas renseigné correctement.");
		}
		
		//Si le formulaire est correctement renseigné, alors on lance la transaction.
		//Utilisation d'un TransactionStatus. On a besoin de lever une FunctionalException,
		//ce qui n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		
		//Cryptage Mot de passe.
		String salt=PasswordUtils.getSalt(SALT_LENGTH);
		String mySecurePassword=PasswordUtils.generateSecurePassword(nouveauMdp, salt);
		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode updateMdpUtilisateur - Nouveau mot de passe :"+nouveauMdp);
		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode updateMdpUtilisateur - Salt :"+salt);
		LOGGER.info("Web Service : UtilisateurService - Couche Business - Méthode updateMdpUtilisateur - Mot de passe sécurisé : "+mySecurePassword);
		
		//Remplissage du bean utilisateur
		utilisateur.setSalt(salt);
		utilisateur.setMotDePasseSecurise(mySecurePassword);
		
		//Appel à la DaoFactory
		getDaoFactory().getUtilisateurDao(). updateMdpUtilisateur(utilisateur);
		
		getPlatformTransactionManager().commit(vTransactionStatus);
	}
	
}