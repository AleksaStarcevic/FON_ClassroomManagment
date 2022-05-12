package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Events.EmailEvent;
import com.example.fon_classroommanagment.Models.Email.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/sendEmail")
    public  void  sendEmail(@RequestBody Email email){

        publisher.publishEvent(new EmailEvent(email));

    }
}
