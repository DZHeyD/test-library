# Test Library App

## To run the System
Command below would build and run test-library-service and postgresql database docker containers:
```bash
docker compose up
```
After that application is available at localhost on 8080 port, ready to serve http query

## Stop the System
Stopping all the running containers is also simple with a single command:
```bash
docker compose down
```

## What this project contains
#### This project is a demo of several concepts:
1) Onion/DDD architecture in code management separating domain, application layer, presentation layer and infrastructure
2) Liquibase integration for database version management
3) Usage of Mapstruct for different mapping scenarios for the same entity
4) Unit Tests using Junit, Mockito
5) Integration Tests using TestContainers, Rest-Assured
6) Dockerization suited for production usage
7) Docker compose configs
8) Project structure is designed to suit future expansion and modification

#### What is missing in the project which is present in regular projects: 
1) OpenApi generated documentation
2) Logstash or any other log collecting configurations

## Functionality
You can create a new book with the query like:
```http request
POST /books
Content-Type: application/json

{
    "name": "TestBook", // Required
    "description": "TestBook Description",
    "yearPublished": 2001
}
```
You can create an author with the query:
```http request
POST /authors
Content-Type: application/json

{
    "firstname": "AuthorName", // Required
    "lastname": "AuthorLastname",
    "middlename": "AuthorMiddlename",
    "dateOfBirth": "1967-05-25",
    "dateOfDeath": null
}
```
You can link author to book using:
```http request
POST /books/{{bookId}}/authors/{{authorId}}}
```
To get all books without authors:
```http request
GET /books
```
To get all books with authors:
```http request
GET /books?includes=authors
```
Result response would be a paged response. 
You can also include page and size query parameters if you want.\
To get an author without his books list:
```http request
GET /authors/{{authorId}}
```
To get with his books list:
```http request
GET /authors/{{authorId}}?includes=books
```
Also all CRUD(POST,GET,PUT,DELETE) operations are supported for each of the entity
