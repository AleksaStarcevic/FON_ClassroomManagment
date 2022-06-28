package com.example.fon_classroommanagment.Controllers;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.fon_classroommanagment.Exceptions.AppointmentsForUserException;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.fon_classroommanagment.Configuration.Routes.*;


@RestController()
@RequestMapping(value = USER_PREFIX)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(USER_DETAILS)
    public ResponseEntity<UserDetailsDTO> getUserDetails(Authentication authentication)throws  TokenExpiredException{
        return  ResponseEntity.ok(  userService.getUserDetails(authentication.getName()));
    }

    @GetMapping(USER_APPOINTMENTS)
    public ResponseEntity<List<AppointmentsForUserDTO>> getAppointmentsForUser(Authentication authentication) throws  AppointmentsForUserException {
        return  ResponseEntity.ok(  userService.getAppointmentsForUser(authentication.getName()));
    }

    @GetMapping(USER_APPOINTMENTS_PENDING)
    public ResponseEntity<List<AppointmentRequestedUserDTO>> getAppointmentsForUser(@RequestParam("id") Long id){
        System.out.println(id);
        return  ResponseEntity.ok(userService.getAppointmentsPendingForUser(id));
    }
    @PostMapping(PASSWORD_RESET)
    public void ChangePassword(@RequestBody @Valid ChangePasswordDTO password,Authentication authentication) throws TokenExpiredException {
        userService.ChangePassword(password,authentication.getName());
    }

    @PostMapping(EMAIL_RESET)
    public void changeEmail(@RequestBody @Valid ChangeEmailDTO dto, Authentication authentication) throws TokenExpiredException {
        userService.changeEmail(authentication.getName(),dto);
    }


    @GetMapping(USER_REQUESTED_APPOINTMENTS)
    public ResponseEntity<List<RequestedAppointmentsDTO>> getRequestedAppointments(){


        return ResponseEntity.ok(userService.getRequestedAppointments());
    }

    @GetMapping(IS_USER_ADMIN)
    public ResponseEntity<Boolean> isUserAdmin(Authentication authentication){
        return ResponseEntity.ok(userService.isUserAdmin(authentication.getName()));
    }
}
