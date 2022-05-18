package com.example.fon_classroommanagment.Models.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SearchClassroomDTO {

    @NotEmpty(message = "Ime ne sme biti prazno")
    private String name;
}
