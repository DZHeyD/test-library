package kz.netcracker.testlibrary.utils;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import kz.netcracker.testlibrary.application.dtos.CreateAuthorDto;
import kz.netcracker.testlibrary.application.dtos.CreateBookDto;
import kz.netcracker.testlibrary.presentation.dtos.AuthorDto;
import kz.netcracker.testlibrary.presentation.dtos.BookDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static io.restassured.RestAssured.given;


@Component
public class TestBookUtils {

    public BookDto createBook(CreateBookDto createBookDto) {
        var response = given()
                .contentType(ContentType.JSON)
                .body(createBookDto)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .extract().body();

        return response.as(BookDto.class);
    }

    public AuthorDto createAuthor(CreateAuthorDto createAuthorDto) {
        var response = given()
                .contentType(ContentType.JSON)
                .body(createAuthorDto)
                .when()
                .post("/authors")
                .then()
                .statusCode(200)
                .extract().body();

        return response.as(AuthorDto.class);
    }

    public void addAuthorToBook(UUID bookId, UUID authorId) {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/books/{bookId}/authors/{authorId}", bookId, authorId)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200);
    }

}
