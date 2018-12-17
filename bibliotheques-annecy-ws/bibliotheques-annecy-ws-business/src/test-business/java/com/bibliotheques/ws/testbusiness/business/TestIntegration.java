package com.bibliotheques.ws.testbusiness.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.bibliotheques.ws.model.bean.edition.Emprunt;

/**
 * Classe permettant d'effectuer des tests d'intégration 
 * au niveau du module business.
 * @author André Monnier
 *
 */
public class TestIntegration extends BusinessTestCase{

	@Test
	public void testSelect() throws Exception {
		List<Emprunt> vListEmpruntBDD=getManagerFactory().getEmpruntManager().getListEmpruntEnRetard();
		assertNotNull("La taille de la liste d'emprunt en retard ne doit pas être nul.",vListEmpruntBDD);
		assertEquals("La taille de la liste d'emprunt est erronée.",5,vListEmpruntBDD.size());
	}
}