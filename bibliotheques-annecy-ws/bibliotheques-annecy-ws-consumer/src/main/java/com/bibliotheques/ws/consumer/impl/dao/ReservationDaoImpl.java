package com.bibliotheques.ws.consumer.impl.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.ExemplaireDao;
import com.bibliotheques.ws.consumer.contract.dao.ReservationDao;
import com.bibliotheques.ws.consumer.contract.dao.UtilisateurDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.ReservationRM;
import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

@Named
public class ReservationDaoImpl extends AbstractDaoImpl implements ReservationDao {
	
	@Inject
	private UtilisateurDao utilisateurDao;
	
	@Inject
	private ExemplaireDao exemplaireDao;
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ReservationDaoImpl.class);

	@Override
	public Reservation getReservation(int utilisateurId, int editionId) throws NotFoundException {
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getReservation()");
		String vSQL = "SELECT * FROM public.reservation where utilisateur_id ="+utilisateurId+" AND exemplaire_edition_id = "+editionId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		
		RowMapper<Reservation> vRowMapper=new ReservationRM(utilisateurDao,exemplaireDao);
		List<Reservation> vListReservation=vJdbcTemplate.query(vSQL, vRowMapper);

		if(vListReservation.size()!=0){
			return vListReservation.get(0);
		}
		else
			throw new NotFoundException("Aucune réservation trouvée en BDD.");
	}
	
	@Override
	public List<Reservation> getListReservation(int bibliothequeId,int editionId) throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getListReservation()");
		String vSQL = "SELECT * FROM public.reservation WHERE exemplaire_bibliotheque_id="+bibliothequeId+" AND exemplaire_edition_id="+editionId+" ORDER by date_reservation ASC";
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		
		RowMapper<Reservation> vRowMapper=new ReservationRM(utilisateurDao,exemplaireDao);
		List<Reservation> vListReservation=vJdbcTemplate.query(vSQL, vRowMapper);

		if(vListReservation.size()!=0){
			return vListReservation;
		}
		else
			throw new NotFoundException("Aucun réservation pour l'édition concernée dans l'ensemble du réseau de bibliothèques!!!");
	}
	
	
	@Override
	public List<Reservation> getListReservationUtilisateur(int utilisateurId) throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getListReservationUtilisateur()");
		String vSQL = "SELECT * FROM public.reservation WHERE utilisateur_id="+utilisateurId+" ORDER by date_reservation ASC";
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		
		RowMapper<Reservation> vRowMapper=new ReservationRM(utilisateurDao,exemplaireDao);
		List<Reservation> vListReservation=vJdbcTemplate.query(vSQL, vRowMapper);
		
		if(vListReservation.size()!=0){
			return vListReservation;
		}
		else 
			throw new NotFoundException("Vous n'avez effectué aucune réservation pour le moment.");
	}
	
	@Override
	public List<Reservation> getListAllReservation() throws NotFoundException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode getListAllReservation()");
		String vSQL = "SELECT * FROM public.reservation ORDER BY date_reservation ASC";
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		
		RowMapper<Reservation> vRowMapper=new ReservationRM(utilisateurDao,exemplaireDao);
		List<Reservation> vListReservation=vJdbcTemplate.query(vSQL, vRowMapper);
		
		if(vListReservation.size()!=0){
			return vListReservation;
		}
		else 
			throw new NotFoundException("Aucune réservation dans l'ensemble du réseau de bibliothèques!!!");	
	}
	
	@Override
	public void insertReservation(Date dateReservation,int utilisateurId,int bibliothequeId,int editionId,int prioriteReservation) throws TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode insertReservation()");
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL même si le cas ne devrait
		//pas se présenter ici.
		String vSQL ="INSERT INTO public.reservation (date_reservation, utilisateur_id, exemplaire_bibliotheque_id, exemplaire_edition_id,priorite_reservation) VALUES (?,?,?,?,?)";
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		
		try {
			vJdbcTemplate.update(vSQL,dateReservation,utilisateurId,bibliothequeId,editionId,prioriteReservation);
		} catch (DataAccessException e) {
			throw new TechnicalException("Erreur technique lors de l'accès en base de données.");
		}	
	}
	
	@Override
	public void deleteReservation(int utilisateurId,int bibliothequeId,int editionId) throws TechnicalException {
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode deleteReservation()");
		String vSQL="DELETE FROM public.reservation WHERE utilisateur_id = ? AND exemplaire_bibliotheque_id=? AND exemplaire_edition_id=?";
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		
		try {
			vJdbcTemplate.update(vSQL,utilisateurId,bibliothequeId,editionId);
		} catch (DataAccessException e) {
			throw new TechnicalException("Erreur technique lors de l'accès en base de données.");
		}
	}
	
	@Override
	public void updateReservation(int id,int prioriteReservation) throws TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode updateReservation()");
		
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL même si le cas ne devrait
		//pas se présenter ici.
		String vSQL="UPDATE public.reservation SET priorite_reservation=? WHERE id=?";
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		
		try {
			vJdbcTemplate.update(vSQL,prioriteReservation,id);
		} catch (DataAccessException e) {
			throw new TechnicalException("Erreur technique lors de l'accès en base de données.");
		}
	}
	
	@Override
	public void updateReservation(int id,int prioriteReservation, Date dateReceptionMail) throws TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Consumer - Méthode updateReservation()");
		
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL même si le cas ne devrait
		//pas se présenter ici.
		String vSQL="UPDATE public.reservation SET priorite_reservation=?, date_reception_mail=? WHERE id=?";
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		
		try {
			vJdbcTemplate.update(vSQL,prioriteReservation,dateReceptionMail,id);
		} catch (DataAccessException e) {
			throw new TechnicalException("Erreur technique lors de l'accès en base de données.");
		}
	}
	
}