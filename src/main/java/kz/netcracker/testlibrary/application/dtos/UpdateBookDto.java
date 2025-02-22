package kz.netcracker.testlibrary.application.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class UpdateBookDto {

    @NotNull
    private UUID id;
    private String name;
    private String description;
    private Integer yearPublished;

}
