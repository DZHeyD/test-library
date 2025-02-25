package kz.netcracker.testlibrary.presentation.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class BookDto {

    private UUID id;
    private String name;
    private String description;
    private Integer yearPublished;
    private Set<AuthorDto> authors = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
