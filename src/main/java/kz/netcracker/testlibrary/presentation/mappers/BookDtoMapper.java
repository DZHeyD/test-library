package kz.netcracker.testlibrary.presentation.mappers;

import kz.netcracker.testlibrary.domain.model.book.Book;
import kz.netcracker.testlibrary.presentation.dtos.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {

    BookDto map(Book book);

}
