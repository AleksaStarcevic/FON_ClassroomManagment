package com.example.fon_classroommanagment.Repository;

import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.RequestedAppointmentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {



    @Query(value = "select a.id from Appointment  a where a.classroom.id=:classroomId and a.date=:date and " +
            "((a.Start_timeInHours<=:start_timeInHours and a.End_timeInHours>=:end_timeInHours) or " +
            "(a.Start_timeInHours>=:start_timeInHours  and a.Start_timeInHours<:end_timeInHours and  a.End_timeInHours>=:end_timeInHours) " +
            "or (a.Start_timeInHours<=:start_timeInHours and a.End_timeInHours>:start_timeInHours and a.End_timeInHours<=:end_timeInHours) " +
            "or (a.Start_timeInHours>=:start_timeInHours and a.End_timeInHours<=:end_timeInHours))")
    List<String> AppointmentAvailable(@Param("classroomId") Long classroomId,@Param("date") Date date,@Param("start_timeInHours") int start_timeInHours,@Param("end_timeInHours") int end_timeInHours);

    @Query(value = "select a from Appointment a where a.classroom.id =:classroomId and a.date=:date")
    List<Appointment> searchReservationsByClassroomAndDate(@Param("classroomId") Long classroomId, @Param("date") Date date);

    @Transactional
    @Modifying
    @Query(value = "update Appointment a set a.classroom.id=:classroomId" +
            ",a.name=:name" +
            ",a.date=:date" +
            ",a.decription=:description" +
            ",a.reason=:reason" +
            ",a.number_of_attendies=:number_of_attendies" +
            ",a.Start_timeInHours=:start_timeInHours," +
            "a.End_timeInHours=:end_timeInHours," +
            "a.type.id=:type where a.id=:id")
    void updateReservation(UUID id,Long classroomId, String name, Date date, String description, String reason, int number_of_attendies, int start_timeInHours, int end_timeInHours, Long type);

    @Query(value = "select a.id from Appointment  a where a.classroom.id=:classroomId and a.date=:date and " +
            "((a.Start_timeInHours<=:start_timeInHours and a.End_timeInHours>=:end_timeInHours) or " +
            "(a.Start_timeInHours>=:start_timeInHours  and a.Start_timeInHours<:end_timeInHours and  a.End_timeInHours>=:end_timeInHours) " +
            "or (a.Start_timeInHours<=:start_timeInHours and a.End_timeInHours>:start_timeInHours and a.End_timeInHours<=:end_timeInHours) " +
            "or (a.Start_timeInHours>=:start_timeInHours and a.End_timeInHours<=:end_timeInHours)) and a.id<>:id")
    List<String> AppointmentConflict(@Param("id") UUID id,@Param("classroomId") Long classroomId,@Param("date") Date date,@Param("start_timeInHours") int start_timeInHours,@Param("end_timeInHours") int end_timeInHours);




    List<Appointment> findByDate(Date date);


    List<Appointment> findByDateAndClassroom(Date date, Classroom classroom);
    List<Appointment> findByEmployeeId(Long id);

    @Query("select month(a.date),count(a.date) from Appointment a where a.classroom.id =:id group by month(a.date)")
    List<Double[]> reservationsByMonths(Long id);

    @Query("select new com.example.fon_classroommanagment.Models.DTO.RequestedAppointmentsDTO(p.employee.image,p.employee.firstName,p.employee.lastName,count(p)) from Appointment  p order by p.employee.id")
    List<RequestedAppointmentsDTO> getRequestedAppointmentsForUsers();
}
