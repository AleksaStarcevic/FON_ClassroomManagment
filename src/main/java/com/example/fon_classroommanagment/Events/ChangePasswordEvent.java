package com.example.fon_classroommanagment.Events;

import com.example.fon_classroommanagment.Models.DTO.EmailDTO;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class ChangePasswordEvent extends ApplicationEvent {

    @Getter
    private final EmailDTO emailDTO;
    public ChangePasswordEvent(EmailDTO source) {
        super(source);
        emailDTO=source;
    }
}
