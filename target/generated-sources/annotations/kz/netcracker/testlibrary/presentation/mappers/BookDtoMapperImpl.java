package kz.netcracker.testlibrary.presentation.mappers;

import javax.annotation.processing.Generated;
import kz.netcracker.testlibrary.domain.model.book.Book;
import kz.netcracker.testlibrary.presentation.dtos.BookDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-22T20:45:55+0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class BookDtoMapperImpl implements BookDtoMapper {

    @Override
    public BookDto map(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        return bookDto;
    }
}
