package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.AppointmentDoesNotExistsException;
import com.example.fon_classroommanagment.Exceptions.ReservationExistsException;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Services.AppointmentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Routes.*;

@RestController()
@RequestMapping(APPOINTMENT_PREFIX)
@Validated
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

        @DeleteMapping(APPOINTMENT_DELETE)
        public void DeleteAppointment(@RequestParam("id") @Valid UUID dto){
             appointmentService.DeleteAppointment(dto.toString());

        }

        @GetMapping(APPOINTMENTS)
        public ResponseEntity<List<Appointment>> getAll(){
            return ResponseEntity.ok(appointmentService.getAll());
        }



        @PostMapping(APPOINTMENT_CONFIRM)
        public void ConfirmAppointment(@RequestParam("id") String appointmentId) throws AppointmentDoesNotExistsException {
              appointmentService.ConfirmAppointment(UUID.fromString(appointmentId));
        }
        @PostMapping(APPOINTMENT_DECLINE)
        public void DeclineAppointment(@RequestParam("id") String appointmentId) throws AppointmentDoesNotExistsException {
              appointmentService.DeclineAppointment(UUID.fromString(appointmentId));
        }

        @PostMapping(APPOINTMENT_CONFIRM_ALL)
        public void ConfirmAppointment(@RequestBody List<String> dto) throws AppointmentDoesNotExistsException {
            appointmentService.ConfirmAllAppointments(dto.stream().map(UUID::fromString).collect(Collectors.toList()));
        }

        @PostMapping(APPOINTMENT_RESERVE)
        public void Reserve(@RequestBody  @Valid  List<ReserveDTO> dto, Authentication authentication) throws ReservationExistsException {
        appointmentService.ReserveAppointment(dto,authentication.getAuthorities().toArray()[0].toString());

        }

        @GetMapping(APPOINTMENT_SEARCH)
        public ResponseEntity<List<Appointment>> searchReservation(@RequestBody  @Valid SearchReservationDTO dto) throws ReservationExistsException {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.searchReservation(dto));
        }

        @PostMapping(APPOINTMENT_DATE)
        public ResponseEntity<List<GetForDateAppointmentDTO>> getAppointmentsForDate(@PathParam("date") @Valid @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") Date date){

            return  ResponseEntity.ok(appointmentService.getForDate(date));
        }
        @PostMapping(APPOINTMENT_CLASSROOM)
        public ResponseEntity<List<GetForDateAppointmentDTO>> getAppointmentsForDateAndClassroom(@RequestBody @Valid RequestAppointmetDaetForClassroomDTO requestAppointmetDateClassroomDTO){
            return  ResponseEntity.ok(appointmentService.getForDateAndClassroom(requestAppointmetDateClassroomDTO));
        }

        @PostMapping(APPOINTMENT_AVAILABILITY)
        public ResponseEntity<Boolean> getIsClassroomAvailableForDate(@RequestBody @Valid RequestIsClassroomAvailableForDateDTO dto ){
          return  ResponseEntity.ok(appointmentService.IsClassroomAvailableAtDate(dto));
        }

        @PatchMapping(APPOINTMENT_UPDATE)
        public void updateReservation(@RequestBody @Valid UpdateReservationDTO dto) throws ReservationExistsException {
             appointmentService.updateReservation(dto);
        }

}
