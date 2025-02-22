package kz.netcracker.testlibrary.application.services;

import kz.netcracker.testlibrary.application.dtos.CreateAuthorDto;
import kz.netcracker.testlibrary.application.dtos.UpdateAuthorDto;
import kz.netcracker.testlibrary.application.mappers.AuthorMapper;
import kz.netcracker.testlibrary.domain.model.book.Author;
import kz.netcracker.testlibrary.domain.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorAppService {

    private final AuthorService authorService;
    private final AuthorMapper mapper;

    public Author create(CreateAuthorDto createAuthorDto) {
        Author author = mapper.map(createAuthorDto);
        return authorService.save(author);
    }

    @Transactional
    public Author update(UpdateAuthorDto updateAuthorDto) {
        Author author = authorService.get(updateAuthorDto.getId());
        Author updatedAuthor = mapper.update(author, updateAuthorDto);
        return authorService.save(updatedAuthor);
    }

}
