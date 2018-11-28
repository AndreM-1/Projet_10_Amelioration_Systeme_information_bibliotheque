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
			
			<h1 class="text-center">Localisation/Horaires</h1>
			<section>
				<div class="row local-horaires">
					<!-- Les images doivent être responsives. Pour cela : 
			     	- Il ne faut surtout pas leur imposer une taille.
			     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
			     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
		     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->				
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<img src="jsp/assets/images/img_bibliotheque_les_romains.png" alt="Bibliothèque Les Romains" class="img-responsive">
					</div>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<p>Les Romains</p>
						<p class="adresse-bibliotheque">Adresse : 1 Boulevard de la Rocade - 74000 Annecy</p>
						<p>Téléphone : 04-50-88-38-10</p>
						<p class="horaire-bibliotheque">Horaires : Du Lundi au Samedi : 10h - 19h</p>
					</div>
				</div>
				<div class="row local-horaires">
					<!-- Les images doivent être responsives. Pour cela : 
			     	- Il ne faut surtout pas leur imposer une taille.
			     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
			     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
		     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->				
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<img src="jsp/assets/images/img_bibliotheque_novel.png" alt="Bibliothèque Novel" class="img-responsive">
					</div>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<p>Novel</p>
						<p class="adresse-bibliotheque">Adresse : 7 bis rue Louis Armand - 74000 Annecy</p>
						<p>Téléphone : 04-50-33-87-41</p>
						<p class="horaire-bibliotheque">Horaires : Du Lundi au Samedi : 10h - 19h</p>
					</div>
				</div>
				<div class="row local-horaires">
					<!-- Les images doivent être responsives. Pour cela : 
			     	- Il ne faut surtout pas leur imposer une taille.
			     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
			     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
		     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->					
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<img src="jsp/assets/images/img_bibliotheque_les_tilleuls.png" alt="Bibliothèque Les Tilleuls" class="img-responsive">
					</div>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<p>Les Tilleuls</p>
						<p class="adresse-bibliotheque">Adresse : 11 rue du Lachat - 74940 Annecy</p>
						<p>Téléphone : 04-50-09-85-89</p>
						<p class="horaire-bibliotheque">Horaires : Du Lundi au Samedi : 10h - 19h</p>
					</div>
				</div>
				<div class="row local-horaires">
					<!-- Les images doivent être responsives. Pour cela : 
			     	- Il ne faut surtout pas leur imposer une taille.
			     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
			     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
		     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->					
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
						<img src="jsp/assets/images/img_bibliotheque_bonlieu.png" alt="Bibliothèque Bonlieu" class="img-responsive">
					</div>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<p>Bonlieu</p>
						<p class="adresse-bibliotheque">Adresse : 1, rue Jean Jaurès Annecy - 74000 Annecy</p>
						<p>Téléphone : 04-50-33-87-00</p>
						<p class="horaire-bibliotheque">Horaires : Du Lundi au Samedi : 10h - 19h</p>
					</div>
				</div>								
			</section>
			
			<!-- Footer -->
			<%@ include file="../_include/footer.jsp"%>
		</div>
	</body>
</html>