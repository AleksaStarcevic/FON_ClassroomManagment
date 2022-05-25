package com.example.fon_classroommanagment.Controllers;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.fon_classroommanagment.Events.AccountRegistrationRequestEvent;
import com.example.fon_classroommanagment.Exceptions.AppointmentDoesNotExistsException;
import com.example.fon_classroommanagment.Exceptions.TokenNotFaundException;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.DTO.AccountDTO;
import com.example.fon_classroommanagment.Models.DTO.ChangeEmailDTO;
import com.example.fon_classroommanagment.Models.DTO.ChangePasswordDTO;
import com.example.fon_classroommanagment.Models.User.Account;
import com.example.fon_classroommanagment.Models.User.UserProfile;
import com.example.fon_classroommanagment.Models.User.ValidationToken;
import com.example.fon_classroommanagment.Services.AccountService;
import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@Validated
public class AuthenticationController {

    @Autowired
    private ApplicationEventPublisher publisher;



@Autowired
private AccountService accountService;

    @PostMapping ("/register")
    public void registerAccount(@RequestBody @Valid  AccountDTO accountDTO) throws UserExistsExcetion {
        Account account=accountDTO.CreateAccount();
        ValidationToken token=  accountService.createValidationToken(account);
        accountDTO.setToken(token.getToken());
        publisher.publishEvent(new AccountRegistrationRequestEvent(accountDTO));

    }

    @GetMapping("/registerConfirmed/{token}")
    public void registerAccountConfirmed(@PathVariable("token")  String token) throws TokenNotFaundException, TokenExpiredException {

        accountService.ConfirmAccount(token);

    }





}
