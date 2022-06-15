package com.example.fon_classroommanagment.Models.DTO;


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


    private String name;
    @Positive(message = "broj strane mora biti pozitivan")
    private int page;
}
