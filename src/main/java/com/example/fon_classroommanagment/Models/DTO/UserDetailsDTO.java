package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {
    @NotNull(message = "Ime ne sme biti prazno")
    @NotEmpty(message = "Ime mora sadrzati karaktere")
    private String firstName;

    @NotNull(message = "Ime ne sme biti prazno")
    @NotEmpty(message = "Ime mora sadrzati karaktere")
    private String lastName;

    @NotNull(message = "Ime ne sme biti prazno")
    @NotEmpty(message = "Ime mora sadrzati karaktere")
    private String typeName;

    private byte[] image;

}
