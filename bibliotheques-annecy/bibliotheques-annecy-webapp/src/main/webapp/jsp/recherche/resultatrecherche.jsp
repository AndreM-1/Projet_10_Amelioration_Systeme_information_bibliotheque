<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="../_include/head.jsp"%>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<%@ include file="../_include/header.jsp"%>

		<section id="resultat-recherche">
			<div class="row" id="row-resultat-recherche">
				<s:if test="%{editionTrouve==false}">
					<h4><s:actionmessage/></h4>
				</s:if>
				<s:iterator value="listEdition">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="resultat-recherche-edition">
						<!-- Les images doivent être responsives. Pour cela : 
				     	- Il ne faut surtout pas leur imposer une taille.
				     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
				     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
			     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->						
						<div class="col-xs-3 col-sm-3 col-md-2 col-lg-2">
							<img class="img-responsive" src="<s:property value="photo.nomPhoto"/>" alt="Photo Couverture livre"/>
						</div>
						<div class="col-xs-9 col-sm-9 col-md-10 col-lg-10">
							<p id="ed-res-rech-titre">
								<s:a action="page_detail_edition">
									<!-- On met le lien sur le titre mais c'est bien l'id de l'édition que l'on passe comme paramètre -->
									<s:param name="id" value="id" />
									<s:property value="ouvrage.titre" />
								</s:a>
							</p>
							<p id="ed-res-rech-auteur"><em>Auteur :</em> <s:property value="ouvrage.auteur.prenom" /> <s:property value="ouvrage.auteur.nom" /></p>
							<p id="ed-res-rech-editeur"><em>Edité par</em> <s:property value="editeur.nomEditeur" /> le <s:property value="dateParution" /></p>
							<p id="ed-res-rech-genre"><em>Genre :</em> <s:property value="genre.genre" /></p>
							<p id="ed-res-rech-isbn"><em>ISBN :</em> <s:property value="isbn"/></p>
							<p id="ed-res-rech-nb-pages"><em>Nombre de pages :</em> <s:property value="nbPages"/> pages</p>
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
