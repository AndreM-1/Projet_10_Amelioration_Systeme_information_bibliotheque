package com.bibliotheques.ws.business.impl.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bibliotheques.ws.consumer.contract.DaoFactory;
import com.bibliotheques.ws.consumer.contract.dao.EmpruntDao;
import com.bibliotheques.ws.model.bean.edition.Bibliotheque;
import com.bibliotheques.ws.model.bean.edition.Edition;
import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.bean.edition.Exemplaire;
import com.bibliotheques.ws.model.bean.edition.StatutEmprunt;
import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.ws.model.exception.NotFoundException;

/**
 * Classe permettant d'effectuer des tests unitaires sur la classe {@link EmpruntManagerImpl}
 * @author André Monnier
 *
 */
public class EmpruntManagerImplTest {
	
	private static DaoFactory daoFactoryMock=mock(DaoFactory.class);
	private static PlatformTransactionManager platformTransactionManagerMock=mock(PlatformTransactionManager.class);
	private static TransactionStatus transactionStatusMock=mock(TransactionStatus.class);
	private static DefaultTransactionDefinition defaultTransactionDefinitionMock=mock(DefaultTransactionDefinition.class);
	private static EmpruntDao empruntDaoMock=mock(EmpruntDao.class);
	private EmpruntManagerImpl empruntManagerImpl=new EmpruntManagerImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AbstractManager.setDaoFactory(daoFactoryMock);
		AbstractManager.setPlatformTransactionManager(platformTransactionManagerMock);
		when(platformTransactionManagerMock.getTransaction(defaultTransactionDefinitionMock)).thenReturn(transactionStatusMock);
		when(daoFactoryMock.getEmpruntDao()).thenReturn(empruntDaoMock); 
	}

	/**
	 * Test de la méthode getListEmpruntEnRetard() avec une liste de 2 éléments : 1 élément qui aurait du être rendu avant la date du jour
	 * et l'autre qui doit être rendu ultérieurement.
	 */
	@Test
	public void getListEmpruntEnRetardCase1() throws Exception {
		List<Emprunt> vListEmprunt=new ArrayList<>(); 
		
		Emprunt vEmprunt1=new Emprunt(); 
		vEmprunt1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.NOVEMBER,15);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vEmprunt1.setDateDeDebut(vXGCDateDebut1);
		
		GregorianCalendar vGregCalDateFin1=new GregorianCalendar(2018,Calendar.DECEMBER,13);
		XMLGregorianCalendar vXGCDateFin1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateFin1);
		vEmprunt1.setDateDeFin(vXGCDateFin1);
		
		vEmprunt1.setProlongation(true);
		
		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vEmprunt1.setUtilisateur(vUtilisateur1);
		
		StatutEmprunt vStatutEmprunt=new StatutEmprunt();
		vStatutEmprunt.setId(1);
		vStatutEmprunt.setStatutEmprunt("En cours");
		vEmprunt1.setStatutEmprunt(vStatutEmprunt);
		
		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vEmprunt1.setExemplaire(vExemplaire1);
		
		vListEmprunt.add(vEmprunt1);
		
		Emprunt vEmprunt2=new Emprunt();
		vEmprunt2.setId(2);
		GregorianCalendar vGregCalDateDebut2=new GregorianCalendar(2018,Calendar.DECEMBER,14);
		XMLGregorianCalendar vXGCDateDebut2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut2);
		vEmprunt2.setDateDeDebut(vXGCDateDebut2);
		
		GregorianCalendar vGregCalDateFin2=new GregorianCalendar(2019,Calendar.JANUARY,11);
		XMLGregorianCalendar vXGCDateFin2=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateFin2);
		vEmprunt2.setDateDeFin(vXGCDateFin2);
		
		vEmprunt2.setProlongation(true);
		vEmprunt2.setUtilisateur(vUtilisateur1);
		vEmprunt2.setStatutEmprunt(vStatutEmprunt);
		
		Exemplaire vExemplaire2=new Exemplaire();
		vExemplaire2.setBibliotheque(vBibliotheque1);
		Edition vEdition2=new Edition();
		vEdition2.setId(3);
		vExemplaire2.setEdition(vEdition2);
		vEmprunt2.setExemplaire(vExemplaire2);
		
		vListEmprunt.add(vEmprunt2);
		
		when(empruntDaoMock.getListEmpruntAvtUpd()).thenReturn(vListEmprunt);	
		
		empruntManagerImpl.getListEmpruntEnRetard();
	}
	
	/**
	 * Test de la méthode getListEmpruntEnRetard() dans le cas où il n'y a aucun emprunt en retard dans l'ensemble du réseau de bibliothèques.
	 * On s'attend à lever une exception de type {@link NotFoundException}
	 * @throws Exception
	 */
	@Test(expected = NotFoundException.class)
	public void getListEmpruntEnRetardCase2() throws Exception{
		List<Emprunt> vListEmprunt=new ArrayList<>();
		
		Emprunt vEmprunt1=new Emprunt();
		vEmprunt1.setId(1);
		GregorianCalendar vGregCalDateDebut1=new GregorianCalendar(2018,Calendar.DECEMBER,15);
		XMLGregorianCalendar vXGCDateDebut1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateDebut1);
		vEmprunt1.setDateDeDebut(vXGCDateDebut1);
		
		GregorianCalendar vGregCalDateFin1=new GregorianCalendar(2019,Calendar.JANUARY,12);
		XMLGregorianCalendar vXGCDateFin1=DatatypeFactory.newInstance().newXMLGregorianCalendar(vGregCalDateFin1);
		vEmprunt1.setDateDeFin(vXGCDateFin1);
		
		vEmprunt1.setProlongation(true);
		
		Utilisateur vUtilisateur1=new Utilisateur();
		vUtilisateur1.setId(1);
		vEmprunt1.setUtilisateur(vUtilisateur1);
		
		StatutEmprunt vStatutEmprunt=new StatutEmprunt();
		vStatutEmprunt.setId(1);
		vStatutEmprunt.setStatutEmprunt("En cours");
		
		vEmprunt1.setStatutEmprunt(vStatutEmprunt);
		
		Exemplaire vExemplaire1=new Exemplaire();
		Bibliotheque vBibliotheque1= new Bibliotheque();
		vBibliotheque1.setId(1);
		vExemplaire1.setBibliotheque(vBibliotheque1);
		Edition vEdition1=new Edition();
		vEdition1.setId(1);
		vExemplaire1.setEdition(vEdition1);
		vEmprunt1.setExemplaire(vExemplaire1);
		
		vListEmprunt.add(vEmprunt1);
		
		when(empruntDaoMock.getListEmpruntAvtUpd()).thenReturn(vListEmprunt);
		when(empruntDaoMock.getListEmpruntEnRetard()).thenThrow(new NotFoundException("Aucun emprunt en retard dans l'ensemble du réseau de bibliothèques!!!"));
		empruntManagerImpl.getListEmpruntEnRetard();
	}
	
	/**
	 * Test de la méthode getListEmpruntEnRetard() dans le cas où il n'y a aucun emprunt en cours ou non rendu à temps dans l'ensemble du réseau
	 * de bibliothèques (cas possible mais très peu probable). On s'attend à lever une exception de type {@link NotFoundException}
	 * @throws Exception
	 */
	@Test(expected = NotFoundException.class)
	public void getListEmpruntEnRetardCase3() throws Exception {
		when(empruntDaoMock.getListEmpruntAvtUpd()).thenThrow(new NotFoundException("Aucun emprunt en cours ou en retard dans l'ensemble du réseau de bibliothèques!!!"));
		empruntManagerImpl.getListEmpruntEnRetard();
	}
	

}
