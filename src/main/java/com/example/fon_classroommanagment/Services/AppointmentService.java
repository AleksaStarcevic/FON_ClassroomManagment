package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Configuration.ExceptionMessages;
import com.example.fon_classroommanagment.Exceptions.AppointmentDoesNotExistsException;
import com.example.fon_classroommanagment.Exceptions.ReservationExistsException;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentStatus;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentType;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import com.example.fon_classroommanagment.Repository.EmployeeRepository;
import com.example.fon_classroommanagment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Constants.*;
import static com.example.fon_classroommanagment.Configuration.ExceptionMessages.USER_EXISTS;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private       ApplicationEventPublisher publisher;

    public void DeleteAppointment(String id, String email) throws UserExistsExcetion {
       UUID idAppointment = UUID.fromString(id);
        Employee empl=employeeRepository.findByEmail(email);

        if(empl==null) throw new UserExistsExcetion(USER_EXISTS);
        appointmentRepository.deleteByIdAndAndEmployee(idAppointment,empl);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Transactional(rollbackFor=ReservationExistsException.class)
    public void ReserveAppointment(List<ReserveDTO> dtoList, String role) throws ReservationExistsException {

        for (ReserveDTO dto:dtoList) {
            if (AvailableRoom(dto.getClassroomId(), dto.getDate(), dto.getStart_timeInHours(), dto.getEnd_timeInHours())) {


                if(role.equals(ADMIN_NAME_TYPE_ROLE))
                     dto.setStatus(STATUS_APPROVED);

                    Appointment appointment = new Appointment(UUID.randomUUID(), userRepository.findByEmail(dto.getEmail()).getEmployee(), new Classroom(dto.getClassroomId()), dto.getName(), dto.getDate(), dto.getDecription(), dto.getReason(), dto.getNumber_of_attendies(), dto.getStart_timeInHours(), dto.getEnd_timeInHours(), new AppointmentStatus((long) dto.getStatus()), new AppointmentType((long) dto.getType()));
                appointmentRepository.save(appointment);
            } else {
                throw new ReservationExistsException(ExceptionMessages.APPOINTMENT_RESERVED);
            }

        }


    }

    private boolean AvailableRoom(Long classroomId, Date date, int start_timeInHours, int end_timeInHours) {
        List<String> o = appointmentRepository.AppointmentAvailable(classroomId, date, start_timeInHours, end_timeInHours);
        System.out.println(o);


        return o.isEmpty();
    }



    public List<Appointment> searchReservation(SearchReservationDTO dto) throws ReservationExistsException {
        List<Appointment> appointments = appointmentRepository.searchReservationsByClassroomAndDate(dto.getClassroomId(),dto.getDate());
        if(appointments.isEmpty()) throw new ReservationExistsException(ExceptionMessages.APPOINTMENT_NOT_FOUND);
      return appointmentRepository.searchReservationsByClassroomAndDate(dto.getClassroomId(),dto.getDate());
    }

    public List<GetForDateAppointmentDTO> getForDate(Date date) {
        return  getForDateAppointmentDTOS(appointmentRepository.findByDate(date));
    }
    private List<GetForDateAppointmentDTO> getForDateAppointmentDTOS(   List<Appointment> appointments){
        return appointments.stream().map(x->new GetForDateAppointmentDTO(x.getStart_timeInHours(),x.getEnd_timeInHours(),x.getType().getName(),x.getClassroom().getName(),x.getDecription())).collect(Collectors.toList());
    }
    public boolean IsClassroomAvailableAtDate(RequestIsClassroomAvailableForDateDTO dto) {

        return AvailableRoom(dto.getClassroomId(), dto.getDate(), dto.getStart_timeInHours(), dto.getEnd_timeInHours());


    }


    public void updateReservation(UpdateReservationDTO dto) throws ReservationExistsException {
        if (AppointmentConflict(dto)) {
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
            throw new ReservationExistsException(ExceptionMessages.APPOINTMENT_RESERVED);
        }


    }

    private boolean AppointmentConflict(UpdateReservationDTO dto) {
        List<String> o = appointmentRepository.AppointmentConflict(dto.getId(),dto.getClassroomId(), dto.getDate(), dto.getStart_timeInHours(), dto.getEnd_timeInHours());
        System.out.println(o);


        return o.isEmpty();
    }





    private Optional<Appointment> FindById(UUID id){
     return    appointmentRepository.findById(id);
    }


    public List<GetForDateAppointmentDTO> getForDateAndClassroom(RequestAppointmetDaetForClassroomDTO requestAppointmetDateClassroomDTO) {
        List<Appointment> byDateAndClassroom = appointmentRepository.findByDateAndClassroom(requestAppointmetDateClassroomDTO.getDate(), requestAppointmetDateClassroomDTO.getClassroomId());
        return getForDateAppointmentDTOS((byDateAndClassroom));

    }


    public void DeclineAppointment(UUID appointmentId) throws AppointmentDoesNotExistsException {
        //da li da ostane u bazi kao declined ili da se obrise?
        ChangeAppointment(new AppointmentStatus(STATUS_DECLINED),appointmentId);
    }

    public void ConfirmAppointment(UUID appointmentId) throws AppointmentDoesNotExistsException {

        ChangeAppointment(new AppointmentStatus(STATUS_APPROVED),appointmentId);

    }

    public void ConfirmAllAppointments(List<UUID> dto) throws AppointmentDoesNotExistsException {
        for (UUID appointmentDTO: dto) {
            ConfirmAppointment(appointmentDTO);

        }
    }

    private void ChangeAppointment(AppointmentStatus status,UUID appointmentId) throws AppointmentDoesNotExistsException {
        Optional<Appointment> appointment = FindById(appointmentId);

        if (appointment.isPresent()) {
            appointment.get().setStatus(status);
           // publisher.publishEvent(new EmailApprovedAppointnemnt(appointment.get()));

            appointmentRepository.save(appointment.get());


        }else{
            throw  new AppointmentDoesNotExistsException(ExceptionMessages.APPOINTMENT_EXIST);
        }
    }


}
