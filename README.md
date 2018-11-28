### Projet 10 : Amélioration système information bibliothèque.

### 2 projets Maven ont été implémentés pour le projet 10 : 
-   1 pour l'application web côté client
-   1 pour les web services côté serveur de services

### L'application web est divisée en 5 sous-modules :

-   bibliotheques-annecy-business
-   bibliotheques-annecy-consumer
-   bibliotheques-annecy-model
-   bibliotheques-annecy-technical
-   bibliotheques-annecy-webapp

### L'application pour les web services est divisée en 6 sous-modules :

-   bibliotheques-annecy-ws-batch
-   bibliotheques-annecy-ws-business
-   bibliotheques-annecy-ws-consumer
-   bibliotheques-annecy-ws-model
-   bibliotheques-annecy-ws-technical
-   bibliotheques-annecy-ws-webapp

Ces applications ont été packagées avec Apache Maven 3.5.3.

### Mise en place de la base de données PostgreSQL 9.6.

Les ressources JNDI relatives à la base de données sont dans le fichier bibliotheques-annecy-ws-webapp.war\META-INF\context.xml.

Les scripts SQL de création de la base de données et d'insertion du jeu de données de démo sont disponibles dans le répertoire script-SQL.
Voici comment procéder pour utiliser ces fichiers :

-   Le serveur PostgreSQL est configuré avec les paramètres par défaut :
	- Host name/address : localhost
	- Port : 5432
	- Username : postgres
-   Créer une database sous PostgreSQL :
	- Nommer la database : SystemeInformationBibliotheque
	- L'associer à l'Owner par défaut postgres. Le mot de passe pour se connecter à la base de données est  : admin
-   Importer les fichiers SQL dans l'ordre indiqué ci-dessous :
	- 01_Creation_DB_Systeme_information_bibliotheque_v1.sql : Ce fichier permet de mettre en place la structure de la base de données : tables, attributs, séquences, clés primaires, étrangères, index uniques.
	- 02_Insertion_donnees_DB_Systeme_information_bibliotheque_v1.sql : Ce fichier permet d'insérer les données relatives au jeu de données de démo pour tester l'application.
	
Vous êtes libres de paramétrer PostgreSQL de manière personnalisée. Mais dans ce cas-là, il faut bien répercuter votre paramétrage dans le fichier bibliotheques-annecy-ws-webapp.war\META-INF\context.xml.

A noter que dans le fichier context.xml, il est également possible de paramétrer la durée max de l'emprunt (qui correspond à un premier emprunt ou à une prolongation). Par défaut, cette
durée max est fixée à 4 semaines.

### Déploiement de l'application pour les Web Services

L'application relative aux web services bibliotheques-annecy-ws-webapp.war est à déployer sur un serveur Apache Tomcat 9. Rappelons que seuls les web services peuvent se connecter à la base de données.

### Déploiement de l'application web

L'application web bibliotheques-annecy-webapp.war est à déployer sur un serveur Apache Tomcat 9. Cette application fera appel aux web services. 
Le fichier bibliotheques-annecy-webapp.war\META-INF\context.xml contient les adresses des web services.

### Déploiement du batch

Le module batch figure dans l'application relative aux web services, mais il ne dépend que des modules model et technical. Il fera donc lui aussi appel aux web services.
La fonction du module batch est d'envoyer un mail de relance aux usagers n'ayant pas rendu les ouvrages dont la période de prêt est terminée. Voici les étapes à suivre pour déployer
le module batch : 

-   Décompresser le fichier bibliotheques-annecy-ws-batch-1.0-SNAPSHOT-archive-deploy.zip
-   Entrer dans le répertoire bibliotheques-annecy-ws-batch-1.0-SNAPSHOT
-   Vous pouvez configurer le batch via le fichier conf\config.properties : adresses des web services, contenu du mail, périodicité des mails, configuration du serveur SMTP Gmail.
-   Ouvrir l'invite de commandes depuis le répertoire bibliotheques-annecy-ws-batch-1.0-SNAPSHOT
-   Vous pouvez soit créer une variable d'environnement BATCH_HOME qui contient le chemin complet jusqu'au répertoire bibliotheques-annecy-ws-batch-1.0-SNAPSHOT ou alors exécuter la ligne
de commande suivante en renseignant directement sa valeur : java -DBATCH_HOME=D:\...\bibliotheques-annecy-ws-batch-1.0-SNAPSHOT -jar lib/bibliotheques-annecy-ws-batch-1.0-SNAPSHOT.jar

### Projet SOAP UI

Les fichiers relatifs au projet SOAP UI pour tester les web services sont dans le répertoire SOAP_UI
