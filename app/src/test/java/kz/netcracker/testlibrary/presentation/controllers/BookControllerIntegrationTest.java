package kz.netcracker.testlibrary.presentation.controllers;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import kz.netcracker.testlibrary.application.dtos.CreateAuthorDto;
import kz.netcracker.testlibrary.application.dtos.CreateBookDto;
import kz.netcracker.testlibrary.application.dtos.UpdateBookDto;
import kz.netcracker.testlibrary.domain.repository.AuthorRepository;
import kz.netcracker.testlibrary.domain.repository.BookRepository;
import kz.netcracker.testlibrary.infrastructure.DbIntegrationTest;
import kz.netcracker.testlibrary.presentation.dtos.AuthorDto;
import kz.netcracker.testlibrary.presentation.dtos.BookDto;
import kz.netcracker.testlibrary.utils.TestBookUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RequiredArgsConstructor
public class BookControllerIntegrationTest extends DbIntegrationTest {

    @Autowired
    private TestBookUtils testBookUtils;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @AfterEach
    public void clean() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void createTest() {

        CreateBookDto createBookDto = CreateBookDto.builder()
                .name("Testname")
                .description("Testdescription")
                .yearPublished(2001)
                .build();

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body(createBookDto)
            .when()
                .post("/books")
            .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo(createBookDto.getName()))
                .body("description", equalTo(createBookDto.getDescription()))
                .body("yearPublished", equalTo(createBookDto.getYearPublished()));
    }

    @Test
    public void getTest() {

        BookDto bookDto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Testname")
                .description("Testdescription")
                .yearPublished(2001)
                .build()
        );

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/books/{id}", bookDto.getId())
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo(bookDto.getName()))
                .body("description", equalTo(bookDto.getDescription()))
                .body("yearPublished", equalTo(bookDto.getYearPublished()));
    }

    @Test
    public void updateTest() {

        BookDto bookDto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Testname")
                .description("Testdescription")
                .yearPublished(2001)
                .build()
        );

        UpdateBookDto updateBookDto = UpdateBookDto.builder()
                .id(bookDto.getId())
                .name("UpdatedName")
                .description("UpdatedDescription")
                .yearPublished(1990)
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(updateBookDto)
                .when()
                .put("/books/{id}", bookDto.getId())
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo(updateBookDto.getName()))
                .body("description", equalTo(updateBookDto.getDescription()))
                .body("yearPublished", equalTo(updateBookDto.getYearPublished()));

    }

    @Test
    public void deleteTest() {

        BookDto bookDto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Testname")
                .description("Testdescription")
                .yearPublished(2001)
                .build()
        );

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/books/{id}", bookDto.getId())
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200);
    }

    @Test
    public void addAndRemoveAuthorTest() {

        BookDto bookDto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Testname")
                .description("Testdescription")
                .yearPublished(2001)
                .build()
        );

        AuthorDto authorDto = testBookUtils.createAuthor(CreateAuthorDto.builder()
                        .firstname("John")
                        .lastname("Mersmeir")
                        .dateOfBirth(LocalDate.of(1967,2,25))
                .build()
        );

        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/books/{bookId}/authors/{authorId}", bookDto.getId(), authorDto.getId())
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/books/{id}", bookDto.getId())
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .body("authors[0].id", equalTo(authorDto.getId().toString()))
                .body("authors[0].firstname", equalTo(authorDto.getFirstname()))
                .body("authors[0].lastname", equalTo(authorDto.getLastname()))
                .body("authors[0].middlename", equalTo(authorDto.getMiddlename()))
                .body("authors[0].dateOfBirth", equalTo(authorDto.getDateOfBirth().toString()))
                .body("authors[0].dateOfDeath", nullValue())
                .body("authors[0].books", nullValue());

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/books/{bookId}/authors/{authorId}", bookDto.getId(), authorDto.getId())
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/books/{id}", bookDto.getId())
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .body("authors", empty());
    }


    @Test
    public void getAllBooksTest() {

        BookDto book1Dto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Book1")
                .description("Book1 Description")
                .yearPublished(2001)
                .build()
        );

        BookDto book2Dto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Book2")
                .description("Book2 Description")
                .yearPublished(2002)
                .build()
        );

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/books")
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .body("content[0].id", equalTo(book1Dto.getId().toString()))
                .body("content[0].name", equalTo(book1Dto.getName()))
                .body("content[0].description", equalTo(book1Dto.getDescription()))
                .body("content[0].yearPublished", equalTo(book1Dto.getYearPublished()))
                .body("content[0].authors", empty())

                .body("content[1].id", equalTo(book2Dto.getId().toString()))
                .body("content[1].name", equalTo(book2Dto.getName()))
                .body("content[1].description", equalTo(book2Dto.getDescription()))
                .body("content[1].yearPublished", equalTo(book2Dto.getYearPublished()))
                .body("content[1].authors", empty());
    }

    // 2 books with 1 same author and 1 book with 2 authors
    @Test
    public void getAllBooksWithAuthorsTest() {

        AuthorDto author1Dto = testBookUtils.createAuthor(CreateAuthorDto.builder()
                .firstname("Mersmeir")
                .build()
        );

        BookDto book1Dto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Book1")
                .build()
        );
        testBookUtils.addAuthorToBook(book1Dto.getId(), author1Dto.getId());

        BookDto book2Dto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Book2")
                .build()
        );
        testBookUtils.addAuthorToBook(book2Dto.getId(), author1Dto.getId());

        AuthorDto author2Dto = testBookUtils.createAuthor(CreateAuthorDto.builder()
                .firstname("Alison")
                .build()
        );
        AuthorDto author3Dto = testBookUtils.createAuthor(CreateAuthorDto.builder()
                .firstname("Kissinger")
                .build()
        );

        BookDto book3Dto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Book3")
                .build()
        );
        testBookUtils.addAuthorToBook(book3Dto.getId(), author2Dto.getId());
        testBookUtils.addAuthorToBook(book3Dto.getId(), author3Dto.getId());

        BookDto book4Dto = testBookUtils.createBook(CreateBookDto.builder()
                .name("Book4")
                .build()
        );

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/books?includes=authors")
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .body("content[0].id", equalTo(book1Dto.getId().toString()))
                .body("content[0].name", equalTo(book1Dto.getName()))
                .body("content[0].authors[0].id", equalTo(author1Dto.getId().toString()))
                .body("content[0].authors[0].firstname", equalTo(author1Dto.getFirstname()))

                .body("content[1].id", equalTo(book2Dto.getId().toString()))
                .body("content[1].name", equalTo(book2Dto.getName()))
                .body("content[1].authors[0].id", equalTo(author1Dto.getId().toString()))
                .body("content[1].authors[0].firstname", equalTo(author1Dto.getFirstname()))

                .body("content[2].id", equalTo(book3Dto.getId().toString()))
                .body("content[2].name", equalTo(book3Dto.getName()))
                .body("content[2].authors[0].id", equalTo(author2Dto.getId().toString()))
                .body("content[2].authors[0].firstname", equalTo(author2Dto.getFirstname()))
                .body("content[2].authors[1].id", equalTo(author3Dto.getId().toString()))
                .body("content[2].authors[1].firstname", equalTo(author3Dto.getFirstname()))

                .body("content[3].id", equalTo(book4Dto.getId().toString()))
                .body("content[3].name", equalTo(book4Dto.getName()));
    }

}
