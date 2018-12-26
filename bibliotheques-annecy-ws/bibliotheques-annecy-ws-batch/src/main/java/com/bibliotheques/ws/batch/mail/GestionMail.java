package com.bibliotheques.ws.batch.mail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bibliotheques.ws.batch.Configuration;
import com.bibliotheques.ws.batch.generated.editionservice.EditionService;
import com.bibliotheques.ws.batch.generated.editionservice.GetListEmpruntEnRetardFault_Exception;
import com.bibliotheques.ws.batch.generated.editionservice.GetListRappelEmpruntEnCoursFault_Exception;
import com.bibliotheques.ws.batch.generated.editionservice.GetListReservationUpdatedFault_Exception;
import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;


/**
 * Classe relative à la gestion des mails.
 * @author André Monnier
 *
 */
@Component
public class GestionMail {

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(GestionMail.class);

	private EditionService editionService;

	private Configuration configuration;

	private JavaMailSenderImpl eMailSenderImpl=new JavaMailSenderImpl();

	private List<Emprunt> listEmpruntEnRetard;

	private List<Emprunt> listEmprunt;

	private List<Reservation> listReservation;

	private List<Utilisateur> listUtilisateur;

	/**
	 * Constructeur avec paramètres.
	 * @param configuration : On récupère le bean configuration
	 */
	public GestionMail(Configuration configuration) {
		this.configuration=configuration;
	}

