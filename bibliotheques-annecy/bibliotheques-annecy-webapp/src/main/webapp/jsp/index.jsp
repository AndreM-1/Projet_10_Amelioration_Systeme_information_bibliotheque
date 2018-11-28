<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<%@ include file="_include/head.jsp"%>	
	</head>
	<body>
		<div class="container">
			<!-- Header -->
			<%@ include file="_include/header.jsp"%>
		
			<section>
				<h1 class="text-center">Sélection <s:property value="moisCourant"/> <s:property value="anneeCourante"/></h1>
				
				<!-- Ne pas oublier de déclarer la classe row sinon comportements inattendus comme des liens Struts non fonctionnels lorsque l'on
				réduit la taille de l'écran ou la limite de la balise section. -->
				<div class="row" id="row-accueil">
					<s:iterator value="listEdition">
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6" id="edition-accueil">	
							<!-- Les images doivent être responsives. Pour cela : 
					     	- Il ne faut surtout pas leur imposer une taille.
					     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
					     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
				     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->
							<div class="col-xs-4 col-sm-4 col-md-5 col-lg-5">
								<img src="<s:property value="photo.nomPhoto"/>" alt="Photo Couverture livre" class="img-responsive"/>
							</div>
							<div class="col-xs-8 col-sm-8 col-md-7 col-lg-7">
								<p id="edition-accueil-titre">
									<s:a action="page_detail_edition">
									<!-- On met le lien sur le titre mais c'est bien l'id de l'édition que l'on passe comme paramètre -->
										<s:param name="id" value="id"/>
										<s:property value="ouvrage.titre"/>
									</s:a>
								</p>				
								<p><em>Auteur :</em> <s:property value="ouvrage.auteur.prenom"/> <s:property value="ouvrage.auteur.nom"/></p>
								<p id="edition-accueil-editeur"><em>Edité par</em> <s:property value="editeur.nomEditeur"/> le <s:property value="dateParution"/></p>
								<p id="edition-accueil-genre"><em>Genre :</em> <s:property value="genre.genre"/></p>
							</div>
						</div>
					</s:iterator>
				</div>
			</section>
		
			<!-- Footer -->
			<%@ include file="_include/footer.jsp"%>
		</div>		
	</body>
</html>
