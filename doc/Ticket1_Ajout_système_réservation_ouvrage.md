### Qualification : Nouvelle fonctionnalité

### Analyse de la demande :

-   Les règles de gestions liées à cette nouvelle fonctionnalité seront gérées dans la couche business de l'application pour les web services (ce qui le cas actuellement) mais aussi
	sans doute en partie dans la couche business de l'application web pour garder le caractère réutilisable des web services.
-	La réservation concerne les livres dont aucun exemplaire n’est actuellement disponible (ils sont tous en cours de prêt).
- 	Chaque ouvrage (titre, pas exemplaire) aura donc une liste d’attente de réservation. Cet énoncé ne correspond peut être pas vraiment à ma situation.
	En effet, dans mon cas, il n'y a pas de distinction physique d'une édition dans une bibliothèque (la table exemplaire n'a pas de numérotation interne prévue à cet effet).
	Par ailleurs, l'application gère un réseau de bibliothèques et pas une seule bibilothèque.
	Pour être précis et adapté le cas à ma situation, c'est plutôt chaque edition dans une bibliothèque donnée, donc un exemplaire dans mon cas, qui aura une liste d'attente de réservation.
	A priori, il est nécessaire d'ajouter une table supplémentaire que l'on nommera "reservation" qui contiendra les colonnes id, date_reservation, utilisateur_id, exemplaire_bibliotheque_id, exemplaire_edition_id, priorite_reservation, date_reception_mail.
-	La liste de réservation ne peut comporter qu’un maximum de personnes correspondant à 2x le nombre d’exemplaires de l’ouvrage : Il faut modifier la table exemplaire avec une colonne 
	nb_exemplaires_init et nb_exemplaires_dispo.
-	Lors de la recherche d’ouvrage, pour ceux indisponibles, il doit y avoir la date de retour prévue la plus proche et le nombre de personnes ayant réservé l’ouvrage. Si la liste d’attente de l’ouvrage n’est pas complète, il doit pouvoir demander une réservation. Il sera alors ajouté à la liste d’attente : A priori, on dispose de tous les éléments pour réaliser cette fonction :
	- Dans la vue pour la page de détail d'une édition, il faudra bien garder les lignes dont le nombre d'exemplaire est nul. Mais dans ce càs là, on affichera une phrase de notification du type "Pas d'exemplaires disponibles pour le moment pour cette édition." Et dans la colonne Emprunt/Réservation, seul le choix "Reserver" sera disponible. Cliquer sur ce bouton redirigera l'utilisateur vers une autre page avec certaines informations habituelles (titre, auteur, etc...) mais aussi les informations additionnelles demandées tels que : la date de retour prévue la plus proche, le nombre de personnes ayant réservé l’ouvrage, un message de notification si la liste d'attente de l'ouvrage est complète, un bouton du type "Confirmer la réservation" (si la liste d'attente n'est pas complète).
- 	L’usager doit pouvoir avoir une liste des réservations qu’il a en cours avec pour chaque ouvrage la prochaine date de retour prévue et sa position dans la liste d’attente : Dans l'onglet
	"Gestion des prêts" d'un utilisateur que l'on pourra renommer en "Gestion prêts/réservations", on peut ajouter une rubrique du type "Réservation en cours".
- 	Concernant la date de retour prévue la plus proche, qu'en est-il si un utilisateur ne rend pas l'édition dans les délais ?
- 	Un utilisateur ne peut pas réserver un livre qu'il a déjà en cours d’emprunt.
- 	Ce n'est pas indiqué dans l'énoncé du ticket mais il faudrait intégrer la contrainte suivante : Un utilisateur ne peut pas réserver un livre qu'il a déjà réservé.
