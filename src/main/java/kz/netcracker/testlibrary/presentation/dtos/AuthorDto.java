package kz.netcracker.testlibrary.presentation.dtos;

import kz.netcracker.testlibrary.domain.model.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class AuthorDto {

    private UUID id;
    private String firstname;
    private String lastname;
    private String middlename;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private Set<BookDto> books;

}
