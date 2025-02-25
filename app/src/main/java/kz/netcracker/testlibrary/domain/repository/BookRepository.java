package kz.netcracker.testlibrary.domain.repository;

import kz.netcracker.testlibrary.domain.model.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findByIdAndDeletedFalse(UUID id);

    Page<Book> findAllByDeletedFalseOrderByCreatedAtAsc(Pageable pageable);

    @Query("select b from Book b left join fetch b.authors a where b.deleted = false ORDER BY b.createdAt asc")
    Page<Book> getAllBooksWithAuthors(Pageable pageable);

}
