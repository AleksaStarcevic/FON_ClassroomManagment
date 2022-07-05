package com.example.fon_classroommanagment.Models.DTO.appointment;

import com.example.fon_classroommanagment.Anotations.CheckValues;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentType;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@CheckValues(message = "Krajnje vreme mora biti vece od pocetnog vremena")
public class GetForDateAppointmentDTO {

    @Positive(message = "Pocetno vreme mora biti pozitivan broj")
    private int Start_timeInHours;

    @Positive(message = "Krajnje vreme mora biti pozitivan broj")
    private int End_timeInHours;

    @NotNull(message = "Tip termina ne sme biti prazan")
    @NotEmpty(message = "Tip termina mora sadrzati karaktere")
    private String typeName;

    @NotNull(message = "Ucionica ne sme biti prazan")
    @NotEmpty(message = "Ucionica mora sadrzati karaktere")
    private String classroomName;

    @NotEmpty(message = "Opis mora sadrzati neke reci")
    @NotBlank(message = "Opis mora sadrzati neke reci")
    @NotNull(message = "Opis ne sme biti prazan")
    private  String decription;
}
