package kz.netcracker.testlibrary.domain.model.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Book extends BaseEntity {

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Integer yearPublished;

    @ManyToMany
    @OrderBy("createdAt asc")
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    )
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
    }

}
