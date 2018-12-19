package com.bibliotheques.ws.business.impl.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bibliotheques.ws.consumer.contract.DaoFactory;
import com.bibliotheques.ws.consumer.contract.dao.EmpruntDao;
import com.bibliotheques.ws.consumer.contract.dao.ReservationDao;
import com.bibliotheques.ws.model.bean.edition.Bibliotheque;
import com.bibliotheques.ws.model.bean.edition.Edition;
import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.bean.edition.Exemplaire;
import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;

/**
 * Classe permettant d'effectuer des tests unitaires sur la classe {@link ReservationManagerImpl}
 * @author André Monnier
 *
 */
public class ReservationManagerImplTest {

	private static DaoFactory daoFactoryMock=mock(DaoFactory.class);
	private static PlatformTransactionManager platformTransactionManagerMock=mock(PlatformTransactionManager.class);
	private static DefaultTransactionDefinition defaultTransactionDefinitionMock=mock(DefaultTransactionDefinition.class);
	private static TransactionStatus transactionStatusMock=mock(TransactionStatus.class);
	private static ReservationDao reservationDaoMock=mock(ReservationDao.class);
	private static EmpruntDao empruntDaoMock=mock(EmpruntDao.class);
	private ReservationManagerImpl reservationManagerImpl=new ReservationManagerImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AbstractManager.setDaoFactory(daoFactoryMock);
		AbstractManager.setPlatformTransactionManager(platformTransactionManagerMock);
		when(platformTransactionManagerMock.getTransaction(defaultTransactionDefinitionMock)).thenReturn(transactionStatusMock);
		when(daoFactoryMock.getReservationDao()).thenReturn(reservationDaoMock); 
		when(daoFactoryMock.getEmpruntDao()).thenReturn(empruntDaoMock); 
	}
	
	/**
	 * Test de la méthode getListReservation(int utilisateurId, int bibliothequeId, int editionId,int nbExemplairesInit) dans le cas où l'utilisateur a déjà
	 * réservé l'édition. On s'attend à lever une exception de type {@link FunctionalException}
	 * @throws Exception
	 */
	@Test(expected = FunctionalException.class)
	public void getListReservationCase1() throws Exception{
		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,17);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);

		int nbExemplairesInit=1;

		when(reservationDaoMock.getReservation(vUtilisateur1.getId(), vEdition1.getId())).thenReturn(vReservation1);
		reservationManagerImpl.getListReservation(vUtilisateur1.getId(), vBibliotheque1.getId(), vEdition1.getId(), nbExemplairesInit);
	}

	/**
	 * Test de la méthode getListReservation(int utilisateurId, int bibliothequeId, int editionId,int nbExemplairesInit) dans le cas où l'utilisateur n'a pas déjà
	 * réservé l'édition mais que la liste d'attente des réservations est complète. On s'attend à lever une exception de type {@link FunctionalException}
	 * @throws Exception
	 */
	@Test(expected = FunctionalException.class)
	public void getListReservationCase2() throws Exception{
		List<Reservation> vListReservation=new ArrayList<>();

		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,17);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);

		vListReservation.add(vReservation1);

		Reservation vReservation2=new Reservation();
		vReservation2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,18);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vReservation2.setDateReservation(vXGCDateDebut2);

		Utilisateur vUtilisateur2=new Utilisateur();
		vUtilisateur2.setId(2);
		vReservation2.setUtilisateur(vUtilisateur2);
		vReservation2.setExemplaire(vExemplaire1);
		vReservation2.setPrioriteReservation(2);

		vListReservation.add(vReservation2);

		int nbExemplairesInit=1;

		when(reservationDaoMock.getReservation(vUtilisateur1.getId(), vEdition1.getId())).thenThrow(new NotFoundException("Aucune réservation trouvée en BDD."));
		
		//POINT IMPORTANT : Précédemment, la méthode getListReservation(bibliothequeId,editionId) avait été appelée par la méthode de test 
		//public void reserverEditionCase1() et levait une exception. Le pb est que même lors de l'appel de la méthode getListReservation(bibliothequeId,editionId)
		//pour un autre test, une exception sera tjrs levée. Pour annuler ce comportement, il faut utiliser la syntaxe ci-dessous.
		doReturn(vListReservation).when(reservationDaoMock).getListReservation(vBibliotheque1.getId(),vEdition1.getId());

		reservationManagerImpl.getListReservation(vUtilisateur1.getId(), vBibliotheque1.getId(), vEdition1.getId(), nbExemplairesInit);
	}

	/**
	 * Test de la méthode getListReservation(int utilisateurId, int bibliothequeId, int editionId,int nbExemplairesInit) dans le cas où l'utilisateur n'a pas déjà
	 * réservé l'édition et que la liste d'attente des réservations n'est pas complète.
	 * @throws Exception
	 */
	@Test
	public void getListReservationCase3() throws Exception{
		List<Reservation> vListReservation=new ArrayList<>();

		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,17);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);

		vListReservation.add(vReservation1);

		Reservation vReservation2=new Reservation();
		vReservation2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,18);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vReservation2.setDateReservation(vXGCDateDebut2);

		Utilisateur vUtilisateur2=new Utilisateur();
		vUtilisateur2.setId(2);
		vReservation2.setUtilisateur(vUtilisateur2);
		vReservation2.setExemplaire(vExemplaire1);
		vReservation2.setPrioriteReservation(2);

		vListReservation.add(vReservation2);

		int nbExemplairesInit=2;

		doReturn(vListReservation).when(reservationDaoMock).getListReservation(vBibliotheque1.getId(),vEdition1.getId());

		List<Reservation> vListReservationBDD=reservationManagerImpl
				.getListReservation(vUtilisateur1.getId(), vBibliotheque1.getId(), vEdition1.getId(), nbExemplairesInit);

		assertNotNull("La liste de réservations ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservations est erronée.",2,vListReservationBDD.size());
	}

	/**
	 * Test de la méthode getListReservation(int utilisateurId, int bibliothequeId, int editionId,int nbExemplairesInit) dans le cas où l'utilisateur n'a pas déjà
	 * réservé l'édition et que la liste d'attente des réservations est vide.
	 * @throws Exception
	 */
	@Test
	public void getListReservationCase4() throws Exception{
		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,17);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);

		int nbExemplairesInit=1;

		when(reservationDaoMock.getListReservation(vBibliotheque1.getId(),vEdition1.getId()))
		.thenThrow(new NotFoundException("Aucun réservation pour l'édition concernée dans l'ensemble du réseau de bibliothèques!!!"));

		List<Reservation> vListReservationBDD=reservationManagerImpl
				.getListReservation(vUtilisateur1.getId(), vBibliotheque1.getId(), vEdition1.getId(), nbExemplairesInit);

		assertNotNull("La liste de réservations ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservations est erronée.",0,vListReservationBDD.size());
	}
	
	/**
	 * Test de la méthode reserverEdition(int utilisateurId, int bibliothequeId, int editionId) dans le cas où l'édition n'a jamais été réservée.
	 * @throws Exception
	 */
	@Test
	public void reserverEditionCase1() throws Exception{
		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,17);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);
		
		when(reservationDaoMock.getListReservation(vBibliotheque1.getId(), vEdition1.getId()))
		.thenThrow(new NotFoundException("Aucun réservation pour l'édition concernée dans l'ensemble du réseau de bibliothèques!!!"));
		
		reservationManagerImpl.reserverEdition(vUtilisateur1.getId(),vBibliotheque1.getId(),vEdition1.getId());
	}
	
	/**
	 * Test de la méthode reserverEdition(int utilisateurId, int bibliothequeId, int editionId) dans le cas où l'édition a déjà été réservée.
	 * @throws Exception
	 */
	@Test
	public void reserverEditionCase2() throws Exception{
		List<Reservation> vListReservation=new ArrayList<>();
		
		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,17);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);
		
		vListReservation.add(vReservation1);
		
		doReturn(vListReservation).when(reservationDaoMock).getListReservation(vBibliotheque1.getId(),vEdition1.getId());
		
		reservationManagerImpl.reserverEdition(vUtilisateur1.getId(),vBibliotheque1.getId(),vEdition1.getId());
	}
	
	/**
	 * Test de la méthode getListReservationUtilisateur(int utilisateurId) dans le cas où l'utilisateur a réservé plusieurs éditions.
	 * @throws Exception
	 */
	@Test
	public void getListReservationUtilisateurCase1() throws Exception {
		List<Reservation> vListReservation=new ArrayList<>();

		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,17);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);

		vListReservation.add(vReservation1);

		Reservation vReservation2=new Reservation();
		vReservation2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,18);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vReservation2.setDateReservation(vXGCDateDebut2);

		vReservation2.setUtilisateur(vUtilisateur1);
		
		Exemplaire vExemplaire2=new Exemplaire();
		vExemplaire2.setBibliotheque(vBibliotheque1);
		Edition vEdition2=new Edition();
		vEdition2.setId(3);
		vExemplaire2.setEdition(vEdition2);
		
		vReservation2.setExemplaire(vExemplaire2);
		vReservation2.setPrioriteReservation(1);

		vListReservation.add(vReservation2);
		
		when(reservationDaoMock.getListReservationUtilisateur(vUtilisateur1.getId())).thenReturn(vListReservation);
		
		List<Reservation> vListReservationBDD=reservationManagerImpl.getListReservationUtilisateur(vUtilisateur1.getId());
		
		assertNotNull("La liste de réservations ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservations est erronée.",2,vListReservationBDD.size());
	}
	
	/**
	 * Test de la méthode getListReservationUtilisateur(int utilisateurId) dans le cas où l'utilisateur n'a réservé aucune édition.
	 * On s'attend à lever une exception de type {@link NotFoundException}
	 * @throws Exception
	 */
	@Test(expected = NotFoundException.class)
	public void getListReservationUtilisateurCase2() throws Exception{
		when(reservationDaoMock.getListReservationUtilisateur(1)).thenThrow(new NotFoundException("Vous n'avez effectué aucune réservation pour le moment."));
		reservationManagerImpl.getListReservationUtilisateur(1);
	}
	
	/**
	 * Test de la méthode annulerReservation(int utilisateurId,int bibliothequeId,int editionId) dans le cas nominal.
	 * @throws Exception
	 */
	@Test
	public void annulerReservationCase1() throws Exception{
		List<Reservation> vListReservation=new ArrayList<>();

		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,17);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);

		vListReservation.add(vReservation1);

		Reservation vReservation2=new Reservation();
		vReservation2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,18);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vReservation2.setDateReservation(vXGCDateDebut2);

		Utilisateur vUtilisateur2=new Utilisateur();
		vUtilisateur2.setId(2);
		vReservation2.setUtilisateur(vUtilisateur2);
		vReservation2.setExemplaire(vExemplaire1);
		vReservation2.setPrioriteReservation(2);

		vListReservation.add(vReservation2);
		
		Utilisateur vUtilisateur3=new Utilisateur();
		vUtilisateur3.setId(3);
		
		doReturn(vListReservation).when(reservationDaoMock).getListReservation(vBibliotheque1.getId(),vEdition1.getId());
		
		reservationManagerImpl.annulerReservation(vUtilisateur3.getId(), vBibliotheque1.getId(), vEdition1.getId());
	}
	
	/**
	 * Test de la méthode getListReservationUpdated() dans le cas nominal.
	 * @throws Exception
	 */
	@Test
	public void getListReservationUpdatedCase1() throws Exception{
		List<Reservation> vListReservation=new ArrayList<>();

		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,10);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);
		
		GregorianCalendar vGregCalDateRecepMail1=new GregorianCalendar(2018,Calendar.DECEMBER,16);
		XMLGregorianCalendar vXGCDateRecepMail1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateRecepMail1);
		vReservation1.setDateReceptionMail(vXGCDateRecepMail1);

		vListReservation.add(vReservation1);

		Reservation vReservation2=new Reservation();
		vReservation2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,11);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vReservation2.setDateReservation(vXGCDateDebut2);

		Utilisateur vUtilisateur2=new Utilisateur();
		vUtilisateur2.setId(2);
		vReservation2.setUtilisateur(vUtilisateur2);
		vReservation2.setExemplaire(vExemplaire1);
		vReservation2.setPrioriteReservation(2);
		
		vListReservation.add(vReservation2);
		
		List<Reservation> vListReservation2=new ArrayList<>();
		vListReservation2.add(vReservation2);
		
		when(reservationDaoMock.getListAllReservation()).thenReturn(vListReservation);
		doReturn(vListReservation2).when(reservationDaoMock).getListReservation(vBibliotheque1.getId(),vEdition1.getId());
		
		List<Reservation> vListReservationBDD=reservationManagerImpl.getListReservationUpdated();
		
		assertNotNull("La liste de réservations ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservations est erronée.",1,vListReservationBDD.size());
	}
	
	/**
	 * Test de la méthode getListReservationUpdated() dans le cas où aucun utilisateur n'a reçu de mail.
	 * On s'attend à lever une exception de type {@link NotFoundException}
	 * @throws Exception
	 */
	@Test(expected = NotFoundException.class)
	public void getListReservationUpdatedCase2() throws Exception{
		List<Reservation> vListReservation=new ArrayList<>();

		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,10);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);
		
		vListReservation.add(vReservation1);

		Reservation vReservation2=new Reservation();
		vReservation2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,11);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vReservation2.setDateReservation(vXGCDateDebut2);

		Utilisateur vUtilisateur2=new Utilisateur();
		vUtilisateur2.setId(2);
		vReservation2.setUtilisateur(vUtilisateur2);
		vReservation2.setExemplaire(vExemplaire1);
		vReservation2.setPrioriteReservation(2);
		
		vListReservation.add(vReservation2);
		
		when(reservationDaoMock.getListAllReservation()).thenReturn(vListReservation);
		
		reservationManagerImpl.getListReservationUpdated();
	}
	
	/**
	 * Test de la méthode getListReservationUpdatedRetourEmprunt() dans le cas nominal :
	 * 	-	Un exemplaire est restitué à J-1.
	 * 	-	2 utilisateurs ont réservé cet exemplaire.
	 * 	-	Seul l'utilisateur qui a réservé en premier recevra un mail.
	 * @throws Exception
	 */
	@Test
	public void getListReservationUpdatedRetourEmpruntCase1() throws Exception{
		List<Emprunt> vListEmprunt=new ArrayList<>(); 
		
		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		
		Emprunt vEmprunt1=new Emprunt(); 
		vEmprunt1.setExemplaire(vExemplaire1);
		
		vListEmprunt.add(vEmprunt1);
		
		List<Reservation> vListReservation=new ArrayList<>();

		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,10);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);

		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);
		
		vListReservation.add(vReservation1);
		
		Reservation vReservation2=new Reservation();
		vReservation2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,11);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vReservation2.setDateReservation(vXGCDateDebut2);

		Utilisateur vUtilisateur2=new Utilisateur();
		vUtilisateur2.setId(2);
		vReservation2.setUtilisateur(vUtilisateur2);
		vReservation2.setExemplaire(vExemplaire1);
		vReservation2.setPrioriteReservation(2);
		
		vListReservation.add(vReservation2);
		
		int statutEmpruntId=3;
		Calendar vCalDateJourPrecedent=Calendar.getInstance();
		vCalDateJourPrecedent.add(Calendar.DATE, -1);
		Date vDateJourPrecedent=vCalDateJourPrecedent.getTime();
        vDateJourPrecedent = DateUtils.truncate(vDateJourPrecedent, Calendar.DATE);
		
		when(empruntDaoMock.getListEmprunt(vDateJourPrecedent,statutEmpruntId)).thenReturn(vListEmprunt);
		
		doReturn(vListReservation).when(reservationDaoMock).getListReservation(vExemplaire1.getBibliotheque().getId(),vExemplaire1.getEdition().getId());
		
		List<Reservation> vListReservationBDD=reservationManagerImpl.getListReservationUpdatedRetourEmprunt();
		
		assertNotNull("La liste de réservations ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservations est erronée.",1,vListReservationBDD.size());
	} 
	
	/**
	 * Test de la méthode getListAllReservation() dans le cas nominal.
	 * @throws Exception
	 */
	@Test
	public void getListAllReservationCase1() throws Exception{
		List<Reservation> vListReservation=new ArrayList<>();

		Reservation vReservation1=new Reservation();
		vReservation1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,10);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vReservation1.setDateReservation(vXGCDateDebut1);

		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vReservation1.setUtilisateur(vUtilisateur1);
		
		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vReservation1.setExemplaire(vExemplaire1);

		vReservation1.setPrioriteReservation(1);
		
		vListReservation.add(vReservation1);
		
		Reservation vReservation2=new Reservation();
		vReservation2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,11);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vReservation2.setDateReservation(vXGCDateDebut2);

		Utilisateur vUtilisateur2=new Utilisateur();
		vUtilisateur2.setId(2);
		vReservation2.setUtilisateur(vUtilisateur2);
		vReservation2.setExemplaire(vExemplaire1);
		vReservation2.setPrioriteReservation(2);
		
		vListReservation.add(vReservation2);
		
		when(reservationDaoMock.getListAllReservation()).thenReturn(vListReservation);
		
		List<Reservation> vListReservationBDD=reservationManagerImpl.getListAllReservation();
		
		assertNotNull("La liste de réservations ne doit pas être nul.",vListReservationBDD);
		assertEquals("La taille de la liste de réservations est erronée.",2,vListReservationBDD.size());
	}
	
	/**
	 * Test de la méthode getListAllReservation() dans le cas où il n'y a aucune réservation.
	 * On s'attend à lever une exception de type {@link NotFoundException}
	 * @throws Exception
	 */
	@Test(expected = NotFoundException.class)
	public void getListAllReservationCase2() throws Exception{
		when(reservationDaoMock.getListAllReservation()).thenThrow(new NotFoundException("Aucune réservation dans l'ensemble du réseau de bibliothèques!!!"));
		reservationManagerImpl.getListAllReservation();
	}
}
