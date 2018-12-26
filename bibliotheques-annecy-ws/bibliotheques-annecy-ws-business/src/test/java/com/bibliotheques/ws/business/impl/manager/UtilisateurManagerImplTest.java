package com.bibliotheques.ws.business.impl.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bibliotheques.ws.business.contract.manager.UtilisateurManager;
import com.bibliotheques.ws.consumer.contract.DaoFactory;
import com.bibliotheques.ws.consumer.contract.dao.UtilisateurDao;

/**
 * Classe permettant d'effectuer des tests unitaires sur la classe {@link UtilisateurManagerImpl}
 * @author André Monnier
 *
 */
public class UtilisateurManagerImplTest {

	private static DaoFactory daoFactoryMock=mock(DaoFactory.class);
	private static PlatformTransactionManager platformTransactionManagerMock=mock(PlatformTransactionManager.class);
	private static TransactionStatus transactionStatusMock=mock(TransactionStatus.class);
	private static DefaultTransactionDefinition defaultTransactionDefinitionMock=mock(DefaultTransactionDefinition.class);
	private static UtilisateurDao utilisateurDaoMock=mock(UtilisateurDao.class);
	private UtilisateurManager utilisateurManagerImpl=new UtilisateurManagerImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AbstractManager.setDaoFactory(daoFactoryMock);
		AbstractManager.setPlatformTransactionManager(platformTransactionManagerMock);
		when(platformTransactionManagerMock.getTransaction(defaultTransactionDefinitionMock)).thenReturn(transactionStatusMock);
		when(daoFactoryMock.getUtilisateurDao()).thenReturn(utilisateurDaoMock); 
	}
	
	/**
	 * Test de la méthode updateParametresUtilisateur(int id, boolean mailRappelPret) dans le cas nominal.
	 * @throws Exception
	 */
	@Test
	public void updateParametresUtilisateurCase1() throws Exception{
		utilisateurManagerImpl.updateParametresUtilisateur(1, true);
	}
	
}
