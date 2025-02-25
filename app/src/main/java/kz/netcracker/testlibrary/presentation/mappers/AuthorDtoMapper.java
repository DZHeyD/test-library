package kz.netcracker.testlibrary.presentation.mappers;

import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.domain.model.book.Book;
import kz.netcracker.testlibrary.presentation.dtos.AuthorDto;
import kz.netcracker.testlibrary.presentation.dtos.BookDto;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorDtoMapper {

    @Mapping(target = "books", ignore = true)
    AuthorDto toAuthorDto(Author author);

    @Mapping(target = "books", qualifiedByName = "toBooksDtoSet")
    AuthorDto toAuthorDtoWithBooks(Author author);

    @Named("toBooksDtoSet")
    @IterableMapping(qualifiedByName = "toBookDtoWithoutAuthors")
    Set<BookDto> toBooksDtoSet(Set<Book> authors);

    @Named("toBookDtoWithoutAuthors")
    @Mapping(target = "authors", ignore = true)
    BookDto toBookDtoWithoutAuthors(Book book);

}
