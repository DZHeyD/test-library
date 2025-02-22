package kz.netcracker.testlibrary.application.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

@Getter
@Setter
public class CreateAuthorDto {

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String middlename;
    @NotNull
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

}
