package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomPagingDTO {

    @NotNull(message = "id ne sme biti prazan")
    private Long id;
    @NotNull(message = "Naziv ne sme biti prazan")
    @NotEmpty(message = "Naziv mora sadrzati neke karaktere")
    private String name;

    @Positive(message = "Broj mesta mora biti pozitivan broj")
    private int number_of_seats;

    private boolean projector;

    private boolean isRC;
}
