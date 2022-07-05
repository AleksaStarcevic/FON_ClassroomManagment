package com.example.fon_classroommanagment.Models.DTO.classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestClassroomsDTO {

    @Positive(message = "Page mora biti pozitivan broj")
    private int page;
}
