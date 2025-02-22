package kz.netcracker.testlibrary.presentation.mappers;

import javax.annotation.processing.Generated;
import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.presentation.dtos.AuthorDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-22T20:45:55+0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class AuthorDtoMapperImpl implements AuthorDtoMapper {

    @Override
    public AuthorDto map(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        return authorDto;
    }
}
