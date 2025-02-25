package kz.netcracker.testlibrary.application.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CreateAuthorDto {

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String middlename;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

}
