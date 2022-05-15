package com.example.fon_classroommanagment.Listener;

import com.example.fon_classroommanagment.Events.ChangePasswordEvent;
import com.example.fon_classroommanagment.Models.Email.Email;
import com.example.fon_classroommanagment.Models.Email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

import static com.example.fon_classroommanagment.Configuration.Constants.EMAIL_HOST_SENDER;
import static com.example.fon_classroommanagment.Configuration.Constants.EMAIL_REGISTRATION_REQUEST_TEMPLATE;

@Component
public class ChangePasswordEventListener implements ApplicationListener<ChangePasswordEvent> {

    @Autowired
    private EmailSender mailSender;
    @Override
    public void onApplicationEvent(ChangePasswordEvent event) {

        String emailTo=event.getEmailDTO().getEmail();


        //Email email=new Email(emailTo,EMAIL_HOST_SENDER,"Change Password",EMAIL_REGISTRATION_REQUEST_TEMPLATE,"Change Password",);
//        try {
//          //  mailSender.sendHtmlMessage(email);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
    }
}
