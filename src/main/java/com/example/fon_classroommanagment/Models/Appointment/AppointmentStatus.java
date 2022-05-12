package com.example.fon_classroommanagment.Models.Appointment;

import com.example.fon_classroommanagment.Models.TypeClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.fon_classroommanagment.Models.Configuration.Constants.APPOINTMENT_STATUS_TABLE_NAME;


@Entity
@Table(name = APPOINTMENT_STATUS_TABLE_NAME)
@NoArgsConstructor
public class AppointmentStatus extends TypeClass {

    public AppointmentStatus(Long id, String name) {
        super(id, name);
    }
}
