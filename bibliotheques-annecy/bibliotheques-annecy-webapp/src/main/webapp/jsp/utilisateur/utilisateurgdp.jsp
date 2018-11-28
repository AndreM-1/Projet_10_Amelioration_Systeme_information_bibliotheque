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
							<li><s:a action="page_utilisateur_mdp">Modifier mon mot de passe</s:a></li>
							<li><s:a action="page_utilisateur_gdp" id="gestion-prets">Gestion des prêts</s:a></li>
						</ul>
					</nav>
			
					<!-- Section permettant à l'utilisateur de visualiser les prêts effectutés -->
					<h3 class="statut-pret">Livres à rendre ou à prolonger</h3>
					<s:if test="%{bEmpruntNonRendu==true}">
						<h4>Vous n'avez aucun livre à rendre.</h4>
					</s:if>
					<div class="row" id="row-gdp-edition-nr">
						<s:iterator value="listEmpruntNonRendu">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="gdp-edition-nr">	
								<!-- Les images doivent être responsives. Pour cela : 
						     	- Il ne faut surtout pas leur imposer une taille.
						     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
						     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
					     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->
								<div class="col-xs-4 col-sm-4 col-md-3 col-lg-3">
									<img class="img-responsive" src="<s:property value="exemplaire.edition.photo.nomPhoto"/>" alt="Photo Couverture livre"/>
								</div>
								<div class="col-xs-8 col-sm-8 col-md-9 col-lg-9">
									<h4 id="gdp-edition-nr-titre"><s:property value="exemplaire.edition.ouvrage.titre"/></h4>				
									<p id="gdp-edition-nr-auteur"><em>Auteur : </em><s:property value="exemplaire.edition.ouvrage.auteur.prenom"/> <s:property value="exemplaire.edition.ouvrage.auteur.nom"/></p>
									<p id="gdp-edition-nr-editeur"><em>Edité par</em> <s:property value="exemplaire.edition.editeur.nomEditeur"/> le <s:property value="exemplaire.edition.dateParution"/></p>
									<p id="gdp-edition-nr-genre"><em>Genre :</em> <s:property value="exemplaire.edition.genre.genre"/></p>
									<p id="gdp-edition-nr-isbn"><em>ISBN :</em> <s:property value="exemplaire.edition.isbn"/></p>
									<p id="gdp-edition-nr-nb-pages"><em>Nombre de pages :</em> <s:property value="exemplaire.edition.nbPages"/> pages</p>
									<p id="gdp-edition-nr-periode-emprunt"><em>Période de l'emprunt :</em> Du <s:property value="dateDeDebut"/> au <s:property value="dateDeFin"/></p>
									<p id="gdp-edition-nr-statut-pret"><em>Statut du prêt :</em> <s:property value="statutEmprunt.statutEmprunt"/></p>
									<s:if test="%{prolongation==true}">
										<p id="gdp-edition-nr-prolong-pret-1"><em>Prolongation du prêt :</em> 
											<s:a action="prolonger_emprunt" class="btn btn-primary ">
												<s:param name="bibliothequeId" value="exemplaire.bibliotheque.id"/>
												<s:param name="editionId" value="exemplaire.edition.id"/>
												Prolonger
											</s:a>
										</p>
									</s:if>
									<s:else>
										<p class="text-justify" id="gdp-edition-nr-prolong-pret-2"><em>Prolongation du prêt :</em> 
											Vous avez déjà prolongé le prêt de cet ouvrage ou dépassé la date limite pour le prolonger. N'oubliez pas de le rendre dès que possible.
										</p>
										<p id="gdp-edition-nr-prolong-pret-2-reduit"><em>Prolongation du prêt :</em> 
											Déjà effectué.
										</p>
									</s:else>
								</div>
							</div>
						</s:iterator>
					</div>
					
					<h3 class="statut-pret">Prêts en cours</h3>
					<s:if test="%{bEmpruntEnCours==true}">
						<h4>Vous n'avez aucun prêt en cours pour le moment.</h4>
					</s:if>
					<div class="row" id="row-gdp-edition-ec">
						<s:iterator value="listEmpruntEnCours">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="gdp-edition-ec">	
								<!-- Les images doivent être responsives. Pour cela : 
						     	- Il ne faut surtout pas leur imposer une taille.
						     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
						     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
					     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->
								<div class="col-xs-4 col-sm-4 col-md-3 col-lg-3">
									<img class="img-responsive" src="<s:property value="exemplaire.edition.photo.nomPhoto"/>" alt="Photo Couverture livre"/>
								</div>
								<div class="col-xs-8 col-sm-8 col-md-9 col-lg-9">
									<h4 id="gdp-edition-ec-titre"><s:property value="exemplaire.edition.ouvrage.titre"/></h4>				
									<p id="gdp-edition-ec-auteur"><em>Auteur : </em><s:property value="exemplaire.edition.ouvrage.auteur.prenom"/> <s:property value="exemplaire.edition.ouvrage.auteur.nom"/></p>
									<p id="gdp-edition-ec-editeur"><em>Edité par</em> <s:property value="exemplaire.edition.editeur.nomEditeur"/> le <s:property value="exemplaire.edition.dateParution"/></p>
									<p id="gdp-edition-ec-genre"><em>Genre :</em> <s:property value="exemplaire.edition.genre.genre"/></p>
									<p id="gdp-edition-ec-isbn"><em>ISBN :</em> <s:property value="exemplaire.edition.isbn"/></p>
									<p id="gdp-edition-ec-nb-pages"><em>Nombre de pages :</em> <s:property value="exemplaire.edition.nbPages"/> pages</p>
									<p id="gdp-edition-ec-periode-emprunt"><em>Période de l'emprunt :</em> Du <s:property value="dateDeDebut"/> au <s:property value="dateDeFin"/></p>
									<p id="gdp-edition-ec-statut-pret"><em>Statut du prêt :</em> <s:property value="statutEmprunt.statutEmprunt"/></p>
									<s:if test="%{prolongation==true}">
										<p id="gdp-edition-ec-prolong-pret-1"><em>Prolongation du prêt :</em> 
											<s:a action="prolonger_emprunt" class="btn btn-primary ">
												<s:param name="bibliothequeId" value="exemplaire.bibliotheque.id"/>
												<s:param name="editionId" value="exemplaire.edition.id"/>
												Prolonger
											</s:a>
										</p>
									</s:if>
									<s:else>
										<p class="text-justify" id="gdp-edition-ec-prolong-pret-2"><em>Prolongation du prêt :</em> 
											Vous avez déjà prolongé le prêt de cet ouvrage. N'oubliez pas de le rendre avant la fin de la période d'emprunt.
										</p>
										<p id="gdp-edition-ec-prolong-pret-2-reduit"><em>Prolongation du prêt :</em> 
											Déjà effectué.
										</p>										
									</s:else>
								</div>
							</div>
						</s:iterator>
					</div>					
					
					<h3 class="statut-pret">Livres rendus</h3>
					<s:if test="%{bEmpruntRendu==true}">
						<h4>Vous n'avez rendu aucun livre pour le moment.</h4>
					</s:if>
					<div class="row" id="row-gdp-edition-r">
						<s:iterator value="listEmpruntRendu">
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="gdp-edition-r">	
								<!-- Les images doivent être responsives. Pour cela : 
						     	- Il ne faut surtout pas leur imposer une taille.
						     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
						     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
					     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->
								<div class="col-xs-4 col-sm-4 col-md-3 col-lg-3">
									<img class="img-responsive" src="<s:property value="exemplaire.edition.photo.nomPhoto"/>" alt="Photo Couverture livre"/>
								</div>
								<div class="col-xs-8 col-sm-8 col-md-9 col-lg-9">
									<h4 id="gdp-edition-r-titre"><s:property value="exemplaire.edition.ouvrage.titre"/></h4>				
									<p id="gdp-edition-r-auteur"><em>Auteur : </em><s:property value="exemplaire.edition.ouvrage.auteur.prenom"/> <s:property value="exemplaire.edition.ouvrage.auteur.nom"/></p>
									<p id="gdp-edition-r-editeur"><em>Edité par</em> <s:property value="exemplaire.edition.editeur.nomEditeur"/> le <s:property value="exemplaire.edition.dateParution"/></p>
									<p id="gdp-edition-r-genre"><em>Genre :</em> <s:property value="exemplaire.edition.genre.genre"/></p>
									<p id="gdp-edition-r-isbn"><em>ISBN :</em> <s:property value="exemplaire.edition.isbn"/></p>
									<p id="gdp-edition-r-nb-pages"><em>Nombre de pages :</em> <s:property value="exemplaire.edition.nbPages"/> pages</p>
									<p id="gdp-edition-r-periode-emprunt"><em>Période de l'emprunt :</em> Du <s:property value="dateDeDebut"/> au <s:property value="dateDeFin"/></p>
									<p id="gdp-edition-r-statut-pret"><em>Statut du prêt :</em> <s:property value="statutEmprunt.statutEmprunt"/></p>
								</div>
							</div>
						</s:iterator>
					</div>					
				</s:if>
			</section>
			
			<!-- Pied de page -->
			<%@ include file="../_include/footer.jsp"%>
	
		</div>
	
	</body>
</html>