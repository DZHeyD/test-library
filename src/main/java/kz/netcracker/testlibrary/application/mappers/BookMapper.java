package kz.netcracker.testlibrary.application.mappers;

import kz.netcracker.testlibrary.application.dtos.CreateBookDto;
import kz.netcracker.testlibrary.application.dtos.UpdateBookDto;
import kz.netcracker.testlibrary.domain.model.book.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    Book map(CreateBookDto createBookDto);

    Book update(@MappingTarget Book book, UpdateBookDto updateBookDto);

}
