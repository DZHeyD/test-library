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
    date = "2025-02-25T20:13:32+0500",
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

        authorDto.setBooks( toBooksDtoSet( author.getBooks() ) );
        authorDto.setId( author.getId() );
        authorDto.setFirstname( author.getFirstname() );
        authorDto.setLastname( author.getLastname() );
        authorDto.setMiddlename( author.getMiddlename() );
        authorDto.setDateOfBirth( author.getDateOfBirth() );
        authorDto.setDateOfDeath( author.getDateOfDeath() );

        return authorDto;
    }

    @Override
    public Set<BookDto> toBooksDtoSet(Set<Book> authors) {
        if ( authors == null ) {
            return null;
        }

        Set<BookDto> set = new LinkedHashSet<BookDto>( Math.max( (int) ( authors.size() / .75f ) + 1, 16 ) );
        for ( Book book : authors ) {
            set.add( toBookDtoWithoutAuthors( book ) );
        }

        return set;
    }

    @Override
    public BookDto toBookDtoWithoutAuthors(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( book.getId() );
        bookDto.setName( book.getName() );
        bookDto.setDescription( book.getDescription() );
        bookDto.setYearPublished( book.getYearPublished() );

        return bookDto;
    }
}
