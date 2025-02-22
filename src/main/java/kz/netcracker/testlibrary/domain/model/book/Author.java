package kz.netcracker.testlibrary.domain.model.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Author extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String firstname;
    private String lastname;
    private String middlename;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

}
