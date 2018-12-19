package com.bibliotheques.ws.business.impl.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bibliotheques.ws.business.contract.manager.ReservationManager;
import com.bibliotheques.ws.model.bean.edition.Emprunt;
import com.bibliotheques.ws.model.bean.edition.Reservation;
import com.bibliotheques.ws.model.exception.FunctionalException;
import com.bibliotheques.ws.model.exception.NotFoundException;
import com.bibliotheques.ws.model.exception.TechnicalException;

@Named
public class ReservationManagerImpl extends AbstractManager implements ReservationManager {

	private List<Reservation> listReservation;
	private List<Reservation> listReservationExpire;
	private List<Reservation> listReservationUpdated;
	private List<Emprunt> listEmprunt;
	private Reservation reservation;

	//Définition du LOGGER
	private static final Logger LOGGER=(Logger) LogManager.getLogger(ReservationManagerImpl.class);

	@Override
	public List<Reservation> getListReservation(int utilisateurId, int bibliothequeId, int editionId,
			int nbExemplairesInit) throws FunctionalException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListReservation()");
		listReservation= new ArrayList<>();
		//On vérifie que l'utilisateur n'a pas déjà réservé l'édition.
		//Vu que l'on a définit un index unique sur les colonnes utilisateur_id et exemplaire_edition_id,
		//on n'a pas besoin du champ bibliothequeId.
		try {
			//Si l'utilisateur a déjà réservé l'édition, on lève une FunctionalException.
			reservation=getDaoFactory().getReservationDao().getReservation(utilisateurId, editionId);
			throw new FunctionalException("Vous avez déjà réservé cette édition dans une des bibliothèques de notre réseau.");
		} catch (NotFoundException e) {
			//Si l'utilisateur ne l'a pas déjà réservée, on vérifie que la liste d'attente des réservations n'est pas complète.
			//Si la liste d'attente n'est pas complète, on retourne la liste des réservations.
			LOGGER.info(e.getMessage());
			try {
				listReservation=getDaoFactory().getReservationDao().getListReservation(bibliothequeId,editionId);
				LOGGER.info("Taille Liste réservations : "+listReservation.size());
				LOGGER.info("nbExemplairesInit : "+nbExemplairesInit);
				if(listReservation.size()>=2*nbExemplairesInit) {
					//Cas où la liste d'attente est complète, on lève donc une FunctionalException.
					throw new FunctionalException("Désolé, mais la liste d'attente des réservations pour cette édition est complète.");
				}else {
					//Cas où la liste d'attente n'est pas complète.
					return listReservation;
				}
			} catch (NotFoundException e1) {
				//Dans ce cas là, personne n'a réservé l'édition dans une bibliothèque donnée, donc c'est bon, la liste d'attente est vide.
				//On retourne une liste de réservation vide.
				LOGGER.info(e1.getMessage());
				LOGGER.info("Liste réservations :"+listReservation);
				LOGGER.info("Taille Liste réservations :"+listReservation.size());
				return listReservation;
			}	
		}
	}


	@Override
	public void reserverEdition(int utilisateurId, int bibliothequeId, int editionId) throws TechnicalException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode reserverEdition()");

		//Utilisation d'un TransactionStatus. On a besoin de lever une TechnicalException,
		//ce qui n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());

		//A ce stade, soit on a une liste d'attente de réservations qui n'est pas complète, soit on a une liste d'attente vide.
		//Dans les 2 cas, on peut effectuer une réservation.
		int prioriteReservation=1;
		Date dateReservation= new Date();
		try {
			listReservation=getDaoFactory().getReservationDao().getListReservation(bibliothequeId,editionId);
			prioriteReservation=listReservation.size()+1;
			try {
				getDaoFactory().getReservationDao().insertReservation(dateReservation,utilisateurId,bibliothequeId,editionId,prioriteReservation);
				getPlatformTransactionManager().commit(vTransactionStatus);
			} catch (TechnicalException e) {
				LOGGER.info(e.getMessage());
				getPlatformTransactionManager().rollback(vTransactionStatus);
				throw new TechnicalException(e.getMessage());
			}
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			try {
				getDaoFactory().getReservationDao().insertReservation(dateReservation,utilisateurId,bibliothequeId,editionId,prioriteReservation);
				getPlatformTransactionManager().commit(vTransactionStatus);
			} catch (TechnicalException e1) {
				LOGGER.info(e1.getMessage());
				getPlatformTransactionManager().rollback(vTransactionStatus);
				throw new TechnicalException(e1.getMessage());
			}
		}	
	}

	@Override
	public List<Reservation> getListReservationUtilisateur(int utilisateurId) throws NotFoundException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListReservationUtilisateur()");
		listReservation= new ArrayList<>();
		try {
			listReservation= getDaoFactory().getReservationDao().getListReservationUtilisateur(utilisateurId);
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		return listReservation;
	}

	@Override
	public void annulerReservation(int utilisateurId,int bibliothequeId,int editionId) throws TechnicalException  {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode annulerReservation()");

		//Utilisation d'un TransactionStatus. On a besoin de lever une TechnicalException,
		//ce qui n'est pas possible avec l'utilisation d'une classe anonyme du transaction template.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());

		//Tout d'abord, on supprime la réservation de la base de données
		try {
			getDaoFactory().getReservationDao().deleteReservation(utilisateurId,bibliothequeId,editionId);	
		} catch (TechnicalException e) {
			LOGGER.info(e.getMessage());
			getPlatformTransactionManager().rollback(vTransactionStatus);
			throw new TechnicalException(e.getMessage());
		}

		//On récupère la liste des réservations d'une édition dans une bibliothèque.
		listReservation= new ArrayList<>();

		try {
			//On récupère une liste qui a minimum 1 élément.
			listReservation=getDaoFactory().getReservationDao().getListReservation(bibliothequeId,editionId);
			int prioriteReservation=0;
			//On met à jour le champs priorite_reservation uniquement pour les lignes où le champs date_reception_mail est null.
			for(Reservation vReservation:listReservation) {
				if(vReservation.getDateReceptionMail()==null) {
					prioriteReservation+=1;
					try {
						getDaoFactory().getReservationDao().updateReservation(vReservation.getId(),prioriteReservation);
					} catch (TechnicalException e) {
						LOGGER.info(e.getMessage());
						getPlatformTransactionManager().rollback(vTransactionStatus);
						throw new TechnicalException(e.getMessage());
					}
				}
			}	
		} catch (NotFoundException e) {
			//Dans ce là, il n'y a rien à faire, pas besoin de mettre à jour le champ priorite_reservation.
			LOGGER.info(e.getMessage());
		}

		getPlatformTransactionManager().commit(vTransactionStatus);
	}

	@Override
	public List<Reservation> getListReservationUpdated() throws NotFoundException, TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListReservationUpdated()");
		
		listReservation = new ArrayList<>();
		listReservationUpdated = new ArrayList<>();
		//On récupère les réservations qui sont expirées, c'est à dire lorsque le champ date_reception_mail est non null
		//et que la date du jour a passé la date de réception du mail de 48H.
		try {
			listReservation=getDaoFactory().getReservationDao().getListAllReservation();
			listReservationExpire = new ArrayList<>();
			Calendar vCalDateDuJour=Calendar.getInstance(); 
			LOGGER.info("vCalDateDuJour : "+vCalDateDuJour);
			for(Reservation vReservation:listReservation) {
				if(vReservation.getDateReceptionMail()!=null) {
					Calendar vCalDateReceptionMail=vReservation.getDateReceptionMail().toGregorianCalendar();
					vCalDateReceptionMail.add(Calendar.DATE, 2);
					LOGGER.info("vCalDateReceptionMail : "+vCalDateReceptionMail);
					if(vCalDateReceptionMail.compareTo(vCalDateDuJour)<=0) {
						listReservationExpire.add(vReservation);
					}
				}
			}

			//Cas où il y a des lignes de réservation expirées.
			if(listReservationExpire.size()!=0) {
				TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
				for(Reservation vReservationExpire:listReservationExpire) {
					//Dans le cas où il y a des lignes de réservation expirées, il faut supprimer ces lignes.
					//Cela revient à annuler ces réservations.
					try {
						annulerReservation(vReservationExpire.getUtilisateur().getId(),vReservationExpire.getExemplaire().getBibliotheque().getId(),
								vReservationExpire.getExemplaire().getEdition().getId());
					} catch (TechnicalException e) {
						LOGGER.info(e.getMessage());
						getPlatformTransactionManager().rollback(vTransactionStatus);
						throw new TechnicalException(e.getMessage());
					}
					//Ensuite, il faut mettre à jour le premier élément de la liste de réservation si présent et le récupérer.
					try {
						listReservation=getDaoFactory().getReservationDao().getListReservation(vReservationExpire.getExemplaire().getBibliotheque().getId(), 
								vReservationExpire.getExemplaire().getEdition().getId());
						for(Reservation vReservation:listReservation) {
							if(vReservation.getDateReceptionMail()==null) {
								try {
									getDaoFactory().getReservationDao().updateReservation(vReservation.getId(), 1,new Date());
								} catch (TechnicalException e) {
									LOGGER.info(e.getMessage());
									getPlatformTransactionManager().rollback(vTransactionStatus);
									throw new TechnicalException(e.getMessage());
								}
								//On récupère l'objet de type reservation une fois la mise à jour effectuée.
								try {
									vReservation=getDaoFactory().getReservationDao().getReservation(vReservation.getUtilisateur().getId(), vReservation.getExemplaire().getEdition().getId());
								} catch (NotFoundException e) {
									LOGGER.info(e.getMessage());
									getPlatformTransactionManager().rollback(vTransactionStatus);
									throw new TechnicalException(e.getMessage());
								}
								listReservationUpdated.add(vReservation);
								break;
							}
						}
								
					} catch (NotFoundException e) {
						//Dans ce cas là, rien à faire. Personne d'autre n'avait réservé l'édition.
						LOGGER.info(e.getMessage());
					}	
				}
				getPlatformTransactionManager().commit(vTransactionStatus);
				
				if (listReservationUpdated.size()==0)
					throw new NotFoundException("Liste listReservationUpdated vide ");
				else
					return listReservationUpdated;
			}else {
				//Dans le cas où il n'y a aucune réservation expirée, il n'y a rien à faire.
				throw new NotFoundException ("Aucune réservation expirée !!!");
			}

		} catch (NotFoundException e) {
			//Dans ce cas, si on a aucune réservation dans l'ensemble du réseau de bibliothèques, il n'y a rien à faire.
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
	}
	
	@Override
	public List<Reservation> getListReservationUpdatedRetourEmprunt() throws NotFoundException, TechnicalException{
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListReservationUpdatedRetourEmprunt()");
		
		listEmprunt = new ArrayList<>();
		listReservation = new ArrayList<>();
		listReservationUpdated = new ArrayList<>();
		
		int statutEmpruntId=3;
		Calendar vCalDateJourPrecedent=Calendar.getInstance();
		vCalDateJourPrecedent.add(Calendar.DATE, -1);
		Date vDateJourPrecedent=vCalDateJourPrecedent.getTime();
	
		//ATTENTION, dans ce cas précis, il faut bien tronquer la date car on va faire une comparaison de type égalité en base de données
		//à partir de cette date.
        vDateJourPrecedent = DateUtils.truncate(vDateJourPrecedent, Calendar.DATE);
    	LOGGER.info("Date de retour effective : "+vDateJourPrecedent);
		
		//On récupère la liste des emprunts rendus à la bibliothèque le jour d'avant.
		try {
			listEmprunt = getDaoFactory().getEmpruntDao().getListEmprunt(vDateJourPrecedent, statutEmpruntId);
			LOGGER.info("Taille liste emprunt : "+listEmprunt.size());
		} catch (NotFoundException e) {
			//Dans ce cas là, si personne n'a restitué d'emprunt à la bibliothèque, rien à faire.
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		
		//On récupère une liste qui a minimun 1 emprunt.
		//On va parcourir cette liste pour voir si un emprunt donné a une liste d'attente de réservation.
		TransactionStatus vTransactionStatus= getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());
		for (Emprunt vEmprunt:listEmprunt) {
			try {
				listReservation=getDaoFactory().getReservationDao().getListReservation(vEmprunt.getExemplaire().getBibliotheque().getId(), vEmprunt.getExemplaire().getEdition().getId());
				
				//Dans ce cas là, l'emprunt restitué à la bibliothèque a minimum une liste d'attente de réservation de 1 élément.
				//On parcourt cette liste pour mettre à jour le premier élément.
				for(Reservation vReservation:listReservation) {
					if(vReservation.getDateReceptionMail()==null) {
						try {
							getDaoFactory().getReservationDao().updateReservation(vReservation.getId(), 1,new Date());
						} catch (TechnicalException e) {
							LOGGER.info(e.getMessage());
							getPlatformTransactionManager().rollback(vTransactionStatus);
							throw new TechnicalException(e.getMessage());
						}
						//On récupère l'objet de type reservation une fois la mise à jour effectuée.
						try {
							vReservation=getDaoFactory().getReservationDao().getReservation(vReservation.getUtilisateur().getId(), vReservation.getExemplaire().getEdition().getId());
						} catch (NotFoundException e) {
							LOGGER.info(e.getMessage());
							getPlatformTransactionManager().rollback(vTransactionStatus);
							throw new TechnicalException(e.getMessage());
						}	
						listReservationUpdated.add(vReservation);
						break;
					}
				}
				
			} catch (NotFoundException e) {
				//Dans ce cas là, rien à faire. L'emprunt restitué n'a pas de liste d'attente de réservation.
				LOGGER.info(e.getMessage());
			}		
		}	
		getPlatformTransactionManager().commit(vTransactionStatus);
		
		if (listReservationUpdated.size()==0)
			throw new NotFoundException("Liste listReservationUpdated vide ");
		else
			return listReservationUpdated;
	}
	

	@Override
	public List<Reservation> getListAllReservation() throws NotFoundException {
		LOGGER.info("Web Service : EditionService - Couche Business - Méthode getListAllReservation()");
		listReservation= new ArrayList<>();
		try {
			listReservation= getDaoFactory().getReservationDao().getListAllReservation();
		} catch (NotFoundException e) {
			LOGGER.info(e.getMessage());
			throw new NotFoundException(e.getMessage());
		}
		return listReservation;
	}
}
