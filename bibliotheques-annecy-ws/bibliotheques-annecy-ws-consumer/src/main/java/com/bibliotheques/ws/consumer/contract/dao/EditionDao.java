package com.bibliotheques.ws.consumer.contract.dao;

import java.util.List;

import com.bibliotheques.ws.model.bean.edition.Edition;
import com.bibliotheques.ws.model.exception.NotFoundException;

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
	 * Méthode permettant de renvoyer un objet de type Edition en fonction de son identifiant.
	 * @param editionId : L'identifiant de l'édition
	 * @return Un objet de type {@link Edition}
	 */
	Edition getEdition(int editionId);
	
	/**
	 * Méthode permettant de rechercher une édition à partir de son titre.
	 * @param titre : Le titre de l'édition
	 * @return List
	 * @throws NotFoundException
	 */
	List<Edition> rechercheEdition(String titre) throws NotFoundException;
	
	
	/**
	 * Méthode permettant de rechercher une édition en fonction de plusieurs critères.
	 * @param titre : Le titre de l'édition
	 * @param nomAuteur : Le nom de l'auteur
	 * @param nomEditeur : Le nom de l'éditeur
	 * @param anneeParution : L'année de parution
	 * @param genre : Le genre 
	 * @return List
	 * @throws NotFoundException
	 */
	List<Edition> rechercheAvanceeEdition(String titre, String nomAuteur, String nomEditeur, String anneeParution, String genre)
			throws NotFoundException;

}