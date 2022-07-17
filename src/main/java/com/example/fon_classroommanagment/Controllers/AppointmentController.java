package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.AppointmentDoesNotExistsException;
import com.example.fon_classroommanagment.Exceptions.ReservationExistsException;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.DTO.appointment.*;
import com.example.fon_classroommanagment.Models.DTO.classroom.RequestIsClassroomAvailableForDateDTO;
import com.example.fon_classroommanagment.Services.AppointmentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Routes.*;

/**
 * AppointmentController obradjuje zahteve vezane za termin
 * @author Aleksa Starcevic
 * @version 1.0
 */
@RestController()
@RequestMapping(APPOINTMENT_PREFIX)
@Validated
public class AppointmentController {
    /**
     * zavisnost appointmentService u kojoj se nalazi logika vezana za termin
     */
    @Autowired
    private AppointmentService appointmentService;

    /**
     * Metoda koja na osnovu id-ja termina i mejla zaposlenog brise termin
     * @param dto id termina za brisanje
     * @param authentication podaci korisnika koji brise termin
     * @throws UserExistsExcetion ako korisnik ne postoji
     */
        @DeleteMapping(APPOINTMENT_DELETE)
        public void DeleteAppointment(@RequestParam("id") @Valid UUID dto,Authentication authentication) throws UserExistsExcetion {

       appointmentService.DeleteAppointment(dto.toString(),authentication.getName());


        }

    /**
     * Metoda koja vraca sve termine
     * @return HTTP odgovor koji u telu sadrzi listu termina
     */
    @GetMapping(APPOINTMENTS)
        public ResponseEntity<List<Appointment>> getAll(){
            return ResponseEntity.ok(appointmentService.getAll());
        }


    /**
     * Metoda za potvrdu termina
     * @param appointmentId id termina kojeg hocemo da potvrdimo
     * @throws AppointmentDoesNotExistsException ako termin sa zadatim identifikatorom ne postoji
     */
    @PostMapping(APPOINTMENT_CONFIRM)
        public void ConfirmAppointment(@RequestParam("id") String appointmentId) throws AppointmentDoesNotExistsException {
              appointmentService.ConfirmAppointment(UUID.fromString(appointmentId));
        }

    /**
     * Metoda za odbijanje termina
     * @param appointmentId id termina kojeg hocemo da odbijemo
     * @throws AppointmentDoesNotExistsException ako termin sa zadatim identifikatorom ne postoji
     */
    @PostMapping(APPOINTMENT_DECLINE)
        public void DeclineAppointment(@RequestParam("id") String appointmentId) throws AppointmentDoesNotExistsException {
              appointmentService.DeclineAppointment(UUID.fromString(appointmentId));
        }

    /**
     * Metoda za potvrdu liste termina
     * @param dto lista identifikatora termina za odobravanje
     * @throws AppointmentDoesNotExistsException ako termin sa zadatim identifikatorom ne postoji
     */
    @PostMapping(APPOINTMENT_CONFIRM_ALL)
        public void ConfirmAppointment(@RequestBody List<String> dto) throws AppointmentDoesNotExistsException {
            appointmentService.ConfirmAllAppointments(dto.stream().map(UUID::fromString).collect(Collectors.toList()));
        }

    /**
     * Metoda za rezervisanje liste termina
     * @param dto lista objekata rezervacija
     * @param authentication podaci o korisniku koji rezervise, ako je administrator termin se odmah odobrava
     * @throws ReservationExistsException ako vec postoji termin sa istim vremenom i datumom
     */
        @PostMapping(APPOINTMENT_RESERVE)
        public void Reserve(@RequestBody  @Valid  List<ReserveDTO> dto, Authentication authentication) throws ReservationExistsException {
        appointmentService.ReserveAppointment(dto,authentication.getAuthorities().toArray()[0].toString());

        }

    /**
     * Metoda za pretragu termina
     * @param dto dto koji sadrzi date,classroomId i start_timeInHours
     * @return HTTP odgovor koji u telu sadrzi listu termina
     * @throws ReservationExistsException ako termin nije pronadjen za uneti datum i vreme
     */
        @GetMapping(APPOINTMENT_SEARCH)
        public ResponseEntity<List<Appointment>> searchReservation(@RequestBody  @Valid SearchAppointmentDTO dto) throws ReservationExistsException {
            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.searchReservation(dto));
        }

        @GetMapping(APPOINTMENT_DETAILS)
        public ResponseEntity<AppointmentDetailsDTO> getAppointmentDetails(@RequestParam("id")  String  id)  {


            return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAppointmentDetails(UUID.fromString(id)));
        }

    /**
     * Metoda koja vraca termine na osnovu datuma
     * @param date datum kada je termin rezervisan
     * @return HTTP odgovor koji u telu sadrzi listu termina za trazeni datum
     */

    @PostMapping(APPOINTMENT_DATE)
        public ResponseEntity<List<GetForDateAppointmentDTO>> appointmentAvailability(@PathVariable @Valid @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
            return  ResponseEntity.ok(appointmentService.getForDate(date));
        }

    /**
     * Metoda koja vraca termine na osnovu datuma i ucionica u kojoj su rezervisani
     * @param requestAppointmetDateClassroomDTO dto koji sadrzi date i classroomId
     * @return HTTP odgovor koji u telu sadrzi listu termina za trazeni datum i ucionicu
     */
    @PostMapping(APPOINTMENT_CLASSROOM)
        public ResponseEntity<List<GetForDateAppointmentDTO>> classroomAvailability(@RequestBody @Valid RequestAppointmetDateForClassroomDTO requestAppointmetDateClassroomDTO){
            return  ResponseEntity.ok(appointmentService.getForDateAndClassroom(requestAppointmetDateClassroomDTO));
        }

    /**
     * Metoda koja proverava da li se termin moze rezervisati
     * @param dto dto koji sadrzi date,classroomId,start_timeInHours,end_timeInHours
     * @return HTTP odgovor koji u telu sadrzi informaciju o tome da li je termin vec zauzet
     */
    @PostMapping(APPOINTMENT_AVAILABILITY)
        public ResponseEntity<Boolean> getIsClassroomAvailableForDate(@RequestBody @Valid RequestIsClassroomAvailableForDateDTO dto ){
          return  ResponseEntity.ok(appointmentService.IsClassroomAvailableAtDate(dto));
        }

    /**
     * Metoda koja azurira termin
     * @param dto sadrzi podatke termina koji se azuriraju
     * @throws ReservationExistsException ako je termin vec rezervisan
     */
    @PatchMapping(APPOINTMENT_UPDATE)
        public void updateReservation(@RequestBody @Valid UpdateAppointmentDTO dto) throws ReservationExistsException {
             appointmentService.updateReservation(dto);
        }

}
