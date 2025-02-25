package kz.netcracker.testlibrary.presentation.controllers;

import kz.netcracker.testlibrary.application.dtos.CreateAuthorDto;
import kz.netcracker.testlibrary.application.dtos.UpdateAuthorDto;
import kz.netcracker.testlibrary.application.services.AuthorAppService;
import kz.netcracker.testlibrary.domain.service.AuthorService;
import kz.netcracker.testlibrary.presentation.dtos.AuthorDto;
import kz.netcracker.testlibrary.presentation.mappers.AuthorDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorAppService authorAppService;
    private final AuthorService authorService;
    private final AuthorDtoMapper authorDtoMapper;

    @PostMapping
    public AuthorDto create(@Valid @RequestBody CreateAuthorDto createAuthorDto) {
        return authorDtoMapper.toAuthorDto(authorAppService.create(createAuthorDto));
    }

    @GetMapping("/{id}")
    public AuthorDto get(@PathVariable("id") UUID id) {
        return authorDtoMapper.toAuthorDto(authorService.get(id));
    }

    @GetMapping(value = "/{id}", params = "includes=books")
    public AuthorDto getWithBooks(@PathVariable("id") UUID id) {
        return authorDtoMapper.toAuthorDtoWithBooks(authorService.getWithBooks(id));
    }

    @PutMapping("/{id}")
    public AuthorDto update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateAuthorDto updateAuthorDto) {
        updateAuthorDto.setId(id);
        return authorDtoMapper.toAuthorDtoWithBooks(authorAppService.update(updateAuthorDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        authorService.delete(id);
    }

}
