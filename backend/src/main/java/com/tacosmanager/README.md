# TacosManager Backend

## Description
TacosManager est une application backend pour la gestion d'un réseau de fastfoods. Elle permet :
- **Admin** : Gérer les fastfoods (CRUD, recherche, tri, filtre).
- **Gérant** : Gérer les produits, familles de produits, et comptes (CRUD, recherche, tri, filtre).
- **Client** : Créer un profil et passer des commandes.
- **Serveur** : Gérer les commandes (Créée → En attente → Prête → Livrée).

## Technologies
- **Spring Boot** : Framework principal.
- **PostgreSQL** : Base de données.
- **JPA/Hibernate** : Persistance des données.
- **Lombok** : Réduction du code boilerplate.
- **Swagger** : Documentation de l'API.
- **Git** : Gestion de version.

## Prérequis
- Java 17
- Maven
- PostgreSQL (installé et configuré)

## Configuration
1. Clonez le projet :
   ```bash
   git clone <repository-url>