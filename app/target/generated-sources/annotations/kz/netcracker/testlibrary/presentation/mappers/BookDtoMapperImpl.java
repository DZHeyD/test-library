package kz.netcracker.testlibrary.presentation.mappers;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.domain.model.book.Book;
import kz.netcracker.testlibrary.presentation.dtos.AuthorDto;
import kz.netcracker.testlibrary.presentation.dtos.BookDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T22:04:39+0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class BookDtoMapperImpl implements BookDtoMapper {

    @Override
    public BookDto toBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );
        bookDto.setDescription( book.getDescription() );
        bookDto.setYearPublished( book.getYearPublished() );
        bookDto.setCreatedAt( book.getCreatedAt() );
        bookDto.setUpdatedAt( book.getUpdatedAt() );

        return bookDto;
    }

    @Override
    public BookDto toBookDtoWithAuthors(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setAuthors( toAuthorsDtoSet( book.getAuthors() ) );
        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );
        bookDto.setDescription( book.getDescription() );
        bookDto.setYearPublished( book.getYearPublished() );
        bookDto.setCreatedAt( book.getCreatedAt() );
        bookDto.setUpdatedAt( book.getUpdatedAt() );

        return bookDto;
    }

    @Override
    public Set<AuthorDto> toAuthorsDtoSet(Set<Author> authors) {
        if ( authors == null ) {
            return null;
        }

        Set<AuthorDto> set = new LinkedHashSet<AuthorDto>( Math.max( (int) ( authors.size() / .75f ) + 1, 16 ) );
        for ( Author author : authors ) {
            set.add( toAuthorDtoWithoutBooks( author ) );
        }

        return set;
    }

    @Override
    public AuthorDto toAuthorDtoWithoutBooks(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId( author.getId() );
        authorDto.setFirstname( author.getFirstname() );
        authorDto.setLastname( author.getLastname() );
        authorDto.setMiddlename( author.getMiddlename() );
        authorDto.setDateOfBirth( author.getDateOfBirth() );
        authorDto.setDateOfDeath( author.getDateOfDeath() );
        authorDto.setCreatedAt( author.getCreatedAt() );
        authorDto.setUpdatedAt( author.getUpdatedAt() );

        return authorDto;
    }
}
