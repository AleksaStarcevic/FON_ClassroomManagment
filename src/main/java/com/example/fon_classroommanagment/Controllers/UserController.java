package com.example.fon_classroommanagment.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.DTO.ChangeEmailDTO;
import com.example.fon_classroommanagment.Models.DTO.ChangePasswordDTO;
import com.example.fon_classroommanagment.Models.DTO.UserDetailsDTO;
import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;

import static com.example.fon_classroommanagment.Configuration.Constants.BEARER_STRING;
import static com.example.fon_classroommanagment.Configuration.Constants.SECRET;

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

}
