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
							<li><s:a action="page_utilisateur_mdp" id="mon-mot-de-passe">Modifier mon mot de passe</s:a></li>
							<li><s:a action="page_utilisateur_gdp">Gestion des prêts</s:a></li>
						</ul>
					</nav>
					<s:actionerror />
					<s:actionmessage/>
		
					<!-- Section permettant à l'utilisateur de changer son mot de passe -->
					<s:form id="form-utilisateur-mdp" action="page_utilisateur_mdp" method="POST">
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<s:hidden name="id" label="Id" />
							 	<s:password name="ancienMotDePasse" label="Mot de passe actuel" requiredLabel="true"/>
							 	<s:password name="nouveauMotDePasse" label="Nouveau mot de passe" requiredLabel="true"/>
							 	<s:password name="confirmationNouveauMotDePasse" label="Confirmation du nouveau mot de passe" requiredLabel="true"/>
					 		</div>
					 	</div>
						<div class="row">
							<div class="col-md-4 col-lg-4">
								<div class="row">
									<div class="col-md-offset-6 col-md-6 col-lg-offset-6 col-lg-6">
										<s:submit value="Confirmer" class="btn btn-primary btn-block"/>	
									</div>
								</div>
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