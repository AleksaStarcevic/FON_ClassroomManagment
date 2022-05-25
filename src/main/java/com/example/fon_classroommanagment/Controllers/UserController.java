package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.AppointmentsForUserException;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
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
    public void ChangePassword(@RequestBody @Valid ChangePasswordDTO password) throws UserExistsExcetion {
        userService.ChangePassword(password);

    }

    @PatchMapping("/changeEmail")
    public void changeEmail(@RequestBody @Valid ChangeEmailDTO dto) throws UserExistsExcetion {
        userService.changeEmail(dto);

    }

    @GetMapping("/UserDetails")
    public ResponseEntity<UserDetailsDTO> getUserDetails(Authentication authentication){


        return  ResponseEntity.ok(  userService.getUserDetails(authentication.getName()));
    }

    @GetMapping("/getAppointmentsForUser/{id}")
    public ResponseEntity<List<AppointmentsForUserDTO>> getAppointmentsForUser(@PathVariable String id) throws UserExistsExcetion, AppointmentsForUserException {
        return  ResponseEntity.ok(  userService.getAppointmentsForUser(id));
    }

}
