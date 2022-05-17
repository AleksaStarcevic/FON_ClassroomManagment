package com.example.fon_classroommanagment.Models.DTO;

import com.example.fon_classroommanagment.Anotations.CheckValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CheckValues(message = "Minimalan kapacitet mora biti manji od maksimalnog")
public class FilterDTO {

    @Positive(message = "Minimalan kapacitet mora biti pozitivan")
    private int min_capacity;

    @Positive(message = "Maksimalan kapacitet mora biti pozitivan")
    private int max_capacity;


    @Positive(message = "Tip ucionice nije dobro unet")
    private int type;


    private boolean sortByCapacity;

}