	/**
	 * Méthode permettant d'envoyer des mails quotidiennement aux utilisateurs n'ayant pas rendus leurs livres à temps.
	 */
	//@Scheduled(fixedRate = 5000)
	@Scheduled(cron = "${mail.cron}")
	public void sendMail() {
		//Définition du DateFormat pour l'affichage de la date d'envoie du mail.
		DateFormat dfEnvoiMail = new SimpleDateFormat("dd/MM/yyyy HH mm ss SSS");
		LOGGER.info("--------------------------------------------");	
		LOGGER.info("Entrée dans la méthode d'envoi des mails le "+dfEnvoiMail.format(new Date()));
		//On vérifie bien que l'on arrive à récupérer toutes les informations du fichier properties.
		LOGGER.info("Adresse Web Service Edition : "+configuration.getAdresseEditionService());
		LOGGER.info("Adresse Web Service Utilisateur : "+configuration.getAdresseUtilisateurService());
		LOGGER.info("Mail - Titre : "+configuration.getTitre());
		LOGGER.info("Mail - Premier message : "+configuration.getPremierMessage());
		LOGGER.info("Mail - Deuxième message : "+configuration.getDeuxiemeMessage());
		LOGGER.info("Mail - Conclusion : "+configuration.getConclusion());
		LOGGER.info("Mail - Signature : "+configuration.getSignature());
		LOGGER.info("--------------------------------------------");

		//Appel au web service.
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(configuration.getAdresseEditionService());
		factory.setServiceClass(EditionService.class);
		editionService=(EditionService)factory.create();
		try {

			//On récupère une liste d'emprunt correspondant aux emprunts avec le statut non rendu à temps classé par utilisateurId puis id.
			listEmpruntEnRetard=editionService.getListEmpruntEnRetard();
			LOGGER.info("Taille liste Emprunt en retard : "+listEmpruntEnRetard.size());

			//A partir de là, on récupère les utilisateurs concernés.
			listUtilisateur=new ArrayList<>();
			int utilisateurId=-1;
			for(Emprunt emprunt:listEmpruntEnRetard) {
				if(emprunt.getUtilisateur().getId()!=utilisateurId) {
					listUtilisateur.add(emprunt.getUtilisateur());
					utilisateurId=emprunt.getUtilisateur().getId();
				}
			}
			LOGGER.info("Taille liste utilisateur : "+listUtilisateur.size());

			//On va envoyer un mail avec la liste de tous les livres non rendus à temps par utilisateur.
			for(Utilisateur vUtilisateur:listUtilisateur) {
				LOGGER.info("--------------------------------------------");
				LOGGER.info("Nom : "+vUtilisateur.getNom());
				LOGGER.info("Prénom : "+vUtilisateur.getPrenom());
				LOGGER.info("Adresse Mail :"+vUtilisateur.getAdresseMail());
				//Instanciation du bean mail.
				Mail mail=new Mail();
				//Adresse mail du destinataire.
				mail.setTo(vUtilisateur.getAdresseMail());
				//Objet du mail.
				mail.setSubject(configuration.getTitre());

				//Construction du contenu du mail avec des balises HTML
				String contenuMail="<html><body>";
				contenuMail+="Bonjour "+vUtilisateur.getCivilite()+" "+vUtilisateur.getPrenom()+" "+vUtilisateur.getNom()+",";
				contenuMail+="<p>";
				contenuMail+=configuration.getPremierMessage();
				contenuMail+="</p>";

				//Définition du DateFormat pour l'affichage des dates dans le mail.
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				int vUtilisateurId=vUtilisateur.getId();
				for(Emprunt emprunt:listEmpruntEnRetard) {
					if(emprunt.getUtilisateur().getId()==vUtilisateurId) {
						LOGGER.info("ISBN : "+emprunt.getExemplaire().getEdition().getIsbn());
						LOGGER.info("Titre : "+emprunt.getExemplaire().getEdition().getOuvrage().getTitre());
						LOGGER.info("Auteur : "+emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom()+" "+emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getNom());
						LOGGER.info("Editeur : "+emprunt.getExemplaire().getEdition().getEditeur().getNomEditeur());
						LOGGER.info("Période de l'emprunt : "+emprunt.getDateDeDebut()+" - "+emprunt.getDateDeFin());
						contenuMail+="<p>";
						contenuMail+="Intitulé de l'ouvrage : ";
						contenuMail+=emprunt.getExemplaire().getEdition().getOuvrage().getTitre()+" ";
						contenuMail+="<br/>";
						contenuMail+="ISBN : ";
						contenuMail+=emprunt.getExemplaire().getEdition().getIsbn()+" ";
						contenuMail+="<br/>";
						contenuMail+="Auteur : ";
						contenuMail+=emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom()+" "+emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getNom()+" ";
						contenuMail+="<br/>";
						contenuMail+="Editeur : ";
						contenuMail+=emprunt.getExemplaire().getEdition().getEditeur().getNomEditeur()+" ";
						contenuMail+="<br/>";
						contenuMail+="Période de l'emprunt : ";
						contenuMail+="Du ";
						contenuMail+=df.format(emprunt.getDateDeDebut().toGregorianCalendar().getTime());
						contenuMail+=" au ";
						contenuMail+=df.format(emprunt.getDateDeFin().toGregorianCalendar().getTime());
						contenuMail+="</p>";
					}
				}	
				contenuMail+="<p>";
				contenuMail+=configuration.getDeuxiemeMessage();
				contenuMail+="</p>";
				contenuMail+="<p>";
				contenuMail+=configuration.getConclusion();
				contenuMail+="</p>";
				contenuMail+="<p>";
				contenuMail+=configuration.getSignature();
				contenuMail+="</p>";
				contenuMail+="</body></html>";
				mail.setBody(contenuMail);
				this.sendMimeMessage(mail);	
			}

		}catch (GetListEmpruntEnRetardFault_Exception e) {
			//Soit il n'y a aucun emprunt en retard dans l'ensemble du réseau de bibliothèques!!!
			//Ou une erreur technique lors de l'accès en base de données.
			//Dans ces cas là, on n'envoie pas de mails.
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Méthode permettant d'envoyer des mails aux utilisateurs pour leur indiquer que les livres
	 * qu'ils ont réservés sont maintenant disponibles à l'emprunt.
	 */
	@Scheduled(cron = "${mail.cron}")
	public void sendMailReservation() {
		//Définition du DateFormat pour l'affichage de la date d'envoie du mail.
		DateFormat dfEnvoiMailReservation = new SimpleDateFormat("dd/MM/yyyy HH mm ss SSS");
		LOGGER.info("--------------------------------------------");	
		LOGGER.info("Entrée dans la méthode d'envoi des mails le "+dfEnvoiMailReservation.format(new Date()));
		LOGGER.info("--------------------------------------------");

		//Appel au web service.
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(configuration.getAdresseEditionService());
		factory.setServiceClass(EditionService.class);
		editionService=(EditionService)factory.create();

		//On récupère une liste de réservations comportant les utilisateurs qui doivent recevoir un mail
		try {
			listReservation=editionService.getListReservationUpdated();
			LOGGER.info("Taille liste réservation : "+listReservation.size());

			//A partir de là, on récupère les utilisateurs concernés.
			listUtilisateur=new ArrayList<>();
			int utilisateurId=-1;
			for(Reservation vReservation:listReservation) {
				if(vReservation.getUtilisateur().getId()!=utilisateurId) {
					listUtilisateur.add(vReservation.getUtilisateur());
					utilisateurId=vReservation.getUtilisateur().getId();
				}
			}

			LOGGER.info("Taille liste utilisateur : "+listUtilisateur.size());

			//On va envoyer un mail avec la liste de toutes les réservations à venir emprunter par utilisateur.
			for(Utilisateur vUtilisateur:listUtilisateur) {
				//Instanciation du bean mail.
				Mail mail=new Mail();
				//Adresse mail du destinataire.
				mail.setTo(vUtilisateur.getAdresseMail());
				//Objet du mail.
				mail.setSubject(configuration.getTitreReservation());

				//Construction du contenu du mail avec des balises HTML
				String contenuMail="<html><body>";
				contenuMail+="Bonjour "+vUtilisateur.getCivilite()+" "+vUtilisateur.getPrenom()+" "+vUtilisateur.getNom()+",";
				contenuMail+="<p>";
				contenuMail+=configuration.getPremierMessageReservation();
				contenuMail+="</p>";

				int vUtilisateurId=vUtilisateur.getId();
				for(Reservation vReservation:listReservation) {
					if(vReservation.getUtilisateur().getId()==vUtilisateurId) {
						LOGGER.info("Titre : "+vReservation.getExemplaire().getEdition().getOuvrage().getTitre());
						LOGGER.info("Bibliothèque : "+vReservation.getExemplaire().getBibliotheque().getNomBibliotheque());
						LOGGER.info("ISBN : "+vReservation.getExemplaire().getEdition().getIsbn());
						LOGGER.info("Auteur : "+vReservation.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom()+" "+vReservation.getExemplaire().getEdition().getOuvrage().getAuteur().getNom());
						LOGGER.info("Editeur : "+vReservation.getExemplaire().getEdition().getEditeur().getNomEditeur());

						contenuMail+="<p>";
						contenuMail+="Intitulé de l'ouvrage : ";
						contenuMail+=vReservation.getExemplaire().getEdition().getOuvrage().getTitre()+" ";
						contenuMail+="<br/>";
						contenuMail+="Bibliothèque : ";
						contenuMail+=vReservation.getExemplaire().getBibliotheque().getNomBibliotheque();
						contenuMail+="<br/>";							
						contenuMail+="ISBN : ";
						contenuMail+=vReservation.getExemplaire().getEdition().getIsbn()+" ";
						contenuMail+="<br/>";
						contenuMail+="Auteur : ";
						contenuMail+=vReservation.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom()+" "+vReservation.getExemplaire().getEdition().getOuvrage().getAuteur().getNom()+" ";
						contenuMail+="<br/>";
						contenuMail+="Editeur : ";
						contenuMail+=vReservation.getExemplaire().getEdition().getEditeur().getNomEditeur()+" ";
						contenuMail+="</p>";
					}
				}	
				contenuMail+="<p>";
				contenuMail+=configuration.getDeuxiemeMessageReservation();
				contenuMail+="</p>";
				contenuMail+="<p>";
				contenuMail+=configuration.getConclusion();
				contenuMail+="</p>";
				contenuMail+="<p>";
				contenuMail+=configuration.getSignature();
				contenuMail+="</p>";
				contenuMail+="</body></html>";
				mail.setBody(contenuMail);
				this.sendMimeMessage(mail);		
			}
		} catch (GetListReservationUpdatedFault_Exception e) {
			//Soit il n'y a aucun mail à envoyer (pas de mail dépassé de 48H dans la table reservation, exemplaires rendus non réservés, pas d'exemplaires rendus J-1, etc...)
			//Ou une erreur technique lors de l'accès en base de données.
			//Dans ces cas là, on n'envoie pas de mails.
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Méthode permettant d'envoyer des mails aux utilisateurs pour leur indiquer leurs prêts arrivant à expiration dans 5 jours ou moins.
	 */
	@Scheduled(cron = "${mail.cron}")
	public void sendMailRappel() {
		//Définition du DateFormat pour l'affichage de la date d'envoie du mail.
		DateFormat dfEnvoiMailReservation = new SimpleDateFormat("dd/MM/yyyy HH mm ss SSS");
		LOGGER.info("--------------------------------------------");	
		LOGGER.info("Entrée dans la méthode d'envoi des mails le "+dfEnvoiMailReservation.format(new Date()));
		LOGGER.info("--------------------------------------------");

		//Appel au web service.
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(configuration.getAdresseEditionService());
		factory.setServiceClass(EditionService.class);
		editionService=(EditionService)factory.create();

		try {
			//On récupère la liste des emprunts arrivant à expiration dans 5 jours ou moins.
			listEmprunt=editionService.getListRappelEmpruntEnCours();
			LOGGER.info("Taille liste Emprunt arrivant à expiration dans 5 jours ou moins : "+listEmprunt.size());

			//A partir de là, on récupère les utilisateurs concernés.
			listUtilisateur=new ArrayList<>();
			int utilisateurId=-1;
			for(Emprunt emprunt:listEmprunt) {
				if(emprunt.getUtilisateur().getId()!=utilisateurId&&emprunt.getUtilisateur().isMailRappelPret()) {
					listUtilisateur.add(emprunt.getUtilisateur());
					utilisateurId=emprunt.getUtilisateur().getId();
				}
			}
			LOGGER.info("Taille liste utilisateur : "+listUtilisateur.size());

			//On va envoyer un mail avec la liste de tous les livres non rendus à temps par utilisateur.
			for(Utilisateur vUtilisateur:listUtilisateur) {
				LOGGER.info("--------------------------------------------");
				LOGGER.info("Nom : "+vUtilisateur.getNom());
				LOGGER.info("Prénom : "+vUtilisateur.getPrenom());
				LOGGER.info("Adresse Mail :"+vUtilisateur.getAdresseMail());
				//Instanciation du bean mail.
				Mail mail=new Mail();
				//Adresse mail du destinataire.
				mail.setTo(vUtilisateur.getAdresseMail());
				//Objet du mail.
				mail.setSubject(configuration.getTitreRappel());

				//Construction du contenu du mail avec des balises HTML
				String contenuMail="<html><body>";
				contenuMail+="Bonjour "+vUtilisateur.getCivilite()+" "+vUtilisateur.getPrenom()+" "+vUtilisateur.getNom()+",";
				contenuMail+="<p>";
				contenuMail+=configuration.getPremierMessageRappel();
				contenuMail+="</p>";

				//Définition du DateFormat pour l'affichage des dates dans le mail.
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				int vUtilisateurId=vUtilisateur.getId();
				for(Emprunt emprunt:listEmprunt) {
					if(emprunt.getUtilisateur().getId()==vUtilisateurId) {
						LOGGER.info("ISBN : "+emprunt.getExemplaire().getEdition().getIsbn());
						LOGGER.info("Titre : "+emprunt.getExemplaire().getEdition().getOuvrage().getTitre());
						LOGGER.info("Auteur : "+emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom()+" "+emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getNom());
						LOGGER.info("Editeur : "+emprunt.getExemplaire().getEdition().getEditeur().getNomEditeur());
						LOGGER.info("Période de l'emprunt : "+emprunt.getDateDeDebut()+" - "+emprunt.getDateDeFin());
						contenuMail+="<p>";
						contenuMail+="Intitulé de l'ouvrage : ";
						contenuMail+=emprunt.getExemplaire().getEdition().getOuvrage().getTitre()+" ";
						contenuMail+="<br/>";
						contenuMail+="ISBN : ";
						contenuMail+=emprunt.getExemplaire().getEdition().getIsbn()+" ";
						contenuMail+="<br/>";
						contenuMail+="Auteur : ";
						contenuMail+=emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getPrenom()+" "+emprunt.getExemplaire().getEdition().getOuvrage().getAuteur().getNom()+" ";
						contenuMail+="<br/>";
						contenuMail+="Editeur : ";
						contenuMail+=emprunt.getExemplaire().getEdition().getEditeur().getNomEditeur()+" ";
						contenuMail+="<br/>";
						contenuMail+="Période de l'emprunt : ";
						contenuMail+="Du ";
						contenuMail+=df.format(emprunt.getDateDeDebut().toGregorianCalendar().getTime());
						contenuMail+=" au ";
						contenuMail+=df.format(emprunt.getDateDeFin().toGregorianCalendar().getTime());
						contenuMail+="</p>";
					}
				}	
				contenuMail+="<p>";
				contenuMail+=configuration.getDeuxiemeMessageRappel();
				contenuMail+="</p>";
				contenuMail+="<p>";
				contenuMail+=configuration.getConclusion();
				contenuMail+="</p>";
				contenuMail+="<p>";
				contenuMail+=configuration.getSignature();
				contenuMail+="</p>";
				contenuMail+="</body></html>";
				mail.setBody(contenuMail);
				this.sendMimeMessage(mail);	
			}
		} catch (GetListRappelEmpruntEnCoursFault_Exception e) {
			//Dans ce cas là, aucun emprunt n'arrive à expiration dans moins de 5 jours. 
			//on n'envoie pas de mails.
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Multipurpose Internet Mail Extensions (MIME) ou Extensions multifonctions du courrier Internet est un standard internet qui étend le format de données
	 * des courriels pour supporter des textes en différents codage de caractères autres que l'ASCII, des contenus non textuels, des contenus multiples, et des 
	 * informations d'en-tête en d'autres codages que l'ASCII. Les courriels étant généralement envoyés via le protocole SMTP au format MIME, ces courriels sont 
	 * souvent appelés courriels SMTP/MIME. Avec l'apparition du multimédia et l'utilisation croissante des applications bureautiques, le besoin s'est fait sentir 
	 * d'échanger, en plus des fichiers textes, des fichiers binaires (format des applications bureautiques, images, sons, fichiers compressés). Cette méthode
	 * permet donc d'envoyer ce type de message. 
	 * @param mail : Un bean de type {@link Mail}
	 */
	public void sendMimeMessage(Mail mail) {
		LOGGER.info("Entrée dans la méthode sendMimeMessage");
		LOGGER.info("Serveur SMTP Gmail - Host : "+configuration.getServeurSmtpHost());
		LOGGER.info("Serveur SMTP Gmail - Port : "+configuration.getServeurSmtpPort());
		LOGGER.info("Serveur SMTP Gmail - UserName : "+configuration.getServeurSmtpUsername());
		LOGGER.info("Serveur SMTP Gmail - Password : "+configuration.getServeurSmtpPassword());
		LOGGER.info("Serveur SMTP Gmail - Protocol Transport : "+configuration.getServeurSmtpProtocol());
		LOGGER.info("Serveur SMTP Gmail - Auth : "+configuration.getServeurSmtpAuth());
		LOGGER.info("Serveur SMTP Gmail - TlsEnable : "+configuration.getServeurSmtpTlsEnable());
		LOGGER.info("Serveur SMTP Gmail - TlsRequired : "+configuration.getServeurSmtpTlsRequired());
		LOGGER.info("Serveur SMTP Gmail - Connection Timeout : "+configuration.getServeurSmtpConnectionTimeout());
		LOGGER.info("Serveur SMTP Gmail - Timeout : "+configuration.getServeurSmtpTimeout());
		LOGGER.info("Serveur SMTP Gmail - Write Timeout : "+configuration.getServeurSmtpWriteTimeout());
		LOGGER.info("Serveur SMTP Gmail - Debug : "+configuration.getServeurSmtpDebug());
		LOGGER.info("Serveur SMTP Gmail - Default Encoding : "+configuration.getServeurSmtpDefaultEncoding());
		LOGGER.info("--------------------------------------------");	

		eMailSenderImpl.setHost(configuration.getServeurSmtpHost());
		eMailSenderImpl.setPort(configuration.getServeurSmtpPort());
		eMailSenderImpl.setUsername(configuration.getServeurSmtpUsername());
		eMailSenderImpl.setPassword(configuration.getServeurSmtpPassword());
		eMailSenderImpl.setProtocol(configuration.getServeurSmtpProtocol());
		eMailSenderImpl.setDefaultEncoding(configuration.getServeurSmtpDefaultEncoding());
		Properties props = eMailSenderImpl.getJavaMailProperties();
		props.put("mail.smtp.auth", configuration.getServeurSmtpAuth());
		props.put("mail.smtp.starttls.enable", configuration.getServeurSmtpTlsEnable());
		props.put("mail.smtp.starttls.required", configuration.getServeurSmtpTlsRequired());
		props.put("mail.smtp.connectiontimeout", configuration.getServeurSmtpConnectionTimeout());
		props.put("mail.smtp.timeout", configuration.getServeurSmtpTimeout());
		props.put("mail.smtp.writetimeout", configuration.getServeurSmtpWriteTimeout());
		props.put("mail.debug", configuration.getServeurSmtpDebug());

		LOGGER.info("Vérification des propriétés de l'eMailSender.");
		LOGGER.info("mail.smtp.auth :"+props.getProperty("mail.smtp.auth"));
		LOGGER.info("mail.smtp.starttls.enable :"+props.getProperty("mail.smtp.starttls.enable"));
		LOGGER.info("mail.smtp.starttls.required :"+props.getProperty("mail.smtp.starttls.required"));
		LOGGER.info("mail.smtp.connectiontimeout :"+props.getProperty("mail.smtp.connectiontimeout"));
		LOGGER.info("mail.smtp.timeout :"+props.getProperty("mail.smtp.timeout"));
		LOGGER.info("mail.smtp.writetimeout :"+props.getProperty("mail.smtp.writetimeout"));
		LOGGER.info("mail.debug :"+props.getProperty("mail.debug"));
		LOGGER.info("Host : "+eMailSenderImpl.getHost());
		LOGGER.info("Port : "+eMailSenderImpl.getPort());
		LOGGER.info("Username : "+eMailSenderImpl.getUsername());
		LOGGER.info("Password : "+eMailSenderImpl.getPassword());
		LOGGER.info("Protocol : "+eMailSenderImpl.getProtocol());
		LOGGER.info("Default Encoding : "+eMailSenderImpl.getDefaultEncoding());

		MimeMessage mimeMessage = eMailSenderImpl.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
			mimeMessageHelper.setTo(mail.getTo());
			mimeMessageHelper.setSubject(mail.getSubject());
			mimeMessageHelper.setText(mail.getBody(),true);
			eMailSenderImpl.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
