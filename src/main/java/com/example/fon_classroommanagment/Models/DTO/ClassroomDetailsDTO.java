package com.example.fon_classroommanagment.Models.DTO;

import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
public class ClassroomDetailsDTO {

    @NotNull(message = "Naziv ne sme biti prazan")
    @NotEmpty(message = "Naziv mora sadrzati neke karaktere")
    private String name;

   @Positive(message = "Broj mesta mora biti pozitivan broj")
    private int number_of_seats;

    @Positive(message = "Broj kompijutera mora biti pozitivan broj")
    private int number_of_computers;


    private boolean aircondition;

    private boolean projector;

    @NotNull(message = "Tip ne sme biti prazan")
    private ClassroomType type;

    @Positive(message = "Povrsina mora biti pozitivan broj")
    private int povrsina;

    @Positive(message = "Sprat mora biti pozitivan broj")
    private int sprat;

    @Positive(message = "Broj tabli mora biti pozitivan broj")
    private int br_tabli;

   private List<Double[]> months_percentage;


}
