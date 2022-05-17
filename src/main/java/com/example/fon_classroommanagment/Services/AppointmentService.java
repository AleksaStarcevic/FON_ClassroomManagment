package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Exceptions.ReservationExistsException;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentStatus;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentType;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.ReserveDTO;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import com.example.fon_classroommanagment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;

    public void DeleteAppointment(String id){
        UUID idAppointment=UUID.fromString(id);
        appointmentRepository.deleteById(idAppointment);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public void ReserveAppointment( ReserveDTO dto) throws ReservationExistsException {
        if(AvailableRoom(dto.getClassroomId(),dto.getDate(),dto.getStart_timeInHours(),dto.getEnd_timeInHours())){
        Appointment appointment=new Appointment(UUID.randomUUID(),userRepository.findByEmail(dto.getEmail()).getEmployee(),new Classroom(dto.getClassroomId()),dto.getName(),dto.getDate(),dto.getDecription(),dto.getReason(),dto.getNumber_of_attendies(),dto.getStart_timeInHours(),dto.getEnd_timeInHours(),new AppointmentStatus((long) dto.getStatus()),new AppointmentType((long) dto.getType()));
            appointmentRepository.save(appointment);
        }
        else{
            throw new ReservationExistsException("Rezervacija je zauzeta,pokusajte drugo vreme");
        }
    }

    private boolean AvailableRoom(Long classroomId, Date date, int start_timeInHours, int end_timeInHours) {
        Optional<String> o=appointmentRepository.AppointmentAvailable(classroomId,date);
        System.out.println(o);
        o.ifPresent(System.out::println);

        return false;
    }


}
