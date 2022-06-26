package com.example.fon_classroommanagment.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.impl.JWTParser;
import com.example.fon_classroommanagment.Exceptions.AppointmentDoesNotExistsException;
import com.example.fon_classroommanagment.Exceptions.ReservationExistsException;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Validated
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

        @DeleteMapping("/DeleteReservation")
        public void DeleteAppointment(@RequestParam("id") @Valid UUID dto){
             appointmentService.DeleteAppointment(dto.toString());

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
        public void ConfirmAppointment(@RequestBody @Valid ConfirmAppointmentDTO dto) throws AppointmentDoesNotExistsException {
              appointmentService.ConfirmAppointment(dto);
        }

    @PostMapping("/confirmAllAppointment")
    public void ConfirmAppointment(@RequestBody List<ConfirmAppointmentDTO> dto) throws AppointmentDoesNotExistsException {
        appointmentService.ConfirmAllAppointments(dto);
    }

    @PostMapping("/reserve")
        public void Reserve(@RequestBody  @Valid  List<ReserveDTO> dto) throws ReservationExistsException {

        appointmentService.ReserveAppointment(dto);

        }

        @GetMapping("/searchReservation")
        public ResponseEntity<List<Appointment>> searchReservation(@RequestBody  @Valid SearchReservationDTO dto) throws ReservationExistsException {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.searchReservation(dto));
        }


        @PostMapping("/GetForDate")
        public ResponseEntity<List<GetForDateAppointmentDTO>> getAppointmentsForDate(@RequestBody @Valid RequestAppointmetDateDTO requestAppointmetDateDTO){

            return  ResponseEntity.ok(appointmentService.getForDate(requestAppointmetDateDTO));
        }
        @PostMapping("/GetForDateAndClassroom")
        public ResponseEntity<List<GetForDateAppointmentDTO>> getAppointmentsForDateAndClassroom(@RequestBody @Valid RequestAppointmetDaetForClassroomDTO requestAppointmetDateClassroomDTO){
            return  ResponseEntity.ok(appointmentService.getForDateAndClassroom(requestAppointmetDateClassroomDTO));
        }






        @PostMapping("/IsClassroomAvailableForDate")
        public ResponseEntity<Boolean> getIsClassroomAvailableForDate(@RequestBody @Valid RequestIsClassroomAvailableForDateDTO dto ){
          return  ResponseEntity.ok(appointmentService.IsClassroomAvailableAtDate(dto));
        }



    @PatchMapping("/updateReservation")
    public void updateReservation(@RequestBody @Valid UpdateReservationDTO dto) throws ReservationExistsException {
         appointmentService.updateReservation(dto);
    }




}
