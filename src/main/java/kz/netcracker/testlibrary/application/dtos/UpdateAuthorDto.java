package kz.netcracker.testlibrary.application.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UpdateAuthorDto {

    @NotNull
    private UUID id;
    private String firstname;
    private String lastname;
    private String middlename;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

}
