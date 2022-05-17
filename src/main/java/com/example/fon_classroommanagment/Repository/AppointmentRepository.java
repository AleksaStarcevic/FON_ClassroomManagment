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

    @Query(value = "select a.id from Appointment  a where a.classroom.id=:classroomId and a.date=:date and ((a.Start_timeInHours<=:start_timeInHours and a.End_timeInHours>=:end_timeInHours) or (a.Start_timeInHours>=:start_timeInHours  and a.Start_timeInHours<:end_timeInHours and  a.End_timeInHours>=:end_timeInHours) or (a.Start_timeInHours<=:start_timeInHours and a.End_timeInHours>:start_timeInHours and a.End_timeInHours<=:end_timeInHours) or (a.Start_timeInHours>=:start_timeInHours and a.End_timeInHours<=:end_timeInHours))")
    List<String> AppointmentAvailable(@Param("classroomId") Long classroomId,@Param("date") Date date,@Param("start_timeInHours") int start_timeInHours,@Param("end_timeInHours") int end_timeInHours);


}
