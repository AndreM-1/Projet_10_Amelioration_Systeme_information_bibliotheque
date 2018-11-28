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
							<li><s:a action="page_utilisateur_coord" id="mes-coordonnees">Mes coordonnées</s:a></li>
							<li><s:a action="page_utilisateur_mdp">Modifier mon mot de passe</s:a></li>
							<li><s:a action="page_utilisateur_gdp">Gestion des prêts</s:a></li>
						</ul>
					</nav>
					<s:actionerror/>
		
					<!-- Section comportant les coordonnées de l'utilisateur sélectionné -->
					<s:form id="form-utilisateur-coord" action="page_utilisateur_coord" method="POST">
		
						<div class="row">
							<div class="col-md-6 col-lg-6">
								<s:hidden name="id" label="Id" />
								<s:hidden name="validationFormulaire" label="Validation Formulaire" />
								<div class="row">
									<div class="col-md-12 col-lg-12">
										<div class="row">
											<div class="col-md-4 col-lg-4">
												<s:select name="civilite" label="Civilité" list="#{'Monsieur':'Monsieur','Madame':'Madame'}"
													emptyOption="false" requiredLabel="true" value="civilite" />
											</div>
											<div class="col-md-8 col-lg-8">
												<s:textfield name="nom" label="Nom" requiredLabel="true" />
											</div>
										</div>
		
										<s:textfield name="prenom" label="Prénom" requiredLabel="true" />
										<s:textfield name="pseudo" label="Pseudo" requiredLabel="true" />
										<s:textfield name="adresseMail" label="Adresse e-mail" requiredLabel="true" />
		
									</div>
		
								</div>
		
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="row">
									<div class="col-md-12 col-lg-12">
										<s:textfield name="telephone" label="Téléphone (XX-XX-XX-XX-XX)" />
										<s:textfield name="dateNaissance" label="Date de naissance (JJ/MM/AAAA)" />
										<s:textfield name="adresse" label="Adresse" />
										<div class="row">
											<div class="col-md-4 col-lg-4">
												<s:textfield name="codePostal" label="Code Postal" />
											</div>
											<div class="col-md-4 col-lg-4">
												<s:textfield name="ville" label="Ville" />
											</div>
											<div class="col-md-4 col-lg-4">
												<s:textfield name="pays" label="Pays"/>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
		
						<div class="row">
							<div class="col-md-offset-5 col-md-2 col-lg-offset-5 col-lg-2">
								<s:submit value="Valider" class="btn btn-primary btn-block" />
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