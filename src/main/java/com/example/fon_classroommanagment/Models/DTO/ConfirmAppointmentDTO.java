package com.example.fon_classroommanagment.Models.DTO;

import com.example.fon_classroommanagment.Models.Appointment.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmAppointmentDTO {
    private UUID id;
    @NotNull(message = "Status ne sme biti prazan")
    private AppointmentStatus status;
}
