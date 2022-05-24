package com.example.fon_classroommanagment.Listener;

import com.example.fon_classroommanagment.Events.EmailApprovedAppointnemnt;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.Email.Email;
import com.example.fon_classroommanagment.Models.Email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import javax.mail.MessagingException;

import java.util.HashMap;

import static com.example.fon_classroommanagment.Configuration.Constants.EMAIL_APPOINTMENT_APPROVED_TEMPLATE;
import static com.example.fon_classroommanagment.Configuration.Constants.EMAIL_HOST_SENDER;

public class AppointmentApprovedEventListner implements ApplicationListener<EmailApprovedAppointnemnt> {
    @Autowired
    private EmailSender mailSender;
    @Override
    public void onApplicationEvent(EmailApprovedAppointnemnt event) {
        Appointment appointment=event.getAppointment();
        Email email=new Email(appointment.getEmployee().getEmail(),
                EMAIL_HOST_SENDER,
                "Appointment Approved",
                EMAIL_APPOINTMENT_APPROVED_TEMPLATE,
                "Appointment Approved",

                new HashMap<>()
                {{
                    put("nameEmployee", appointment.getEmployee().getFirstName());
                    put("nameClassroom",appointment.getName());
                    put("tip",appointment.getType().getName());
                    put("status",appointment.getStatus().getName());

                }});

        try {
            mailSender.sendHtmlMessage(email);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
