package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestedUserDTO {

    private UUID id;
    private String classroomName;
    private String title;
    private Date date;
    private int startTime;
    private int endTime;
}
