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

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/ChangePassword")
    public void ChangePassword(@RequestBody @Valid ChangePasswordDTO password,Authentication authentication) throws TokenExpiredException {

        userService.ChangePassword(password,authentication.getName());

    }

    @PostMapping("/changeEmail")
    public void changeEmail(@RequestBody @Valid ChangeEmailDTO dto, Authentication authentication) throws TokenExpiredException {
        userService.changeEmail(authentication.getName(),dto);

    }

    @GetMapping("/UserDetails")
    public ResponseEntity<UserDetailsDTO> getUserDetails(Authentication authentication)throws  TokenExpiredException{


        return  ResponseEntity.ok(  userService.getUserDetails(authentication.getName()));
    }

    @GetMapping("/getAppointmentsForUser")
    public ResponseEntity<List<AppointmentsForUserDTO>> getAppointmentsForUser(Authentication authentication) throws  AppointmentsForUserException {
        return  ResponseEntity.ok(  userService.getAppointmentsForUser(authentication.getName()));
    }

    @GetMapping("/requestedAppointments")
    public ResponseEntity<List<RequestedAppointmentsDTO>> getRequestedAppointments(){


        return ResponseEntity.ok(userService.getRequestedAppointments());
    }

    @GetMapping("/isAdmin")
    public ResponseEntity<Boolean> isUserAdmin(Authentication authentication){
        return ResponseEntity.ok(userService.isUserAdmin(authentication.getName()));
    }
}
