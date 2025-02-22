package kz.netcracker.testlibrary.application.mappers;

import javax.annotation.processing.Generated;
import kz.netcracker.testlibrary.application.dtos.CreateAuthorDto;
import kz.netcracker.testlibrary.application.dtos.UpdateAuthorDto;
import kz.netcracker.testlibrary.domain.model.book.Author;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-22T20:45:55+0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author map(CreateAuthorDto createAuthorDto) {
        if ( createAuthorDto == null ) {
            return null;
        }

        Author author = new Author();

        return author;
    }

    @Override
    public Author update(Author author, UpdateAuthorDto updateAuthorDto) {
        if ( author == null && updateAuthorDto == null ) {
            return null;
        }

        Author author1 = new Author();

        return author1;
    }
}
