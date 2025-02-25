package kz.netcracker.testlibrary.domain.service;

import kz.netcracker.testlibrary.domain.exception.ResourceNotFoundException;
import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.domain.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author get(UUID id) {
        return authorRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with this id: " + id));
    }

    public Author getWithBooks(UUID id) {
        return authorRepository.findByIdAndWithBooks(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with this id: " + id));
    }

    @Transactional
    public void delete(UUID id) {
        Author authorToBeDeleted = authorRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with this id: " + id));
        authorToBeDeleted.delete();
        authorToBeDeleted.setBooks(new HashSet<>());
        authorRepository.save(authorToBeDeleted);
    }

}
