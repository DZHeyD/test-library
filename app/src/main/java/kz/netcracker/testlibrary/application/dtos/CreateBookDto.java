package kz.netcracker.testlibrary.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CreateBookDto {

    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Integer yearPublished;

}
