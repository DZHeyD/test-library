package kz.netcracker.testlibrary.application.mappers;

import kz.netcracker.testlibrary.application.dtos.CreateAuthorDto;
import kz.netcracker.testlibrary.application.dtos.UpdateAuthorDto;
import kz.netcracker.testlibrary.domain.model.book.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author map(CreateAuthorDto createAuthorDto);

    Author update(Author author, UpdateAuthorDto updateAuthorDto);

}
