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
				<h1 class="text-center">Inscriptions et tarifs</h1>
				
				<p class="text-justify">Pour vous inscrire, vous devez présenter à la bibliothèque : </p>
				<ul class="text-justify">
					<li>Une pièce d'identité</li>
					<li>Un justificatif de domicile de moins de 3 mois</li>
				</ul>
				<p class="text-justify">Abonnement plein tarif : </p>
				<ul class="text-justify">
					<li>Adultes à partir de 25 ans : 22 euros</li>
				</ul>				
				<p class="text-justify">Abonnement gratuit :</p>
				<ul class="text-justify">
					<li>Jeunes de moins de 25 ans</li>
					<li>Etudiants (sur présentation de la carte d'étudiant)</li>
					<li>Adultes non imposables (sur présentation de votre dernier avis de non-imposition)</li>
				</ul>
				<p class="text-justify">Bonne lecture !!!</p>
				
			</section>
			
			<!-- Footer -->
			<%@ include file="../_include/footer.jsp"%>
		</div>
	</body>
</html>