
****************************************
****************************************
****** Application de di�t�tique *******
****************************************
****************************************

* 1 - Infos utiles *

- Auteur			:	Xavier Tagliarino
-- Mail : xavier.tagliarino@gmail.com
- Date de cr�ation	:	20/03/2018

* 2 - Descriptif *

* A - r�sum� *

Application permettant de g�rer des aliments class�s par Cat�gorie

* B - Actions possibles *

* B - 1 Cat�gorie *
	
	- Afficher
	- Cr�er
	- Modifier
	- Supprimer des cat�gories

* B - 2 Aliments * (Si au moins une cat�gorie existe)
	
	- Afficher les aliments par cat�gorie avec leur valeur energ�tique calcul�e en temps r�el
	- Cr�er un aliment en lui affectant une cat�gorie
	- Modifier (Si au moins un aliment)
	- Suppimer (Si au moins un aliment)
	
* 3 - Sch�ma de donn�es *

Cf Fichier "modele.jpg"

* 4 - La base de donn�es

Le moteur utilis� est "Postgresql"

4 - a - Cr�ation de la base de donn�es *

Cr�er une base de donn�es de type "Postgresql" comme suit :

Ex�cuter les commandes SQL suivantes :

- DROP DATABASE IF EXISTS "dietetique"; 
- CREATE DATABASE "dietetique";
- Ex�cuter les scripts dans : "create.sql"


4 - b - Param�trage :
Les coordon�es de la base de donn�es sont � param�trer dans le fichier :config.properties
- Acc�s : "/src/main/ressources/config.properties"
- Donn�es :
sgbd.driver=org.postgresql.Driver
sgbd.dsn=jdbc:postgresql://localhost:5432/<votrebase>
sgbd.login=<votrelogin>
sgbd.password=<votre mode de passe>

5 - Lancement de l'application

Importer un projet Maven existant via Eclipse 