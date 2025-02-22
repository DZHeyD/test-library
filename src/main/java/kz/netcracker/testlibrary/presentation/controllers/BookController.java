package kz.netcracker.testlibrary.presentation.controllers;

import kz.netcracker.testlibrary.application.dtos.CreateBookDto;
import kz.netcracker.testlibrary.application.dtos.UpdateBookDto;
import kz.netcracker.testlibrary.application.services.BookAppService;
import kz.netcracker.testlibrary.domain.service.BookService;
import kz.netcracker.testlibrary.presentation.dtos.BookDto;
import kz.netcracker.testlibrary.presentation.mappers.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookAppService bookAppService;
    private final BookService bookService;
    private final BookDtoMapper bookDtoMapper;

    @PostMapping
    public BookDto create(@Valid @RequestBody CreateBookDto createBookDto) {
        return bookDtoMapper.map(bookAppService.create(createBookDto));
    }

    @GetMapping("/{id}")
    public BookDto get(@PathVariable("id") UUID id) {
        return bookDtoMapper.map(bookService.get(id));
    }

    @PutMapping("/{id}")
    public BookDto update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateBookDto updateBookDto) {
        updateBookDto.setId(id);
        return bookDtoMapper.map(bookAppService.update(updateBookDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        bookService.delete(id);
    }

    @PostMapping("/{bookId}/authors/{authorId}")
    public void addAuthor(
            @PathVariable("bookId") UUID bookId,
            @PathVariable("authorId") UUID authorId
    ) {
        bookAppService.addAuthorToBook(bookId, authorId);
    }

    @DeleteMapping("/{bookId}/authors/{authorId}")
    public void removeAuthor(
            @PathVariable("bookId") UUID bookId,
            @PathVariable("authorId") UUID authorId
    ) {
        bookAppService.removeAuthorFromBook(bookId, authorId);
    }

    @GetMapping
    public Page<BookDto> getAllBooks(@PageableDefault Pageable pageable) {
        return bookService.getAllBooks(pageable).map(bookDtoMapper::map);
    }

    @GetMapping(params =  "includes=authors")
    public Page<BookDto> getAllBooksWithAuthors(@PageableDefault Pageable pageable) {
        return bookService.getAllBooksWithAuthors(pageable).map(bookDtoMapper::map);
    }

}
