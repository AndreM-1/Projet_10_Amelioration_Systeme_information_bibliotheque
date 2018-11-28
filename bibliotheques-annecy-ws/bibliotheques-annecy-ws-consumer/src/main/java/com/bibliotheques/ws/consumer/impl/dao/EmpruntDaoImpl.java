package com.bibliotheques.ws.consumer.impl.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.EmpruntDao;
import com.bibliotheques.ws.consumer.contract.dao.ExemplaireDao;
import com.bibliotheques.ws.consumer.contract.dao.StatutEmpruntDao;
import com.bibliotheques.ws.consumer.contract.dao.UtilisateurDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.EmpruntRM;
import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

@Named
public class EmpruntDaoImpl extends AbstractDaoImpl implements EmpruntDao {
	
	@Inject
	private UtilisateurDao utilisateurDao;
	
	@Inject
	private StatutEmpruntDao statutEmpruntDao;
	
	@Inject
	private ExemplaireDao exemplaireDao;
	
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(EmpruntDaoImpl.class);
	
	@Override
	public List<Emprunt> getListEmprunt(int utilisateurId) throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getListEmprunt()");
		String vSQL = "SELECT * FROM public.emprunt where utilisateur_id ="+utilisateurId+" ORDER by statut_emprunt_id";
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		
		RowMapper<Emprunt> vRowMapper=new EmpruntRM(utilisateurDao,statutEmpruntDao,exemplaireDao);
		List<Emprunt> vListEmprunt=vJdbcTemplate.query(vSQL, vRowMapper);

		if(vListEmprunt.size()!=0){
			return vListEmprunt;
		}
		else
			throw new NotFoundException("Aucun emprunt effectué pour le moment.");
	}
	
	@Override
	public Emprunt getEmprunt (int utilisateurId, int editionId) throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getEmprunt()");
		String vSQL = "SELECT * FROM public.emprunt where utilisateur_id ="+utilisateurId+" AND exemplaire_edition_id = "+editionId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		
		RowMapper<Emprunt> vRowMapper=new EmpruntRM(utilisateurDao,statutEmpruntDao,exemplaireDao);
		List<Emprunt> vListEmprunt=vJdbcTemplate.query(vSQL, vRowMapper);

		if(vListEmprunt.size()!=0){
			return vListEmprunt.get(0);
		}
		else
			throw new NotFoundException("Aucun emprunt trouvé en BDD.");
		
	}
	
	@Override
	public void insertEmprunt(Date dateDeDebut,Date dateDeFin,boolean prolongation, int utilisateurId, int statutEmpruntId, int bibliothequeId, int editionId)
			throws FunctionalException {
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode insertEmprunt()");
		
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL même si le cas ne devrait
		//pas se présenter ici.
		String vSQL ="INSERT INTO public.emprunt (date_de_debut,date_de_fin,prolongation, utilisateur_id, statut_emprunt_id, exemplaire_bibliotheque_id,"
				+ "exemplaire_edition_id)  VALUES (?,?,?,?,?,?,?)";
		
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		try {
			vJdbcTemplate.update(vSQL, dateDeDebut,dateDeFin,prolongation,utilisateurId,statutEmpruntId,bibliothequeId,editionId);
		} catch (DuplicateKeyException e) {
			LOGGER.info("Vous avez déjà réservé cette édition dans une des bibliothèques de notre réseau.");
			throw new FunctionalException("Vous avez déjà réservé cette édition dans une des bibliothèques de notre réseau.");
		}	
	}
	
	@Override
	public void updateEmprunt(Date dateDeFin,boolean prolongation,Date dateDeProlongation,String dureeProlongation, int utilisateurId, int statutEmpruntId, 
			int bibliothequeId, int editionId) throws TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode updateEmprunt()");
		
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL même si le cas ne devrait
		//pas se présenter ici.
		String vSQL="UPDATE public.emprunt SET date_de_fin=?, prolongation=?, date_de_prolongation=?, duree_prolongation=?, statut_emprunt_id=?"
				+ " WHERE exemplaire_bibliotheque_id=? AND utilisateur_id=? AND exemplaire_edition_id=?";
		
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		
		try {
			vJdbcTemplate.update(vSQL,dateDeFin,prolongation,dateDeProlongation,dureeProlongation,statutEmpruntId,bibliothequeId,utilisateurId,editionId);
		} catch (DataAccessException e) {
			LOGGER.info(e.getMessage());
			throw new TechnicalException("Erreur technique lors de l'accès en base de données.");
		}
	}
	
	@Override
	public void updateEmprunt(int statutEmpruntId, int utilisateurId,int bibliothequeId, int editionId) throws TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode updateEmprunt()");
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL même si le cas ne devrait
		//pas se présenter ici.
		String vSQL="UPDATE public.emprunt SET statut_emprunt_id=? WHERE exemplaire_bibliotheque_id=? AND utilisateur_id=? AND exemplaire_edition_id=?";
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		try {
			vJdbcTemplate.update(vSQL,statutEmpruntId,bibliothequeId,utilisateurId,editionId);
		} catch (DataAccessException e) {
			LOGGER.info(e.getMessage());
			throw new TechnicalException("Erreur technique lors de l'accès en base de données.");
		}
	}
	
	@Override
	public void updateEmprunt(boolean prolongation, int utilisateurId,int bibliothequeId, int editionId) throws TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode updateEmprunt()");
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL même si le cas ne devrait
		//pas se présenter ici.
		String vSQL="UPDATE public.emprunt SET prolongation=? WHERE exemplaire_bibliotheque_id=? AND utilisateur_id=? AND exemplaire_edition_id=?";
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		try {
			vJdbcTemplate.update(vSQL,prolongation,bibliothequeId,utilisateurId,editionId);
		} catch (DataAccessException e) {
			LOGGER.info(e.getMessage());
			throw new TechnicalException("Erreur technique lors de l'accès en base de données.");
		}
	}
	
	
	@Override
	public List<Emprunt> getListEmpruntAvtUpd() throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getListEmpruntEnRetardAvtUpd()");
		String vSQL = "SELECT * FROM public.emprunt WHERE statut_emprunt_id IN (1,2) ORDER by utilisateur_id,id";
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		
		RowMapper<Emprunt> vRowMapper=new EmpruntRM(utilisateurDao,statutEmpruntDao,exemplaireDao);
		List<Emprunt> vListEmprunt=vJdbcTemplate.query(vSQL, vRowMapper);

		if(vListEmprunt.size()!=0){
			return vListEmprunt;
		}
		else
			throw new NotFoundException("Aucun emprunt en cours ou en retard dans l'ensemble du réseau de bibliothèques!!!");
	}
	
	@Override
	public List<Emprunt> getListEmpruntEnRetard() throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getListEmpruntEnRetard()");
		String vSQL = "SELECT * FROM public.emprunt WHERE statut_emprunt_id=2 ORDER by utilisateur_id,id";
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		
		RowMapper<Emprunt> vRowMapper=new EmpruntRM(utilisateurDao,statutEmpruntDao,exemplaireDao);
		List<Emprunt> vListEmprunt=vJdbcTemplate.query(vSQL, vRowMapper);

		if(vListEmprunt.size()!=0){
			return vListEmprunt;
		}
		else
			throw new NotFoundException("Aucun emprunt en retard dans l'ensemble du réseau de bibliothèques!!!");
	}
}