package kz.netcracker.testlibrary.domain.model.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Author extends BaseEntity {

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String middlename;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private LocalDate dateOfDeath;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

}
