package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.DTO.ReserveDTO;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void DeleteAppointment(String id){
        UUID idAppointment=UUID.fromString(id);
        appointmentRepository.deleteById(idAppointment);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public void ReserveAppointment( ReserveDTO dto) {
        //if(AvailableRoom(dto.getClassroomId(),dto.getDate(),dto.getTimeInHours()));
    }
}
