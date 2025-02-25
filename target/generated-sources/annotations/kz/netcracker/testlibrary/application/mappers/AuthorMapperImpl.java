package kz.netcracker.testlibrary.application.mappers;

import javax.annotation.processing.Generated;
import kz.netcracker.testlibrary.application.dtos.CreateAuthorDto;
import kz.netcracker.testlibrary.application.dtos.UpdateAuthorDto;
import kz.netcracker.testlibrary.domain.model.book.Author;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T10:53:23+0500",
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

        author.setFirstname( createAuthorDto.getFirstname() );
        author.setLastname( createAuthorDto.getLastname() );
        author.setMiddlename( createAuthorDto.getMiddlename() );
        author.setDateOfBirth( createAuthorDto.getDateOfBirth() );
        author.setDateOfDeath( createAuthorDto.getDateOfDeath() );

        return author;
    }

    @Override
    public Author update(Author author, UpdateAuthorDto updateAuthorDto) {
        if ( updateAuthorDto == null ) {
            return author;
        }

        author.setId( updateAuthorDto.getId() );
        author.setFirstname( updateAuthorDto.getFirstname() );
        author.setLastname( updateAuthorDto.getLastname() );
        author.setMiddlename( updateAuthorDto.getMiddlename() );
        author.setDateOfBirth( updateAuthorDto.getDateOfBirth() );
        author.setDateOfDeath( updateAuthorDto.getDateOfDeath() );

        return author;
    }
}
