package com.bibliotheques.ws.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bibliotheques.ws.consumer.contract.dao.EditeurDao;
import com.bibliotheques.ws.consumer.contract.dao.EditionDao;
import com.bibliotheques.ws.consumer.contract.dao.GenreDao;
import com.bibliotheques.ws.consumer.contract.dao.OuvrageDao;
import com.bibliotheques.ws.consumer.contract.dao.PhotoDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.EditionRM;
import com.bibliotheques.ws.model.bean.edition.Edition;
import com.bibliotheques.ws.model.exception.NotFoundException;


@Named
public class EditionDaoImpl extends AbstractDaoImpl implements EditionDao{
	
	@Inject
	private OuvrageDao ouvrageDao;
	
	@Inject
	private PhotoDao photoDao;
	
	@Inject
	private EditeurDao editeurDao;
	
	@Inject
	private GenreDao genreDao;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EditionDaoImpl.class);

	
	@Override
	public List<Edition> getListEdition(int nbEdition){
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getListEdition()");
		String vSQL = "SELECT * FROM public.edition ORDER BY date_parution DESC LIMIT "+nbEdition;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Edition> vRowMapper=new EditionRM(ouvrageDao,photoDao,editeurDao,genreDao);
		List<Edition> vListEdition = vJdbcTemplate.query(vSQL, vRowMapper);

		return vListEdition;
	}
	
	@Override
	public Edition getEdition(int editionId) {
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getEdition()");
		String vSQL="SELECT * FROM public.edition WHERE id="+editionId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Edition> vRowMapper=new EditionRM(ouvrageDao,photoDao,editeurDao,genreDao);
		List<Edition> vListEdition=vJdbcTemplate.query(vSQL, vRowMapper);
		return vListEdition.get(0);
	}
	
	@Override
	public List<Edition> rechercheEdition(String titre) throws NotFoundException{
		
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL.
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode rechercheEdition()");
		String vSQL="SELECT edition.id, edition.isbn, edition.date_parution, edition.nb_pages, edition.format, edition.genre_id, edition.editeur_id, "
				+ "edition.photo_id, edition.ouvrage_id FROM public.edition INNER JOIN public.ouvrage ON edition.ouvrage_id=ouvrage.id "
				+ "WHERE REPLACE(TRIM(LOWER(ouvrage.titre)),' ','')=?";
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Edition> vRowMapper=new EditionRM(ouvrageDao,photoDao,editeurDao,genreDao);
		List<Edition> vListEdition = vJdbcTemplate.query(vSQL, vRowMapper, titre);

		if(vListEdition.size()!=0)	{
			return vListEdition;
		}
		else
			throw new NotFoundException("Aucun livre ne correspond à votre recherche.");
	}
	
	@Override
	public List<Edition> rechercheAvanceeEdition(String titre, String nomAuteur, String nomEditeur,
			String anneeParution, String genre) throws NotFoundException{
		
		//ATTENTION, il faut bien procéder ainsi pour éviter les problèmes d'injection SQL. Pas besoin de requête préparée dans ce cas.
		//A noter la subtilité WHERE 1=1 pour inclure la clause WHERE dès le départ.
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode rechercheAvanceeEdition()");
		String vSQL="SELECT edition.id, edition.isbn, edition.date_parution, edition.nb_pages, edition.format, edition.genre_id, edition.editeur_id, "
				+ "edition.photo_id, edition.ouvrage_id FROM public.edition "
				+ "INNER JOIN public.ouvrage ON edition.ouvrage_id=ouvrage.id "
				+ "INNER JOIN public.editeur ON edition.editeur_id=editeur.id "
				+ "INNER JOIN public.genre ON edition.genre_id=genre.id "
				+ "INNER JOIN public.auteur ON ouvrage.auteur_id=auteur.id "
				+ "WHERE 1=1 "
				+ "AND REPLACE(TRIM(LOWER(ouvrage.titre)),' ','')=:titre ";
		
		//On définit une MapSqlParameterSource dans laquelle on va mapper la valeur de nos paramètres d'entrée à un identifiant de type String.
		//On va prendre le même nom pour cet identifiant.
		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("titre", titre);
		
		int concatSql=0;
		
		//A part le titre, les autres champs sont facultatifs.
		//On est donc obligé de procéder comme indiqué ci-dessous en construisant la requête SQL pas à pas
		//étant donné que le nombre de paramètres d'entrée de la requête peut varier.
		if(!nomAuteur.trim().isEmpty()) {
			vSQL+= "AND REPLACE(TRIM(LOWER(auteur.prenom || auteur.nom)),' ','')=:nomAuteur ";
			concatSql++;
			vParams.addValue("nomAuteur", nomAuteur);
		}
			
		if(!nomEditeur.trim().isEmpty()) {
			vSQL+="AND editeur.nom_editeur=:nomEditeur ";
			concatSql++;
			vParams.addValue("nomEditeur", nomEditeur);
		}
			
		if(!anneeParution.trim().isEmpty()) {
			vSQL+=" AND to_char(date_parution,'YYYY') =:anneeParution ";
			concatSql++;
			vParams.addValue("anneeParution", anneeParution);
		}
			
		if(!genre.trim().isEmpty()) {
			vSQL+="AND genre.genre=:genre ";
			concatSql++;
			vParams.addValue("genre", genre);
		}
			
		LOGGER.info("Web Service : EditionService - Couche Consumer - vSQL = "+ vSQL);		
		LOGGER.info("Web Service : EditionService - Couche Consumer - concatSql = "+ concatSql);	
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource()); 
		RowMapper<Edition> vRowMapper=new EditionRM(ouvrageDao,photoDao,editeurDao,genreDao);
		List<Edition> vListEdition =vJdbcTemplate.query(vSQL,vParams,vRowMapper);

		if(vListEdition.size()!=0)	{
			return vListEdition;
		}
		else
			throw new NotFoundException("Aucun livre ne correspond à votre recherche.");
		
	}
}