package kz.netcracker.testlibrary.application.services;

import kz.netcracker.testlibrary.application.dtos.CreateBookDto;
import kz.netcracker.testlibrary.application.dtos.UpdateBookDto;
import kz.netcracker.testlibrary.application.mappers.BookMapper;
import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.domain.model.book.Book;
import kz.netcracker.testlibrary.domain.service.AuthorService;
import kz.netcracker.testlibrary.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookAppService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookMapper mapper;

    public Book create(CreateBookDto createBookDto) {
        Book book = mapper.map(createBookDto);
        return bookService.save(book);
    }

    @Transactional
    public Book update(UpdateBookDto updateBookDto) {
        Book book = bookService.get(updateBookDto.getId());
        Book updatedBook = mapper.update(book, updateBookDto);
        return bookService.save(updatedBook);
    }

    @Transactional
    public void addAuthorToBook(UUID bookId, UUID authorId) {
        Book book = bookService.get(bookId);
        Author author = authorService.get(authorId);

        book.addAuthor(author);

        bookService.save(book);
    }

    @Transactional
    public void removeAuthorFromBook(UUID bookId, UUID authorId) {
        Book book = bookService.get(bookId);
        Author author = authorService.get(authorId);

        book.removeAuthor(author);

        bookService.save(book);
    }

}
