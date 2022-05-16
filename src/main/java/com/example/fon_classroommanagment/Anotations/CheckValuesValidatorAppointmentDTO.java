package com.example.fon_classroommanagment.Anotations;

import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.DTO.ReserveDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckValuesValidatorAppointmentDTO implements ConstraintValidator<CheckValues, ReserveDTO> {

    @Override
    public boolean isValid(ReserveDTO clazz, ConstraintValidatorContext constraintValidatorContext) {

        return clazz.getStart_timeInHours() < clazz.getEnd_timeInHours();
    }
}