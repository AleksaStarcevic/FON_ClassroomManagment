package com.example.fon_classroommanagment.Listener;

import com.example.fon_classroommanagment.Events.EmailEvent;
import com.example.fon_classroommanagment.Models.Email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class EmailListener implements ApplicationListener<EmailEvent> {
@Autowired
private EmailSender sender;

    @Override
    public void onApplicationEvent(EmailEvent event) {
        try {
            sender.sendHtmlMessage(event.getEmail());
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
