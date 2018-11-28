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
				<h1 class="text-center">Conditions de prêt</h1>
				<p class="text-justify">Vous pouvez emprunter plusieurs livres en même temps.</p>
				<p class="text-justify">Pour cela, vous devez d'abord vous authentifier ou créer un compte si ce n'est pas déjà fait.</p>
				<p class="text-justify">Les prêts sont pour une période de 4 semaines.</p>
				<p class="text-justify">Les prêts sont prolongeables une seule fois pour une période de temps de 4 semaines également.</p>
				<p class="text-justify">N'oubliez pas de consulter la rubrique <s:a action="page_utilisateur_gdp">Gestion des prêts</s:a> de votre compte pour plus de détails sur le statut de vos prêts 
				et les options possibles.</p>
				<p class="text-justify">Si vous n'avez pas rendu un ouvrage à temps à la bibliothèque, un mail de relance quotidien vous sera adressé.</p>
				<p class="text-justify">Vous aurez dans ce cas soit la possibilité de prolonger la durée du prêt, soit de rapporter le livre à la bibliothèque.</p>
				<p class="text-justify">Par contre, si la durée de votre retard excède 4 semaines, vous ne pourrez plus prolonger le prêt.</p>
				<p class="text-justify">En cas de retard dans la restitution d'un document, nous serons contraints d'appliquer des pénalités par jour de retard et par document.</p>
				<p class="text-justify">Si vous désirez plus d'informations, n'hésitez pas à vous adresser au personnel de la bibliothèque.</p>
				<p class="text-justify">Bonne lecture !!!</p>
			</section>
			
			<!-- Footer -->
			<%@ include file="../_include/footer.jsp"%>
		</div>
	</body>
</html>