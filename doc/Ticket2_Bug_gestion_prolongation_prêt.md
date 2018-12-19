### Qualification : Bug dans la gestion des prolongations de prêt

### Analyse de la demande :

-   Bug au niveau de la gestion des prolongations de prêt. En effet, un usager peut prolonger un prêt après la date butoir. Il ne doit pas être possible pour l’usager de prolonger un prêt si la date de fin de prêt est dépassée.
-   Le Web Service lié à la prolongation d'un emprunt est correct. Pas de changement à ce niveau là.
-	Au niveau de l'appli web, la fonction de prolongation d'un emprunt est accessible uniquement depuis le profil de l'utilisateur au niveau de la rubrique "Gestion des prêts". Le problème est qu'un utilisateur peut actuellement prolonger le prêt après la date de fin de prêt. Il faudrait supprimer cette option de l'interface graphique au niveau du profil de l'utilisateur.
-	Le batch qui envoie des mails de relance aux usagers qui n’ont pas rendu un ou des ouvrages à temps fait appel à un web service getListEmpruntEnRetard() qui se charge de mettre à jour les champs statut_emprunt_id et prolongation de la table emprunt. Il faudra appliquer la même règle pour la mise à jour des champs statut_emprunt_id et prolongation au niveau de la couche business liée à ce web service. Et au niveau du batch, plus précisément du contenu du mail, il faudra changer la formulation du message de fin.