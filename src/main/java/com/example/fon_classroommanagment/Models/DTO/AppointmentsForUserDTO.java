package com.example.fon_classroommanagment.Models.DTO;

import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentsForUserDTO {

   private String classroomName;
   private String appointmentName;
   private Date date;
   private int start_timeInHours;
   private int end_timeInHours;
}
