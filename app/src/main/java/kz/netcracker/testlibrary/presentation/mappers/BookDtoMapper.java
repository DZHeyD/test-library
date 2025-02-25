package kz.netcracker.testlibrary.presentation.mappers;

import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.domain.model.book.Book;
import kz.netcracker.testlibrary.presentation.dtos.AuthorDto;
import kz.netcracker.testlibrary.presentation.dtos.BookDto;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookDtoMapper {

    @Mapping(target = "authors", ignore = true)
    BookDto toBookDto(Book book);

    @Mapping(target = "authors", qualifiedByName = "toAuthorsDtoSet")
    BookDto toBookDtoWithAuthors(Book book);

    @Named("toAuthorsDtoSet")
    @IterableMapping(qualifiedByName = "toAuthorDtoWithoutBooks")
    Set<AuthorDto> toAuthorsDtoSet(Set<Author> authors);

    @Named("toAuthorDtoWithoutBooks")
    @Mapping(target = "books", ignore = true)
    AuthorDto toAuthorDtoWithoutBooks(Author author);

}
