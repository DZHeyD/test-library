package kz.netcracker.testlibrary.domain.service;

import kz.netcracker.testlibrary.domain.exception.ResourceNotFoundException;
import kz.netcracker.testlibrary.domain.model.book.Book;
import kz.netcracker.testlibrary.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book get(UUID id) {
        return bookRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with this id: " + id));
    }

    @Transactional
    public void delete(UUID id) {
        Book bookToBeDeleted = bookRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with this id: " + id));
        bookToBeDeleted.delete();
        bookToBeDeleted.setAuthors(new HashSet<>());
        bookRepository.save(bookToBeDeleted);
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAllByDeletedFalse(pageable);
    }

    public Page<Book> getAllBooksWithAuthors(Pageable pageable) {
        return bookRepository.getAllBooksWithAuthors(pageable);
    }
}
