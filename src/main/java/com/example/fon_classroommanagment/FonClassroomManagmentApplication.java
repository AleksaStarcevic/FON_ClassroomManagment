package com.example.fon_classroommanagment;

import com.example.fon_classroommanagment.Controllers.AuthenticationController;
import com.example.fon_classroommanagment.Models.Email.EmailSender;
import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(scanBasePackages={
        "com.example.fon_classroommanagment"})
public class FonClassroomManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(FonClassroomManagmentApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public EmailSender getEmailSender(){
        return  new EmailSender();

    }



}
