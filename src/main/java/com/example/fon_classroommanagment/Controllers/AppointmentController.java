package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.DTO.ConfirmAppointmentDTO;
import com.example.fon_classroommanagment.Models.DTO.DeleteReservationDTO;
import com.example.fon_classroommanagment.Models.DTO.ReserveDTO;
import com.example.fon_classroommanagment.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @DeleteMapping("/DeleteReservation")
    public void DeleteAppointment(@RequestBody DeleteReservationDTO dto){
appointmentService.DeleteAppointment(dto.getId().toString());

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Appointment>> getAll(){
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/test")
        public ResponseEntity<String> test(){
        return  ResponseEntity.ok("ok");
        }

        @PostMapping("/confirmAppointment")
    public void ConfirmAppointment(@RequestBody ConfirmAppointmentDTO dto){

        }

        @PostMapping("/reserve")
        public void Reserve(@RequestBody  @Valid  ReserveDTO dto){
System.out.println(dto);
        //appointmentService.ReserveAppointment(dto);

        }
    @ExceptionHandler(ConstraintViolationException.class)
    public  ResponseEntity<String> HandleMethodArgumentsNotValid(ConstraintViolationException exception){
        return ResponseEntity.badRequest().body
                ( exception.getConstraintViolations().toArray()[0].toString());

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<String> HandleMethodArgumentsNotValid(MethodArgumentNotValidException exception){

        return (exception.hasGlobalErrors())? ResponseEntity.badRequest().body
                (exception.getGlobalError().getDefaultMessage()):ResponseEntity.badRequest().body(exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

}
