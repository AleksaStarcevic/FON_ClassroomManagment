package com.example.fon_classroommanagment.Events;

import com.example.fon_classroommanagment.Models.DTO.AccountDTO;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class AccountRegistrationRequestEvent extends ApplicationEvent {

    @Getter
    private final AccountDTO dto;
    public AccountRegistrationRequestEvent(AccountDTO source) {
        super(source);
        this.dto=source;
    }
}
