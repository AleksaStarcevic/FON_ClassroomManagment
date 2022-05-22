package com.example.fon_classroommanagment.Models.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestClassroomDetailsDTO {

    @Positive(message = "Id mora biti pozitivan broj")
    private Long id;


}
