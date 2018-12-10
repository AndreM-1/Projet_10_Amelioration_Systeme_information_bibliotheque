<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<%@ include file="../_include/head.jsp"%>	
	</head>
	<body>
		<div class="container">
			<!-- Header -->
			<%@ include file="../_include/header.jsp"%>
		
			<section id="page-confirmation-reservation">
				<div class="row" id="row-page-confirmation-reservation">
					<s:iterator value="listEmprunt" begin ="0" end ="0">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="detail-edition">	
							<!-- Les images doivent être responsives. Pour cela : 
					     	- Il ne faut surtout pas leur imposer une taille.
					     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
					     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
				     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->
							<div class="col-xs-4 col-sm-4 col-md-3 col-lg-3">
								<img class="img-responsive" src="<s:property value="exemplaire.edition.photo.nomPhoto"/>" alt="Photo Couverture livre"/>
							</div>
							<div class="col-xs-8 col-sm-8 col-md-9 col-lg-9">
								<p id="page-confirmation-reservation-titre"><s:property value="exemplaire.edition.ouvrage.titre"/></p>				
								<p id="page-confirmation-reservation-auteur"><em>Auteur : </em><s:property value="exemplaire.edition.ouvrage.auteur.prenom"/> <s:property value="exemplaire.edition.ouvrage.auteur.nom"/></p>
								<p id="page-confirmation-reservation-editeur"><em>Edité par</em> <s:property value="exemplaire.edition.editeur.nomEditeur"/> le <s:property value="exemplaire.edition.dateParution"/></p>
								<p id="page-confirmation-reservation-genre"><em>Genre :</em> <s:property value="exemplaire.edition.genre.genre"/></p>
								<p id="page-confirmation-reservation-isbn"><em>ISBN :</em> <s:property value="exemplaire.edition.isbn"/></p>
								<p id="page-confirmation-reservation-nb-pages"><em>Nombre de pages :</em> <s:property value="exemplaire.edition.nbPages"/> pages</p>
								<p id="page-confirmation-reservation-date-retour"><em>Date de retour prévue la plus proche  :</em> <s:property value="dateDeFin"/> <s:property value="notification"/> </p>
								<p id="page-confirmation-reservation-nb-reservations"><em>Nombre de réservations :</em> <s:property value="nbReservation"/>  </p>
								<p>
									<s:a action="confirmer_reservation" class="btn btn-primary">
										<s:param name="bibliothequeId" value="exemplaire.bibliotheque.id"/>
										<s:param name="editionId" value="exemplaire.edition.id"/>
										<s:param name="nbExemplairesInit" value="exemplaire.nbExemplairesInit"/>
										Confirmer la réservation
									</s:a>
								</p>
							</div>
						</div>
					</s:iterator>
				</div>
			</section>
		
			<!-- Footer -->
			<%@ include file="../_include/footer.jsp"%>
		</div>		
	</body>
</html>
