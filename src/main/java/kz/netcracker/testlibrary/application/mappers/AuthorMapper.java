package kz.netcracker.testlibrary.application.mappers;

import kz.netcracker.testlibrary.application.dtos.CreateAuthorDto;
import kz.netcracker.testlibrary.application.dtos.UpdateAuthorDto;
import kz.netcracker.testlibrary.domain.model.book.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {

    Author map(CreateAuthorDto createAuthorDto);

    Author update(@MappingTarget Author author, UpdateAuthorDto updateAuthorDto);

}
