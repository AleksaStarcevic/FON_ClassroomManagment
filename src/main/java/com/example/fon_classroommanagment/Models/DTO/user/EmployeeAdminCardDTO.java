package com.example.fon_classroommanagment.Models.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
public class EmployeeAdminCardDTO {
    @NotNull(message = "Id ne sme biti prazan")
            private UUID id;
    @NotNull(message = "firstName ne sme biti prazan")

    private String  firstName;
    @NotNull(message = "lastName ne sme biti prazan")

    private String  lastName;
    @NotNull(message = "permissionTitle ne sme biti prazan")

    private String permissionTitle;

}
