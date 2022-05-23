package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Exceptions.ReservationExistsException;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentStatus;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentType;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import com.example.fon_classroommanagment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.fon_classroommanagment.Configuration.Constants.APPOINTMENT_DECLINED;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;

    public void DeleteAppointment(String id) {
        UUID idAppointment = UUID.fromString(id);
        appointmentRepository.deleteById(idAppointment);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Transactional(rollbackFor=ReservationExistsException.class)
    public void ReserveAppointment(List<ReserveDTO> dtoList) throws ReservationExistsException {

        for (ReserveDTO dto:dtoList) {
            if (AvailableRoom(dto.getClassroomId(), dto.getDate(), dto.getStart_timeInHours(), dto.getEnd_timeInHours())) {
                Appointment appointment = new Appointment(UUID.randomUUID(), userRepository.findByEmail(dto.getEmail()).getEmployee(), new Classroom(dto.getClassroomId()), dto.getName(), dto.getDate(), dto.getDecription(), dto.getReason(), dto.getNumber_of_attendies(), dto.getStart_timeInHours(), dto.getEnd_timeInHours(), new AppointmentStatus((long) dto.getStatus()), new AppointmentType((long) dto.getType()));
                appointmentRepository.save(appointment);
            } else {
                throw new ReservationExistsException("Rezervacija je zauzeta,pokusajte drugo vreme");
            }

        }


    }

    private boolean AvailableRoom(Long classroomId, Date date, int start_timeInHours, int end_timeInHours) {
        List<String> o = appointmentRepository.AppointmentAvailable(classroomId, date, start_timeInHours, end_timeInHours);
        System.out.println(o);


        return o.isEmpty();
    }

    public void ConfirmAppointment(ConfirmAppointmentDTO dto) {
        Optional<Appointment> appointment = appointmentRepository.findById(dto.getId());
        System.out.println(dto);
        if (appointment.isPresent()) {
            //send email to person to notify him/her that appointment has changed
            if (dto.getStatus().getName().equals(APPOINTMENT_DECLINED)) {
                //send email thats diclined delete it
                appointmentRepository.deleteById(dto.getId());
            } else {
                appointment.get().setStatus(dto.getStatus());
                appointmentRepository.save(appointment.get());
            }
        }


    }

    public List<Appointment> searchReservation(SearchReservationDTO dto) throws ReservationExistsException {
        List<Appointment> appointments = appointmentRepository.searchReservationByClassroomAndDate(dto.getClassroomId(),dto.getDate());
        if(appointments.isEmpty()) throw new ReservationExistsException("No reservations in given classroom at given date");
      return appointmentRepository.searchReservationByClassroomAndDate(dto.getClassroomId(),dto.getDate());
    }

    public List<Appointment> getForDate(RequestAppointmetDateDTO requestAppointmetDateDTO) {
        return  appointmentRepository.findByDate(requestAppointmetDateDTO.getDatum());
    }
}
