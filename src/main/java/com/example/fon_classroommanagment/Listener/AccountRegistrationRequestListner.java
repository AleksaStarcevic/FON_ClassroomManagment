package com.example.fon_classroommanagment.Listener;

import com.example.fon_classroommanagment.Events.AccountRegistrationRequestEvent;
import com.example.fon_classroommanagment.Models.DTO.AccountDTO;
import com.example.fon_classroommanagment.Models.Email.Email;
import com.example.fon_classroommanagment.Models.Email.EmailSender;
import com.example.fon_classroommanagment.Models.User.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.HashMap;

import static com.example.fon_classroommanagment.Configuration.Constants.EMAIL_HOST_SENDER;
import static com.example.fon_classroommanagment.Configuration.Constants.EMAIL_REGISTRATION_REQUEST_TEMPLATE;

@Component
public class AccountRegistrationRequestListner implements ApplicationListener<AccountRegistrationRequestEvent> {

  @Autowired
  private EmailSender mailSender;
    @Override
    public void onApplicationEvent(AccountRegistrationRequestEvent event) {
        AccountDTO dto=event.getDto();
        Email email=new Email(dto.getEmail(),EMAIL_HOST_SENDER,"test1",EMAIL_REGISTRATION_REQUEST_TEMPLATE,"Registration",
                new HashMap<>()
                {{
                    put("name", dto.getFirstName());
                    put("path","/registerConfirmed/"+dto.getToken());

                }});
        try {
            mailSender.sendHtmlMessage(email);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }


    }
}
