package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void DeleteAppointment(UUID id){
        appointmentRepository.deleteById(id);
    }
}
