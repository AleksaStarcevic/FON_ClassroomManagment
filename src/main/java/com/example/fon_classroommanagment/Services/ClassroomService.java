package com.example.fon_classroommanagment.Services;


import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import com.example.fon_classroommanagment.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Constants.PAGE_SIZE;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Classroom> filter(FilterDTO filterDTO) {


          return   classroomRepository.filter(filterDTO.getMin_capacity(),filterDTO.getMax_capacity(),filterDTO.isAircondition(),filterDTO.isProjector());

    }

    public List<Classroom> searchClassroom(SearchClassroomDTO dto)  {
        //if(classrooms.isEmpty()) throw new ClassroomExistsException("Classroom with that name doesn't exist");
        return classroomRepository.findByNameContaining(dto.getName());

    }

    public ClassroomDetailsDTO classroomDetails(RequestClassroomDetailsDTO dto) throws ClassroomExistsException {
        Optional<Classroom> optional = classroomRepository.findById(dto.getId());
        if (optional.isEmpty()) throw new ClassroomExistsException("Classroom with given id doesn't exist");
        Classroom classroom = optional.get();
        List<Double[]> monthsPercentage = appointmentRepository.reservationsByMonths(dto.getId());

        for (Double[] month : monthsPercentage) {
            for (int i = 1; i < month.length; i++) {
                month[i] = (month[i] / 310) * 100;

            }
        }

        ClassroomDetailsDTO result = new ClassroomDetailsDTO(classroom.getName(),
                classroom.getNumber_of_seats(),
                classroom.getNumber_of_computers(),
                classroom.isAircondition(),
                classroom.isProjector(),
                classroom.getType(),
                classroom.getPovrsina(),
                classroom.getSprat(),
                classroom.getBr_tabli(), monthsPercentage);


        return result;
    }


    public List<Classroom> getAllClassrooms(int page) {



        Page<Classroom> all = classroomRepository.findAll(PageRequest.of(page, PAGE_SIZE));
        return all.getContent();
    }
    public List<GetForDateAppointmentDTO> getForDateClassroom(RequestIsClassroomAvailableForDateDTO requestAppointmetDateDTO) throws ClassroomExistsException {

    Optional<Classroom> classroomOpt=classroomRepository.findById(requestAppointmetDateDTO.getClassroomId());

    if(classroomOpt.isPresent()){
        Classroom classroom=classroomOpt.get();

      return getForDateAppointmentDTOS(appointmentRepository.findByDateAndClassroom(requestAppointmetDateDTO.getDate(), classroom));

    }
        throw new ClassroomExistsException("Ucionica ne postoji");
    }
    private List<GetForDateAppointmentDTO> getForDateAppointmentDTOS(   List<Appointment> appointments){
        return appointments.stream().map(x->new GetForDateAppointmentDTO(x.getStart_timeInHours(),x.getEnd_timeInHours(),x.getType().getName(),x.getClassroom().getName(),x.getDecription())).collect(Collectors.toList());
    }


    public List<ClassroomType> getAllClassroomTypes() {
        return classroomRepository.getAllClassroomTypes();
    }
}
