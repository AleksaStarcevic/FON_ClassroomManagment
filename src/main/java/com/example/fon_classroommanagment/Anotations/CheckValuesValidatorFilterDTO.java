package com.example.fon_classroommanagment.Anotations;

import com.example.fon_classroommanagment.Models.DTO.FilterDTO;
import com.example.fon_classroommanagment.Models.DTO.ReserveDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckValuesValidatorFilterDTO implements ConstraintValidator<CheckValues, FilterDTO> {

    @Override
    public boolean isValid(FilterDTO clazz, ConstraintValidatorContext constraintValidatorContext) {

        return clazz.getMin_capacity() <= clazz.getMax_capacity();
    }
}