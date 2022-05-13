package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Events.AccountRegistrationRequestEvent;
import com.example.fon_classroommanagment.Models.DTO.AccountDTO;
import com.example.fon_classroommanagment.Models.User.Account;
import com.example.fon_classroommanagment.Models.User.ValidationToken;
import com.example.fon_classroommanagment.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class AuthenticationController {

    @Autowired
    private ApplicationEventPublisher publisher;

@Autowired
private BCryptPasswordEncoder encoder;
@Autowired
private AccountService accountService;

    @PostMapping ("/register")
    public void registerAccount(@RequestBody @Valid  AccountDTO accountDTO){
        Account account=new Account(accountDTO.getEmail(),
                accountDTO.getFirstName(),
                accountDTO.getLastName(),
                accountDTO.getDepartment(),
                accountDTO.getTitle(),
                accountDTO.getType(),
               encoder.encode(accountDTO.getPassword())
               );
        ValidationToken token=  accountService.createValidationToken(account,UUID.randomUUID().toString());
accountDTO.setToken(token.getToken());


        publisher.publishEvent(new AccountRegistrationRequestEvent(accountDTO));

    }

    @GetMapping("/registerConfirmed/{token}")
    public void registerAccountConfirmed(@PathVariable("token") String token){

        accountService.ConfirmAccount(token);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<String> HandleMethodArgumentsNotValid(MethodArgumentNotValidException exception){
       return ResponseEntity.badRequest().body
               (exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }
}
