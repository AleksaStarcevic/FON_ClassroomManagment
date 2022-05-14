package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void DeleteAppointment(UUID id){
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }
}
