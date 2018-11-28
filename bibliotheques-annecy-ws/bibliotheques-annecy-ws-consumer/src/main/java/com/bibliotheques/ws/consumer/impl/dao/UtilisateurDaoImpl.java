package com.bibliotheques.ws.consumer.impl.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.bibliotheques.ws.consumer.contract.dao.UtilisateurDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.utilisateur.UtilisateurRM;
import com.bibliotheques.ws.model.bean.utilisateur.Utilisateur;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;

@Named
public class UtilisateurDaoImpl extends AbstractDaoImpl implements UtilisateurDao{
	
	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(UtilisateurDaoImpl.class);

	@Override
	public Utilisateur getUtilisateur(String adresseMail) throws NotFoundException {
		
		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL.
		LOGGER.info("Web Service : UtilisateurService - Couche Consumer - Méthode getUtilisateur");
		String vSQL="SELECT * FROM public.utilisateur WHERE adresse_mail=?";
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Utilisateur> vRowMapper=new UtilisateurRM();
		List<Utilisateur> vListUtilisateur = vJdbcTemplate.query(vSQL, vRowMapper, adresseMail);

		if(vListUtilisateur.size()!=0)	{
			return vListUtilisateur.get(0);
		}
		else
			throw new NotFoundException("Web Service : UtilisateurService - Couche Consumer - Méthode getUtilisateur - Aucun utilisateur correspondant à l'adresse mail demandée.");
		
	}
	
	
	@Override
	public Utilisateur getUtilisateur(int id) throws NotFoundException {
		LOGGER.info("Web Service : UtilisateurService - Couche Consumer - Méthode getUtilisateur");
		String vSQL="SELECT * FROM public.utilisateur WHERE id="+id;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Utilisateur> vRowMapper=new UtilisateurRM();
		List<Utilisateur> vListUtilisateur=vJdbcTemplate.query(vSQL, vRowMapper);

		if(vListUtilisateur.size()!=0)	{
			return vListUtilisateur.get(0);
		}
		else
			throw new NotFoundException("Web Service : UtilisateurService - Couche Consumer - Méthode getUtilisateur - Aucun utilisateur correspondant à l'id demandé.");
		
	}
	
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws FunctionalException{
		
		//En procédant ainsi, on évite les problèmes d'injection SQL. Pas besoin de requête préparée dans ce cas.
		String vSQL="INSERT INTO public.utilisateur(civilite,nom,prenom,pseudo,adresse_mail,salt,mot_de_passe_securise,pays) VALUES "
				+ "(:civilite,:nom,:prenom,:pseudo,:adresseMail,:salt,:motDePasseSecurise,:pays)";	
		SqlParameterSource vParams=new BeanPropertySqlParameterSource(utilisateur);
		NamedParameterJdbcTemplate vJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());

		try {
			vJdbcTemplate.update(vSQL,vParams);
		} catch (DuplicateKeyException vEx) {
			LOGGER.info("Le pseudo ou l'adresse mail existe déjà.");
			throw new FunctionalException("Le pseudo ou l'adresse mail existe déjà.");
		}
	}
	
	@Override
	public void updateCoordUtilisateur(int id, String civilite, String nom, String prenom, String pseudo,
			String adresseMail, String telephone, XMLGregorianCalendar dateNaissance, String adresse, String codePostal,
			String ville, String pays) throws FunctionalException {
		
		LOGGER.info("Web Service : UtilisateurService - Couche Consumer - Méthode updateCoordUtilisateur");

		//ATTENTION, il faut bien procéder ainsi en utilisant une requête préparée pour éviter les problèmes d'injection SQL.
		String vSQL="UPDATE public.utilisateur SET civilite=?, nom=?, prenom=?, pseudo=?, adresse_mail=?, telephone=?, date_naissance=?, adresse=?, "
				+ "code_postal=?, ville=?, pays=? WHERE id=?";
				
		LOGGER.info("Requête SQL Update coordonnées utilisateur : "+vSQL);
		
		//Spring n'arrive pas à convertir le format XMLGregorianCalendar reçu en format Date utilisé par la BDD.
		//On doit donc convertir le XMLGregorianCalendar en Date. A cause de cela, on ne peut pas utiliser directement le bean Utilisateur.
		LOGGER.info("Date de naissance en format XMLGregorianCalendar : "+dateNaissance);
		Date dateNaissanceFormatDate;
		
		if(dateNaissance!=null) {
			dateNaissanceFormatDate=dateNaissance.toGregorianCalendar().getTime();
			LOGGER.info("Date de naissance en format Date : "+dateNaissanceFormatDate);
		}else {
			dateNaissanceFormatDate=null;
		}
		
		JdbcTemplate vJdbcTemplate=new JdbcTemplate(getDataSource());
		
		try {
			vJdbcTemplate.update(vSQL,civilite,nom,prenom,pseudo,adresseMail,telephone,dateNaissanceFormatDate,adresse,codePostal,ville,pays,id);
		} catch (DuplicateKeyException vEx) {
			LOGGER.info("Le pseudo ou l'adresse mail existe déjà.");
			throw new FunctionalException("Le pseudo ou l'adresse mail existe déjà.");
		}
	}
	
	@Override
	public void updateMdpUtilisateur (Utilisateur utilisateur) {
		
		//En procédant ainsi, on évite les problèmes d'injection SQL. Pas besoin de requête préparée dans ce cas.
		String vSQL="UPDATE public.utilisateur SET mot_de_passe_securise=:motDePasseSecurise, salt=:salt WHERE id=:id";
		SqlParameterSource vParams=new BeanPropertySqlParameterSource(utilisateur);
		NamedParameterJdbcTemplate vJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
		vJdbcTemplate.update(vSQL,vParams);
	}
	
}