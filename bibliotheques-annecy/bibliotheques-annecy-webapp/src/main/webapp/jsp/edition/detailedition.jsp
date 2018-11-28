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
		
			<section id="edition-detaillee">
				<div class="row" id="row-edition-detaillee">
					<s:iterator value="listExemplaire" begin ="1" end ="1">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="detail-edition">	
							<!-- Les images doivent être responsives. Pour cela : 
					     	- Il ne faut surtout pas leur imposer une taille.
					     	- Les images doivent avoir les mêmes dimensions. C'est un pré-requis indispensable.
					     	- Il faut par contre bien mettre l'image dans une balise div qui applique les classes de type col-*-*
				     		- Il faut appliquer la classe img-responsive directement au niveau de l'image. -->
							<div class="col-xs-4 col-sm-4 col-md-3 col-lg-3">
								<img class="img-responsive" src="<s:property value="edition.photo.nomPhoto"/>" alt="Photo Couverture livre"/>
							</div>
							<div class="col-xs-8 col-sm-8 col-md-9 col-lg-9">
								<p id="edition-detaillee-titre"><s:property value="edition.ouvrage.titre"/></p>				
								<p id="edition-detaillee-auteur"><em>Auteur : </em><s:property value="edition.ouvrage.auteur.prenom"/> <s:property value="edition.ouvrage.auteur.nom"/></p>
								<p id="edition-detaillee-editeur"><em>Edité par</em> <s:property value="edition.editeur.nomEditeur"/> le <s:property value="edition.dateParution"/></p>
								<p id="edition-detaillee-genre"><em>Genre :</em> <s:property value="edition.genre.genre"/></p>
								<p id="edition-detaillee-isbn"><em>ISBN :</em> <s:property value="edition.isbn"/></p>
								<p id="edition-detaillee-nb-pages"><em>Nombre de pages :</em> <s:property value="edition.nbPages"/> pages</p>
								<p id="edition-detaillee-resume" class="text-justify"><em>Resumé : </em><s:property value="edition.ouvrage.resume"/></p>
							</div>
						</div>
					</s:iterator>
				</div>
				<s:if test="%{exemplaireTrouve==true}">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 table-responsive">
							<table class="table table-bordered table-striped table-condensed">
								<thead>
									<tr>
										<th class="text-center">Bibliothèque</th>
										<th class="text-center">Nombre d'exemplaires disponibles</th>
										<th class="text-center">Emprunt</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="listExemplaire">
										<s:if test="%{nbExemplaires!=0}">
											<tr>
												<td class="text-center"><s:property value="bibliotheque.nomBibliotheque"/></td>
												<td class="text-center"><s:property value="nbExemplaires"/></td>
												<td class="text-center">
												<s:a action="emprunter_edition" class="btn btn-primary">
													<s:param name="bibliothequeId" value="bibliotheque.id"/>
													<s:param name="editionId" value="edition.id"/>
													Emprunter
												</s:a>
												</td>
											</tr>
										</s:if>
									</s:iterator>
								</tbody>	
							</table>
						</div>
					</div>
				</s:if>	
				<s:else>
					<h3 class="text-center">Aucun exemplaire de cet ouvrage n'est disponible pour le moment. Veuillez revenir plus tard.</h3>
				</s:else>
			</section>
		
			<!-- Footer -->
			<%@ include file="../_include/footer.jsp"%>
		</div>		
	</body>
</html>
