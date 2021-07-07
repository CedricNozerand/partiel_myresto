# partiel_myresto

Projet de gestion des commandes du restaurant MyResto.

# Développeurs

- [Fatimata Ba](https://github.com/timziba)
- [Cédric Nozerand](https://github.com/CedricNozerand)
- [Benjamin Boutrois](https://github.com/BenjaminBoutrois)
- [Loïk Froelich](https://github.com/AtiLoik)
- [Braham Moussouni](https://github.com/brahamax1209)

# Prérequis

- Serveur Web (Apache + MySQL + phpmyadmin)
- Tomcat 9
- JDK 11
- Maven 3.6.3

# Installation

1. Cloner le projet : `git clone https://github.com/CedricNozerand/partiel_myresto.git`

2. Ouvrir le fichier `partiel_myresto/src/main/resources/application.properties` et modifier les informations de connexion à la base de données (username et password) ainsi que le port de connexion avec votre serveur de base de données.

3. Dans Phpmyadmin, créer une base de données *myresto*.

4. Sélectionner la base de données *myresto* et importer le fichier `partiel_myresto/myresto.sql`

5. Ouvrir un invite de commandes à la racine du projet et démarrer le projet : `mvn spring-boot:run`

6. Ouvrir un navigateur pour tester : [partiel_myresto](http://127.0.0.1:8080/)

# Connexion

Par défaut, il y a un utilisateur admin dans la base de données. Ses identifiants sont :

- Identifiant : admin@myResto.com
- Mot de passe : admin

Pour se connecter en tant qu'utilisateur, il faut cliquer sur le bouton inscription sur la page d'accueil.