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
				<!-- Formulaire de création de compte -->
				<div class="row" id="row-creation-compte">
					<div class="col-md-offset-3 col-md-6 col-lg-offset-4 col-lg-4" id="div-connexion">
						<h1 class="text-center">S'inscrire</h1>
						<s:form action="page_creation_compte" method="POST">
							<s:actionerror/>
							<s:select name="civilite" label="Civilité" list="#{'Monsieur':'Monsieur','Madame':'Madame'}"
						  	emptyOption="false" requiredLabel="true" value="utilisateur.civilite" />
							<s:textfield name="nom" label="Nom" requiredLabel="true"/>
							<s:textfield name="prenom" label="Prénom" requiredLabel="true"/>
					        <s:textfield name="pseudo" label="Pseudo" requiredLabel="true"/>
					        <s:textfield name="adresseMail" label="Adresse e-mail" requiredLabel="true"/>
					        <s:password name="motDePasse" label="Mot de passe" requiredLabel="true"/>
					        <s:password name="confirmationMotDePasse" label="Confirmation du mot de passe" requiredLabel="true"/>
					       	<s:checkbox type="checkbox" name="conditionsUtilisation" label="J'ai lu et accepté les conditions d'utilisation" /> 
							<s:submit value="S'inscrire" class="btn btn-primary"/><s:a class="btn btn-primary" action="connexion">Déjà inscrit?</s:a> 
			 			</s:form>	
		 			</div>
				</div>
			</section>
			<!-- Pied de page -->
			<%@ include file="../_include/footer.jsp"%>
		</div>
	</body>
</html>