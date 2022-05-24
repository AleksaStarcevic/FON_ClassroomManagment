package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Events.EmailApprovedAppointnemnt;
import com.example.fon_classroommanagment.Exceptions.ReservationExistsException;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentStatus;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentType;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import com.example.fon_classroommanagment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Constants.APPOINTMENT_DECLINED;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private       ApplicationEventPublisher publisher;

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
                throw new ReservationExistsException("Rezervacija "+dto.getName()+" datuma: "+dto.getDate()+" ,se ne moze rezervisati,pogledajte da li se dobro uneli podatke,ili je neko vec rezervisao ucionicu pre vas");
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
        System.out.println(appointment);
        if (appointment.isPresent()) {
            //send email to person to notify him/her that appointment has changed
            if (dto.getStatus().getName().equals(APPOINTMENT_DECLINED)) {
                //send email thats diclined delete it
                appointmentRepository.deleteById(dto.getId());
            } else {
                publisher.publishEvent(new EmailApprovedAppointnemnt(appointment.get()));
                appointment.get().setStatus(dto.getStatus());
                appointmentRepository.save(appointment.get());
            }
        }else{
            //ne postoji appointment vrati poruku
        }


    }

    public List<Appointment> searchReservation(SearchReservationDTO dto) throws ReservationExistsException {
        List<Appointment> appointments = appointmentRepository.searchReservationsByClassroomAndDate(dto.getClassroomId(),dto.getDate());
        if(appointments.isEmpty()) throw new ReservationExistsException("No reservations in given classroom at given date");
      return appointmentRepository.searchReservationsByClassroomAndDate(dto.getClassroomId(),dto.getDate());
    }

    public List<GetForDateAppointmentDTO> getForDate(RequestAppointmetDateDTO requestAppointmetDateDTO) {
        return  getForDateAppointmentDTOS(appointmentRepository.findByDate(requestAppointmetDateDTO.getDatum()));
    }
    private List<GetForDateAppointmentDTO> getForDateAppointmentDTOS(   List<Appointment> appointments){
        return appointments.stream().map(x->new GetForDateAppointmentDTO(x.getStart_timeInHours(),x.getEnd_timeInHours(),x.getType().getName(),x.getClassroom().getName(),x.getDecription())).collect(Collectors.toList());
    }
    public boolean IsClassroomAvailableAtDate(RequestIsClassroomAvailableForDateDTO dto) {

        List<Appointment> resQuery=appointmentRepository.findByDateAndClassroom(dto.getDate(),new Classroom(dto.getClassroomId()));


        return resQuery.size()==0;
    }

    public void updateReservation(UpdateReservationDTO dto) throws ReservationExistsException {
        if (AppointmentAvailableExceptThis(dto.getId(),dto.getClassroomId(), dto.getDate(), dto.getStart_timeInHours(), dto.getEnd_timeInHours())) {
            appointmentRepository.updateReservation(dto.getId(),dto.getClassroomId(),
                    dto.getName(),
                    dto.getDate(),
                    dto.getDecription(),
                    dto.getReason(),
                    dto.getNumber_of_attendies(),
                    dto.getStart_timeInHours(),
                    dto.getEnd_timeInHours(),
                    dto.getType());
        } else {
            throw new ReservationExistsException("Rezervacija je zauzeta,pokusajte drugo vreme");
        }


    }

    private boolean AppointmentAvailableExceptThis(UUID id,Long classroomId, Date date, int start_timeInHours, int end_timeInHours) {
        List<String> o = appointmentRepository.AppointmentAvailableExceptThis(id,classroomId, date, start_timeInHours, end_timeInHours);
        System.out.println(o);


        return o.isEmpty();
    }




    public void ConfirmAllAppointments(List<ConfirmAppointmentDTO> dto) {
        for (ConfirmAppointmentDTO appointmentDTO: dto) {
            ConfirmAppointment(appointmentDTO);

        }
    }

}
