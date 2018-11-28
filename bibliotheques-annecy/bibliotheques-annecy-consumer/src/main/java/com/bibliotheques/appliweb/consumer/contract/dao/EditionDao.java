package com.bibliotheques.appliweb.consumer.contract.dao;

import java.util.List;

import com.bibliotheques.appliweb.model.bean.edition.Edition;
import com.bibliotheques.appliweb.model.exception.RechercheAvanceeEditionFault_Exception;
import com.bibliotheques.appliweb.model.exception.RechercheEditionFault_Exception;

/**
 * Interface EditionDao
 * @author André Monnier
 *
 */
public interface EditionDao {

	/**
	 * Méthode permettant de renvoyer la liste des éditions.
	 * @param nbEdition : Le nombre d'éditions souhaité.
	 * @return List
	 */
	List<Edition> getListEdition(int nbEdition);

	/**
	 * Méthode permettant de rechercher une édition à partir de son titre.
	 * @param titre : Le titre de l'édition
	 * @return List
	 * @throws RechercheEditionFault_Exception
	 */
	List<Edition> rechercheEdition(String titre) throws RechercheEditionFault_Exception;

	/**
	 * Méthode permettant de rechercher une édition en fonction de plusieurs critères.
	 * @param titre : Le titre de l'édition
	 * @param nomAuteur : Le nom de l'auteur
	 * @param nomEditeur : Le nom de l'éditeur
	 * @param anneeParution : L'année de parution
	 * @param genre : Le genre 
	 * @return List
	 * @throws RechercheAvanceeEditionFault_Exception
	 */
	List<Edition> rechercheAvanceeEdition(String titre, String nomAuteur, String nomEditeur, String anneeParution, String genre)
			throws RechercheAvanceeEditionFault_Exception;
}