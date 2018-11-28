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
			
			<h1 class="text-center" >Qui sommes-nous?</h1>
			<section id="section-qsn">
				<div>
					<p class="text-justify">Ce site a été mis en place afin de moderniser le système de gestion des bibliothèques de la ville d'Annecy.</p>
					<p class="text-justify">Il regroupe les informations des bibliothèques de l'agglomération annécienne au sein d'un seul et unique portail internet.</p>
					<p class="text-justify">Les bibliothèques de l'agglomération annécienne concernées par ce regroupement sont les suivantes : Les Romains, Novel, Les Tilleuls, Bonlieu.</p>
					<p class="text-justify">Ce site permet donc :</p>
					<ul>
						<li>d'avoir accès à un catalogue commun de 4 bibliothèques, soit un accès à un contenu très conséquent de romans policiers, fantastiques, science-fiction, BD, manga, etc...</li>
						<li>de faciliter la gestion des prêts et prolongations de documents via la création d'un compte client</li>
					</ul>
					<p class="text-justify">Bonne lecture !!!</p>
				</div>
				<div id="sous-section-qsn">
					<img src="jsp/assets/images/logo_bibliotheque_1.png" alt="Logo bibliothèque" height=100px width=100px/>
					<p>Annecy</p>
				</div>
			</section>
			
			<!-- Footer -->
			<%@ include file="../_include/footer.jsp"%>
		</div>
	</body>
</html>