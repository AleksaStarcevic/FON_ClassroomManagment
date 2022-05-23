package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.ReservationExistsException;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

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
        appointmentService.ConfirmAppointment(dto);
        }

        @PostMapping("/reserve")
        public void Reserve(@RequestBody  @Valid  ReserveDTO dto) throws ReservationExistsException {

        appointmentService.ReserveAppointment(dto);

        }

    @GetMapping("/searchReservation")
    public ResponseEntity<List<Appointment>> searchReservation(@RequestBody  @Valid SearchReservationDTO dto) throws ReservationExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.searchReservation(dto));
    }







}
