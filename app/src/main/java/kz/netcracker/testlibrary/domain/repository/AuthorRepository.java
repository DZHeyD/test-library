package kz.netcracker.testlibrary.domain.repository;

import kz.netcracker.testlibrary.domain.model.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByIdAndDeletedFalse(UUID id);

    @Query("select a from Author a left join fetch a.books where a.id = :id and a.deleted = false")
    Optional<Author> findByIdAndWithBooks(UUID id);

}
