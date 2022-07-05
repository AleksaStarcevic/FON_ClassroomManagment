package com.example.fon_classroommanagment.Controllers;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.fon_classroommanagment.Exceptions.AppointmentsForUserException;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Models.DTO.appointment.AppointmentRequestedUserDTO;
import com.example.fon_classroommanagment.Models.DTO.appointment.RequestedAppointmentsDTO;
import com.example.fon_classroommanagment.Models.DTO.user.*;
import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.fon_classroommanagment.Configuration.Routes.*;

/**
 * UserController obradjuje zahteve vezane usera aplikacije
 * @author Ilija Radojkovic
 * @version 1.0
 */
@RestController()
@RequestMapping(value = USER_PREFIX)
public class UserController {

    /**
     * zavisnost UserService u kojoj se nalazi logika vezana za usera aplikacije
     */
    @Autowired
    private UserService userService;

    /**
     * Metoda koja vraca detaljne informacije vezane za usera aplikacije
     * @param authentication objekat koji daje spring boot,trenutni ulogovan korisnik
     * @return HTTP odgovor koji u telu sadrzi objekat UserDetailsDTO
     */
    @GetMapping(USER_DETAILS)
    public ResponseEntity<UserDetailsDTO> getUserDetails(Authentication authentication)throws  TokenExpiredException{
        return  ResponseEntity.ok(  userService.getUserDetails(authentication.getName()));
    }

    /**
     * Metoda koja vraca listu termina za trenutnog usera aplikacije
     * @param authentication objekat koji daje spring boot,trenutni ulogovan user
     * @return HTTP odgovor koji u telu sadrzi listu informacija o terminima usera
     */
    @GetMapping(USER_APPOINTMENTS)
    public ResponseEntity<List<AppointmentsForUserDTO>> getAppointmentsForUser(Authentication authentication) throws  AppointmentsForUserException {
        return  ResponseEntity.ok(  userService.getAppointmentsForUser(authentication.getName()));
    }
    /**
     * Metoda koja vraca listu termina koji je user sa id-ijem podneo
     * @param id objekat koji
     * @return HTTP odgovor koji u telu sadrzi listu informacija o terminima usera
     */
    @GetMapping(USER_APPOINTMENTS_PENDING)
    public ResponseEntity<List<AppointmentRequestedUserDTO>> getAppointmentsForUser(@RequestParam("id") Long id){
        return  ResponseEntity.ok(userService.getAppointmentsPendingForUser(id));
    }
    /**
     * Metoda koja menja korisku sifru
     * @param authentication objekat koji daje spring boot,trenutni ulogovan user
     * @param password objekat koji sadrzi informacije za menjanje sifre
     */
    @PostMapping(PASSWORD_RESET)
    public void ChangePassword(@RequestBody @Valid ChangePasswordDTO password, Authentication authentication) throws TokenExpiredException {
        userService.ChangePassword(password,authentication.getName());
    }

    /**
     * Metoda koja menja korisku mejl
     * @param authentication objekat koji daje spring boot,trenutni ulogovan user
     * @param email objekat koji sadrzi informacije za menjanje mejla
     */
    @PostMapping(EMAIL_RESET)
    public void changeEmail(@RequestBody @Valid ChangeEmailDTO email, Authentication authentication) throws TokenExpiredException {
        userService.changeEmail(authentication.getName(),email);
    }

    /**
     * Metoda koja vraca sve termine koji su zahtevani i cekaju dalju obradu
     * @return HTTP odgovor koji u telu sadrzi listu informacija o terminima koji cekaju obradu
     */

    @GetMapping(USER_REQUESTED_APPOINTMENTS)
    public ResponseEntity<List<RequestedAppointmentsDTO>> getRequestedAppointments(){
        return ResponseEntity.ok(userService.getRequestedAppointments());
    }

    /**
     * Metoda koja vraca da li je user admin
     * @param authentication objekat koji daje spring boot,trenutni ulogovan user
     * @return HTTP odgovor koji u telu sadrzi boolean odgovor
     */
    @GetMapping(IS_USER_ADMIN)
    public ResponseEntity<Boolean> isUserAdmin(Authentication authentication){
        return ResponseEntity.ok(userService.isUserAdmin(authentication.getName()));
    }
    /**
     * Metoda koja vraca osnovne informacije o korisnicima (id,naziv,role)
     * @param authentication objekat koji daje spring boot,trenutni ulogovan user
     * @return HTTP odgovor koji u telu sadrzi listu informacija o osnovnim informacijama korisnika
     */
    @GetMapping(COMMON_EMPLOYEE_PERMISSIONS_INFO)
    public ResponseEntity<List<EmployeeAdminCardDTO>> getEmployeePermissions(Authentication authentication){
        return ResponseEntity.ok((userService.getEmployeesPermissions(authentication.getName())));
    }
    /**
     * Metoda koja menja korisku dozvole u sistemu
     * @param  roleDTO objekat koji sadrzi osnovne informacije za promenu dozvola
     */
    @PostMapping(USER_UPDATE_ROLE)
    public void updateRole(@RequestBody @Valid UpdateRoleDTO roleDTO){
        userService.updateRole(roleDTO);
    }
}
