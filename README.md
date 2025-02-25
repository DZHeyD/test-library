# Test Library App

## To run the System
Command below would build and run test-library-service and postgresql database docker containers:
```bash
docker compose up
```
After that application is available at localhost on 8080 port, ready to serve http query

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
To get all books:

## Stop the System
Stopping all the running containers is also simple with a single command:
```bash
docker compose down
```
