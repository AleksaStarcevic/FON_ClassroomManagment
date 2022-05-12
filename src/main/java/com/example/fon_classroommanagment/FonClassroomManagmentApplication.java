package com.example.fon_classroommanagment;

import com.example.fon_classroommanagment.Models.Email.EmailSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FonClassroomManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(FonClassroomManagmentApplication.class, args);
    }


    @Bean
    public EmailSender getEmailSender(){
        return  new EmailSender();

    }

}
