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
			
			<section>
				<!-- Formulaire recherche avancée -->
				<div class="row" id="row-recherche-avancee">
					<div class="col-xs-12 col-sm-12 col-md-offset-3 col-md-6 col-lg-offset-4 col-lg-4" id="div-recherche-avancee">
						<h1 class="text-center">Recherche avancée</h1>
						<s:form action="recherche_edition_avancee" method="POST">
							<s:actionerror/>
							<s:textfield name="titre" label="Titre" requiredLabel="true"/>
							<s:textfield name="nomAuteur" label="Auteur"/>
							<s:select headerKey="-1" headerValue="Sélectionner un éditeur" name="nomEditeur" label="Editeur" list="listNomEditeur"/>
							<s:select headerKey="-1" headerValue="Sélectionner une année" name="anneeParution" label="Année de publication" list="listAnneeParution"/>
							<s:select headerKey="-1" headerValue="Sélectionner un genre" name="genre" label="Genre" list="listGenre"/>
							<s:submit value="Valider" class="btn btn-primary"/>
			 			</s:form>	
		 			</div>
				</div>
			</section>
			<!-- Pied de page -->
			<%@ include file="../_include/footer.jsp"%>
		</div>
	</body>
</html>