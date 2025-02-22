package kz.netcracker.testlibrary.application.mappers;

import kz.netcracker.testlibrary.application.dtos.CreateBookDto;
import kz.netcracker.testlibrary.application.dtos.UpdateBookDto;
import kz.netcracker.testlibrary.domain.model.book.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book map(CreateBookDto createBookDto);

    Book update(Book book, UpdateBookDto updateBookDto);

}
