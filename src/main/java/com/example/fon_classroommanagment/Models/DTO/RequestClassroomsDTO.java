package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class RequestClassroomsDTO {

    @Positive(message = "Page mora biti pozitivan broj")
    private int page;
}
