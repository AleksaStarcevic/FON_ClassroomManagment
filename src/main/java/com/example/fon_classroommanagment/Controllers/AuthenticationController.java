package com.example.fon_classroommanagment.Controllers;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.fon_classroommanagment.Events.AccountRegistrationRequestEvent;
import com.example.fon_classroommanagment.Exceptions.TokenNotFaundException;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.DTO.UserRegistrationDTO;
import com.example.fon_classroommanagment.Models.User.Account;
import com.example.fon_classroommanagment.Models.User.ValidationToken;
import com.example.fon_classroommanagment.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.fon_classroommanagment.Configuration.Routes.REGISTER;
import static com.example.fon_classroommanagment.Configuration.Routes.REGISTER_CONFIRM;

@RestController
@Validated
public class AuthenticationController {

    @Autowired
    private ApplicationEventPublisher publisher;



@Autowired
private AccountService accountService;

    @PostMapping (REGISTER)
    public void registerAccount(@RequestBody() UserRegistrationDTO registerDTO) throws UserExistsExcetion {
       Account account=registerDTO.createAccount();
        ValidationToken token=  accountService.createValidationToken(account);
        account.setToken(token.getToken());

        publisher.publishEvent(new AccountRegistrationRequestEvent(account));

    }

    @GetMapping(REGISTER_CONFIRM)
    public void registerAccountConfirmed(@PathVariable("token")  String token) throws TokenNotFaundException, TokenExpiredException {
        accountService.ConfirmAccount(token);
    }





}
