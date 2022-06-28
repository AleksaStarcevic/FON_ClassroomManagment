package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestedUserDTO {

    private String classroomName;
    private String title;
    private Date date;
    private int startTime;
    private int endTime;
}
