package com.example.fon_classroommanagment.Events;

import org.springframework.context.ApplicationEvent;

public class EmailApprovedAppointnemnt extends ApplicationEvent {
    public EmailApprovedAppointnemnt(Object source) {
        super(source);
    }
}
