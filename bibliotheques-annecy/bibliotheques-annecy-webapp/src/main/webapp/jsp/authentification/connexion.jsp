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
				<!-- Formulaire de connexion -->
				<div class="row" id="row-connexion">
					<div class="col-md-offset-4 col-md-4 col-lg-offset-4 col-lg-4" id="div-connexion">
						<h1 class="text-center">Se connecter</h1>
						<s:form action="connexion" method="POST">
							<s:actionerror />
							<s:textfield name="adresseMail" label="Adresse mail" requiredLabel="true" />
							<s:password name="motDePasse" label="Mot de passe" requiredLabel="true" />
							<s:submit value="Connexion" class="btn btn-primary" />
							<s:a class="btn btn-primary" action="page_creation_compte">Pas encore de compte?</s:a>
						</s:form>
					</div>
				</div>
			</section>
			
			<!-- Pied de page -->
			<%@ include file="../_include/footer.jsp"%>
		</div>
	</body>
</html>