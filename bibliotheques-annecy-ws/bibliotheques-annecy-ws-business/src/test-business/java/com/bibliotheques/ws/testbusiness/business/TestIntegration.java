package com.bibliotheques.ws.testbusiness.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.ws.model.exception.TechnicalException;

/**
 * Classe permettant d'effectuer des tests d'intégration 
 * au niveau du module business.
 * @author André Monnier
 *
 */
public class TestIntegration extends BusinessTestCase{

	//ATTENTION à l'ordre à cause des contraintes de clés étrangères !!!
	private static final String[] TABNOMTABLESBDD= {"public.reservation","public.emprunt","public.statut_emprunt","public.utilisateur","public.exemplaire",
			"public.bibliotheque","public.edition","public.genre","public.editeur","public.photo","public.ouvrage","public.auteur"};

	private static final String[] TABNOMSEQUENCESBDD= {"public.reservation_id_seq","public.emprunt_id_seq","public.statut_emprunt_id_seq","public.utilisateur_id_seq",
			"public.bibliotheque_id_seq","public.edition_id_seq","public.genre_id_seq","public.editeur_id_seq","public.photo_id_seq","public.ouvrage_id_seq",
			"public.auteur_id_seq"};

	@Before
	public void setUp() throws Exception {
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSourceTest());

		//On efface les données des tables de la base de données.
		for(String str:TABNOMTABLESBDD) {
			String vSQL= "DELETE FROM "+ str;
			try {
				vJdbcTemplate.update(vSQL);
			} catch (DataAccessException e) {
				getPlatformTransactionManager().rollback(vTransactionStatus);
				throw new TechnicalException("Erreur d'accès à la base de données");
			}
		}

		//On réinitialise les séquences de toutes les tables.
		for(String str:TABNOMSEQUENCESBDD) {
			try {
				vJdbcTemplate.update("ALTER SEQUENCE "+ str+" RESTART 1");
			} catch (DataAccessException e) {
				getPlatformTransactionManager().rollback(vTransactionStatus);
				throw new TechnicalException("Erreur d'accès à la base de données");
			}
		}

		//On remplit les tables de la base de données avec les données initiales du jeu de démo.
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setSqlScriptEncoding("UTF-8");
		try {
			populator.addScripts(new ClassPathResource("02_Insertion_donnees_DB_Systeme_information_bibliotheque_TI_v1.sql"));
			populator.execute(getDataSourceTest());
			getPlatformTransactionManager().commit(vTransactionStatus);
		} catch (ScriptException e) {
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new TechnicalException("Erreur d'accès à la base de données");
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSourceTest());

		//On efface les données des tables de la base de données.
		for(String str:TABNOMTABLESBDD) {
			String vSQL= "DELETE FROM "+ str;
			try {
				vJdbcTemplate.update(vSQL);
			} catch (DataAccessException e) {
				getPlatformTransactionManager().rollback(vTransactionStatus);
				throw new TechnicalException("Erreur d'accès à la base de données");
			}
		}

		//On réinitialise les séquences de toutes les tables.
		for(String str:TABNOMSEQUENCESBDD) {
			try {
				vJdbcTemplate.update("ALTER SEQUENCE "+ str+" RESTART 1");
			} catch (DataAccessException e) {
				getPlatformTransactionManager().rollback(vTransactionStatus);
				throw new TechnicalException("Erreur d'accès à la base de données");
			}
		}

