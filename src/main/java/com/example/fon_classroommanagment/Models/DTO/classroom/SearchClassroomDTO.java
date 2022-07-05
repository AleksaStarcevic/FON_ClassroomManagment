package com.example.fon_classroommanagment.Models.DTO.classroom;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SearchClassroomDTO {


    @NotNull(message = "Ime ne sme biti null")
    private String name;
    @Positive(message = "Broj strane mora biti pozitivan")
    private int page;
}
