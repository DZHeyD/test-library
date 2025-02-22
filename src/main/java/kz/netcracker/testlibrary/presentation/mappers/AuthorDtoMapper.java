package kz.netcracker.testlibrary.presentation.mappers;

import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.presentation.dtos.AuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorDtoMapper {

    AuthorDto map(Author author);

}
