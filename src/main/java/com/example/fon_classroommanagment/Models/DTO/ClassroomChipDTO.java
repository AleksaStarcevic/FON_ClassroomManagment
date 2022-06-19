package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomChipDTO {

    @NotNull(message = "Id ne sme biti null")
    @Positive(message = "Id mora biti pozitivan broj")
    private Long id;
    @NotNull(message = "Name mora biti skup karaktera")
    @NotEmpty(message = "Name ne sme biti prazan")
    private String name;

}
