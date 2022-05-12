package com.example.fon_classroommanagment.Listener;

import com.example.fon_classroommanagment.Events.EmailEvent;
import com.example.fon_classroommanagment.Models.Email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener implements ApplicationListener<EmailEvent> {
@Autowired
private EmailSender sender;

    @Override
    public void onApplicationEvent(EmailEvent event) {
        sender.sendEmail(event.getEmail());
    }
}
