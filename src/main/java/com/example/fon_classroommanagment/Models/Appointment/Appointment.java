package com.example.fon_classroommanagment.Models.Appointment;

import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

import static com.example.fon_classroommanagment.Configuration.Constants.APPOINTMENT_TABLE_NAME;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = APPOINTMENT_TABLE_NAME)
public class Appointment {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne( optional = false)
    private Employee employee;




    @ManyToOne( optional = false)
    private Classroom classroom;



    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String name;

    @Column(columnDefinition = "DATE",nullable = false)
    private Date date;



    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private  String decription;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String reason;

    @Column(columnDefinition = "INT(11) UNSIGNED",nullable = false)
    private int number_of_attendies;

    @Column(columnDefinition = "INT(11) UNSIGNED",nullable = false)
    private int timeInHours;

    @ManyToOne( optional = false)
    @JoinColumn
    private AppointmentStatus status;

    @ManyToOne( optional = false)
    @JoinColumn
    private AppointmentType type;


}
