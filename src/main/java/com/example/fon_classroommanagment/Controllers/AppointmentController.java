package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @DeleteMapping("/DeleteReservation")
    public void DeleteAppointment(@RequestParam String id){
appointmentService.DeleteAppointment(id);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Appointment>> getAll(){
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/test")
        public ResponseEntity<String> test(){
        return  ResponseEntity.ok("ok");
        }
}
