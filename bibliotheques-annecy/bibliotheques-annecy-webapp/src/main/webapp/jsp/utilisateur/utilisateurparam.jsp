<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<%@ include file="../_include/head.jsp"%>
	</head>
	<body>
	
		<div class="container">
			<!-- Header -->
			<%@ include file="../_include/header.jsp"%>

			<section>
				<s:if test="#session.user">
					<!-- Menu de navigation -->
					<nav id="nav-profil-utilisateur">
						<ul>
							<li><s:a action="page_utilisateur_coord">Mes coordonnées</s:a></li>
							<li><s:a action="page_utilisateur_mdp">Mon mot de passe</s:a></li>
							<li><s:a action="page_utilisateur_gdp">Gestion prêts/réservations</s:a></li>
							<li><s:a action="page_utilisateur_param" id="mes-parametres">Paramètres</s:a></li>
						</ul>
					</nav>
					<s:actionerror />
					<s:actionmessage/>
		
					<!-- Section permettant à l'utilisateur de changer ses paramètres -->
					<s:form id="form-utilisateur-param" action="page_utilisateur_param" method="POST">
						<div class="row">
							<div class="col-sm-12 col-md-12 col-lg-12">
								<s:hidden name="id" label="Id" />
								<s:hidden name="validationFormulaire" label="Validation Formulaire" />
							 	<s:checkbox type="checkbox" name="mailRappelPret" label="Activer le mail de rappel pour les prêts arrivant à expiration dans 5 jours ou moins" /> 
					 		</div>
					 	</div>
					 	<div class="row">
					 		<div class="col-xs-4 col-sm-3 col-md-3 col-lg-3">
					 			<s:submit value="Confirmer" class="btn btn-primary btn-block"/>
					 		</div>
					 	</div>
					</s:form>
				</s:if>
			</section>
			
			<!-- Pied de page -->
			<%@ include file="../_include/footer.jsp"%>
	
		</div>
	
	</body>
</html>