		//On remplit les tables de la base de données avec les données initiales du jeu de démo.
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setSqlScriptEncoding("UTF-8");
		try {
			populator.addScripts(new ClassPathResource("02_Insertion_donnees_DB_Systeme_information_bibliotheque_v1.sql"));
			populator.execute(getDataSourceTest());
			getPlatformTransactionManager().commit(vTransactionStatus);
		} catch (ScriptException e) {
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new TechnicalException("Erreur d'accès à la base de données");
		}
	}

	/**
	 * Méthode qui permet de tester la lecture de la base de données, i.e les méthodes getListEmpruntEnRetard(), 
	 * getListEmprunt(int utilisateurId,int bibliothequeId,int editionId),getListReservation(int utilisateurId, int bibliothequeId, int editionId,int nbExemplairesInit),
	 * getListReservationUtilisateur(int utilisateurId) et getListRappelEmpruntEnCours().
	 * @throws Exception
	 */
	@Test
	public void testSelect() throws Exception {

		List<Emprunt> vListEmpruntEnRetardBDD=getManagerFactory().getEmpruntManager().getListEmpruntEnRetard();
		assertNotNull("La liste d'emprunt en retard ne doit pas être nul.",vListEmpruntEnRetardBDD);
		assertEquals("La taille de la liste d'emprunt est erronée.",5,vListEmpruntEnRetardBDD.size());

		int utilisateurId=1;
		int bibliothequeId=2;
		int editionId=32;
		List<Emprunt> vListEmpruntBDD=getManagerFactory().getEmpruntManager().getListEmprunt(utilisateurId, bibliothequeId, editionId);
		assertNotNull("La liste d'emprunt ne doit pas être nul.",vListEmpruntBDD);
		assertEquals("La taille de la liste d'emprunt est erronée.",1,vListEmpruntBDD.size());

		utilisateurId=2;
		bibliothequeId=1;
		editionId=1;
		int nbExemplairesInit=1;
		List<Reservation> vListReservationBDD=getManagerFactory().getReservationManager().getListReservation(utilisateurId, bibliothequeId, editionId, nbExemplairesInit);
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",0,vListReservationBDD.size());

		utilisateurId=4;
		bibliothequeId=4;
		editionId=35;
		nbExemplairesInit=2;
		vListReservationBDD=getManagerFactory().getReservationManager().getListReservation(utilisateurId, bibliothequeId, editionId, nbExemplairesInit);
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",1,vListReservationBDD.size());

		utilisateurId=1;
		List<Reservation> vListReservationUtilisateurBDD=getManagerFactory().getReservationManager().getListReservationUtilisateur(utilisateurId);
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationUtilisateurBDD);
		assertEquals("La taille de la liste de réservation est erronée.",4,vListReservationUtilisateurBDD.size());
		
		vListEmpruntBDD=getManagerFactory().getEmpruntManager().getListRappelEmpruntEnCours();
		assertNotNull("La liste d'emprunt ne doit pas être nul.",vListEmpruntBDD);
		assertEquals("La taille de la liste d'emprunt est erronée.",6,vListEmpruntBDD.size());
	}
	
	/**
	 * Méthode qui permet de tester la réservation d'une édition en base de données dans les cas suivants :
	 * 	- Edition déjà réservée par un autre utilisateur.
	 *  - Edition qui n'a pas été réservée.
	 * @throws Exception
	 */
	@Test
	public void testReserverEdition() throws Exception{
		int utilisateurId=4;
		int bibliothequeId=4;
		int editionId=35;
		getManagerFactory().getReservationManager().reserverEdition(utilisateurId, bibliothequeId, editionId);
		List<Reservation> vListReservationBDD=getManagerFactory().getReservationManager().getListAllReservation();
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",8,vListReservationBDD.size());
		
		utilisateurId=2;
		bibliothequeId=1;
		editionId=1;
		getManagerFactory().getReservationManager().reserverEdition(utilisateurId, bibliothequeId, editionId);
		vListReservationBDD=getManagerFactory().getReservationManager().getListAllReservation();
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",9,vListReservationBDD.size());
	}
	
	/**
	 * Méthode qui permet de tester l'annulation d'une réservation. 
	 * @throws Exception
	 */
	@Test
	public void testAnnulerReservation() throws Exception{
		int utilisateurId=3;
		int bibliothequeId=4;
		int editionId=36;
		getManagerFactory().getReservationManager().annulerReservation(utilisateurId, bibliothequeId, editionId);
		List<Reservation> vListReservationBDD=getManagerFactory().getReservationManager().getListAllReservation();
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",6,vListReservationBDD.size());
	}
	
	/**
	 * Test d'intégration de la méthode getListReservationUpdated(). On se place dans les conditions suivantes :
	 * Le délai de 48H pour venir chercher l'emprunt est passé pour 2 utilisateurs. Les 2 lignes de réservation correspondantes
	 * vont donc être supprimées, et ces 2 utilisateurs vont recevoir un mail.
	 * @throws Exception
	 */
	@Test
	public void testGetListReservationUpdated() throws Exception{				
		List<Reservation> vListReservationBDD=getManagerFactory().getReservationManager().getListReservationUpdated();
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",2,vListReservationBDD.size());
		
		vListReservationBDD=getManagerFactory().getReservationManager().getListAllReservation();
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",5,vListReservationBDD.size()); 
	}
	
	/**
	 * Test d'intégration de la méthode getListReservationUpdatedRetourEmprunt(). On se place dans les conditions suivantes :
	 * L'emprunt (4,35) a été restitué J-1 avant le test.
	 * @throws Exception
	 */
	@Test
	public void testGetListReservationUpdatedRetourEmprunt() throws Exception{
		List<Reservation> vListReservationBDD=getManagerFactory().getReservationManager().getListReservationUpdatedRetourEmprunt();
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",1,vListReservationBDD.size());
		
		vListReservationBDD=getManagerFactory().getReservationManager().getListAllReservation();
		assertNotNull("La liste de réservation ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservation est erronée.",7,vListReservationBDD.size()); 
	}
	
	/**
	 * Test d'intégration de la méthode updateParametresUtilisateur(int id, boolean mailRappelPret) dans les cas suivants :
	 *  - Modification du champ "mail_rappel_pret" à false en base de données pour l'utilisateur d'id 1. On récupère cet utilisateur à partir de son adresseMail/motDePasse
	 *  et on vérifie que la modification a bien été effectuée.
	 *  - Modification du champ "mail_rappel_pret" à true en base de données pour l'utilisateur d'id 1. On récupère cet utilisateur à partir de son adresseMail/motDePasse
	 *  et on vérifie que la modification a bien été effectuée.
	 * @throws Exception
	 */
	@Test
	public void testUpdateParametresUtilisateur() throws Exception{
		getManagerFactory().getUtilisateurManager().updateParametresUtilisateur(1, false); 
		Utilisateur vUtilisateurBDD=getManagerFactory().getUtilisateurManager().getUtilisateur("andre_monnier@yahoo.fr", "M0tp@SAdM83!!");
		assertEquals("La valeur de l'attribut mailRappelPret est erronée.",false,vUtilisateurBDD.isMailRappelPret()); 
		
		getManagerFactory().getUtilisateurManager().updateParametresUtilisateur(1, true);
		vUtilisateurBDD=getManagerFactory().getUtilisateurManager().getUtilisateur("andre_monnier@yahoo.fr", "M0tp@SAdM83!!");
		assertEquals("La valeur de l'attribut mailRappelPret est erronée.",true,vUtilisateurBDD.isMailRappelPret()); 	
	}
}