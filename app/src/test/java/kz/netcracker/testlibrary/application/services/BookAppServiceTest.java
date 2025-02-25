package kz.netcracker.testlibrary.application.services;

import kz.netcracker.testlibrary.application.dtos.CreateBookDto;
import kz.netcracker.testlibrary.application.dtos.UpdateBookDto;
import kz.netcracker.testlibrary.application.mappers.BookMapper;
import kz.netcracker.testlibrary.application.mappers.BookMapperImpl;
import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.domain.model.book.Book;
import kz.netcracker.testlibrary.domain.service.AuthorService;
import kz.netcracker.testlibrary.domain.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookAppServiceTest {

    @InjectMocks
    private BookAppService bookAppService;

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @Mock
    private BookMapper mapper;

    private static final UUID BOOK_ID = UUID.randomUUID();
    private static final String BOOK_NAME = "Becoming Human";
    private static final String BOOK_DESC = "Sample Test Description";
    private static final Integer BOOK_YEAR = 2001;

    private CreateBookDto createBookDto;
    private UpdateBookDto updateBookDto;
    private Book book;
    private Author author;

    @BeforeEach
    void setUp() {
        createBookDto = CreateBookDto.builder()
                .name(BOOK_NAME)
                .description(BOOK_DESC)
                .yearPublished(BOOK_YEAR)
                .build();

        book = new Book();
        book.setId(BOOK_ID);
        book.setName(BOOK_NAME);
        book.setDescription(BOOK_DESC);
        book.setYearPublished(BOOK_YEAR);

        updateBookDto = UpdateBookDto.builder()
                .id(BOOK_ID)
                .name(BOOK_NAME)
                .description(BOOK_DESC)
                .yearPublished(BOOK_YEAR)
                .build();

        author = new Author();
        author.setId(UUID.randomUUID());
        author.setFirstname("Henry");
        author.setLastname("Monroe");
    }


    @Test
    public void createTest() {
        BookMapper testMapper = new BookMapperImpl();
        Book mappedBook = testMapper.map(createBookDto);

        given(mapper.map(createBookDto)).willReturn(mappedBook);
        given(bookService.save(mappedBook)).willReturn(book);

        Book testBook = bookAppService.create(createBookDto);

        assertThat(testBook).isNotNull();
        verify(bookService, times(1)).save(mappedBook);
        assertThat(testBook.getName()).isEqualTo(createBookDto.getName());
        assertThat(testBook.getDescription()).isEqualTo(createBookDto.getDescription());
        assertThat(testBook.getYearPublished()).isEqualTo(createBookDto.getYearPublished());
    }

    @Test
    public void updateTest() {

        updateBookDto.setName("Another Name");
        updateBookDto.setDescription("Another Description");

        BookMapper testMapper = new BookMapperImpl();
        Book mappedBook = testMapper.update(book, updateBookDto);

        Book updatedBook = new Book();
        updatedBook.setId(updateBookDto.getId());
        updatedBook.setName(updateBookDto.getName());
        updatedBook.setDescription(updateBookDto.getDescription());
        updatedBook.setYearPublished(updateBookDto.getYearPublished());

        given(bookService.get(updateBookDto.getId())).willReturn(book);
        given(mapper.update(book, updateBookDto)).willReturn(mappedBook);
        given(bookService.save(mappedBook)).willReturn(updatedBook);

        Book testBook = bookAppService.update(updateBookDto);

        assertThat(testBook).isNotNull();
        verify(bookService, times(1)).get(updateBookDto.getId());
        verify(bookService, times(1)).save(mappedBook);
        assertThat(testBook.getName()).isEqualTo(updateBookDto.getName());
        assertThat(testBook.getDescription()).isEqualTo(updateBookDto.getDescription());
        assertThat(testBook.getYearPublished()).isEqualTo(updateBookDto.getYearPublished());
    }

    @Test
    public void addAuthorToBookTest() {

        given(bookService.get(book.getId())).willReturn(book);
        given(authorService.get(author.getId())).willReturn(author);

        given(bookService.save(book)).willReturn(book);

        bookAppService.addAuthorToBook(book.getId(), author.getId());

        book.addAuthor(author);

        verify(bookService, times(1)).get(book.getId());
        verify(authorService, times(1)).get(author.getId());
        verify(bookService, times(1)).save(book);

        book.removeAuthor(author);
    }

    @Test
    public void removeAuthorToBookTest() {

        given(bookService.get(book.getId())).willReturn(book);
        given(authorService.get(author.getId())).willReturn(author);

        given(bookService.save(book)).willReturn(book);

        book.addAuthor(author);
        bookAppService.removeAuthorFromBook(book.getId(), author.getId());
        book.removeAuthor(author);

        verify(bookService, times(1)).get(book.getId());
        verify(authorService, times(1)).get(author.getId());
        verify(bookService, times(1)).save(book);
    }

}
