package com.example.fon_classroommanagment.Repository;

import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query(value = "select a.id from Appointment  a where a.classroom.id=:classroomId and a.date=:date")
    Optional<String> AppointmentAvailable(@Param("classroomId") Long classroomId,@Param("date") Date date);

}
