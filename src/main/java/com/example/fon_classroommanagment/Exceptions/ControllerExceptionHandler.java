package com.example.fon_classroommanagment.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ClassroomExistsException.class)
    public  ResponseEntity<String> HandleClassroomDoesNotExist(ClassroomExistsException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> HandleMethodArgumentsNotValid(ConstraintViolationException exception){
        return ResponseEntity.badRequest().body
                ( exception.getConstraintViolations().toArray()[0].toString());

    }    @ExceptionHandler(AppointmentDoesNotExistsException.class)
    public ResponseEntity<String> HandleAppointmentDoesNotExists(AppointmentDoesNotExistsException exception){
        return ResponseEntity.badRequest().body
                ( exception.getMessage());

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public  ResponseEntity<String> HandleEntityNotFoundException(EntityNotFoundException exception){
        return ResponseEntity.badRequest().body
                ( exception.getMessage());

    }
    @ExceptionHandler(ReservationExistsException.class)
    public  ResponseEntity<String> HandleReservationExistsException(ReservationExistsException exception){
        return ResponseEntity.badRequest().body
                ( exception.getMessage());

    }


    @ExceptionHandler(UserExistsExcetion.class)
    public  ResponseEntity<String> HandleUserDoesNotExist(UserExistsExcetion exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
