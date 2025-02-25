package kz.netcracker.testlibrary.application.mappers;

import javax.annotation.processing.Generated;
import kz.netcracker.testlibrary.application.dtos.CreateBookDto;
import kz.netcracker.testlibrary.application.dtos.UpdateBookDto;
import kz.netcracker.testlibrary.domain.model.book.Book;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T20:13:32+0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book map(CreateBookDto createBookDto) {
        if ( createBookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setName( createBookDto.getName() );
        book.setDescription( createBookDto.getDescription() );
        book.setYearPublished( createBookDto.getYearPublished() );

        return book;
    }

    @Override
    public Book update(Book book, UpdateBookDto updateBookDto) {
        if ( updateBookDto == null ) {
            return book;
        }

        book.setId( updateBookDto.getId() );
        book.setName( updateBookDto.getName() );
        book.setDescription( updateBookDto.getDescription() );
        book.setYearPublished( updateBookDto.getYearPublished() );

        return book;
    }
}
