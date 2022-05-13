package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Events.AccountRegistrationRequestEvent;
import com.example.fon_classroommanagment.Models.DTO.AccountDTO;
import com.example.fon_classroommanagment.Models.User.Account;
import com.example.fon_classroommanagment.Models.User.ValidationToken;
import com.example.fon_classroommanagment.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public void registerAccount(@RequestBody AccountDTO accountDTO){
        ValidationToken token=accountService.createValidationToken(accountDTO, UUID.randomUUID().toString());

        Account account=new Account(accountDTO.getEmail(),
                accountDTO.getFirstName(),
                accountDTO.getLastName(),
                accountDTO.getDepartment(),
                accountDTO.getTitle(),
                accountDTO.getType(),
               encoder.encode(accountDTO.getPassword()),
                token);
accountService.SaveAccount(account);
        publisher.publishEvent(new AccountRegistrationRequestEvent(accountDTO));

    }
}
