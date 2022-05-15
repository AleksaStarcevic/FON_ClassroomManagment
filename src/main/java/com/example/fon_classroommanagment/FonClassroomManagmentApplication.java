package com.example.fon_classroommanagment;

import com.example.fon_classroommanagment.Models.Email.EmailSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FonClassroomManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(FonClassroomManagmentApplication.class, args);
    }


    @Bean
    public EmailSender getEmailSender(){
        return  new EmailSender();

    }
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

}
