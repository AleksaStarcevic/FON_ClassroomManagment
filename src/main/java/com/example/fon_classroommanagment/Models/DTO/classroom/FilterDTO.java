package com.example.fon_classroommanagment.Models.DTO.classroom;

import com.example.fon_classroommanagment.Anotations.CheckValues;
import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.util.List;

import static com.example.fon_classroommanagment.Configuration.Constants.MAX_CAPACITY;
import static com.example.fon_classroommanagment.Configuration.Constants.MIN_CAPACITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CheckValues(message = "Minimalan kapacitet mora biti manji od maksimalnog")
public class FilterDTO {

    @Positive(message = "Minimalan kapacitet mora biti pozitivan")
    private int min_capacity=MIN_CAPACITY;

    @Positive(message = "Maksimalan kapacitet mora biti pozitivan")
    private int max_capacity=MAX_CAPACITY;


    @NotNull(message = "Tip ucionice nije dobro unet")
    private List<ClassroomType> types;

private boolean aircondition;
private boolean projector;
    private boolean sortByCapacity;

}
