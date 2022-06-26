package com.example.fon_classroommanagment.Services;


import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentType;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import com.example.fon_classroommanagment.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Constants.*;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<ClassroomCardDTO> filter(FilterDTO filterDTO) {


          List<Classroom> result =classroomRepository.filter(filterDTO.getMin_capacity(),filterDTO.getMax_capacity(),filterDTO.isAircondition(),filterDTO.isProjector());
        return CreateClassroomPagingDTOs(result);
    }

    public List<ClassroomCardDTO> searchClassroom(SearchClassroomDTO dto)  {
        //if(classrooms.isEmpty()) throw new ClassroomExistsException("Classroom with that name doesn't exist");
       List<Classroom> result= classroomRepository.findByNameContaining("%"+dto.getName()+"%",PageRequest.of(dto.getPage()-1, PAGE_SIZE)).getContent();
        return CreateClassroomPagingDTOs(result);


    }

    private   List<ClassroomCardDTO> CreateClassroomPagingDTOs(List<Classroom> resultQuery ){
        return resultQuery.stream().map(x->new ClassroomCardDTO(x.getId(),x.getName(),x.getNumber_of_seats(),x.isProjector(),x.getType().getName().equals(RC_TYPE_NAME))).collect(Collectors.toList());

    }

    public ClassroomDetailsDTO classroomDetails(Long classroomId) throws ClassroomExistsException {
        Optional<Classroom> optional = classroomRepository.findById(classroomId);
        if (optional.isEmpty()) throw new ClassroomExistsException("Classroom with given id doesn't exist");
        Classroom classroom = optional.get();
        List<Double[]> monthsPercentage = appointmentRepository.reservationsByMonths(classroomId);

        for (Double[] month : monthsPercentage) {
            for (int i = 1; i < month.length; i++) {
                month[i] = (month[i] / 310) * 100;

            }
        }

        return new  ClassroomDetailsDTO(classroom.getName(),
                classroom.getNumber_of_seats(),
                classroom.getNumber_of_computers(),
                classroom.isAircondition(),
                classroom.isProjector(),
                classroom.getType(),
                classroom.getPovrsina(),
                classroom.getSprat(),
                classroom.getBr_tabli(), monthsPercentage);



    }


    public List<ClassroomCardDTO> getAllClassrooms(int page) {



        Page<Classroom> all = classroomRepository.findAll(PageRequest.of(page, PAGE_SIZE));
        return CreateClassroomPagingDTOs(all.getContent());
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

    public List<AppointmentType> getAllAppointmentTypes() {
        return appointmentRepository.getAllAppointmentTypes();
    }

    public List<ClassroomChipDTO> getClassroomsAsChips(String name) {

      List<Classroom> result= classroomRepository.findByNameChips("%"+name+"%", Pageable.ofSize(CHIP_SEARCH_ELEMENTS)).getContent();
return CreateClassroomChipDTOs(result);
    }
    public List<ClassroomChipDTO> getAllClassroomsAsChips(int page) {

        Page<ClassroomChipDTO> all = classroomRepository.findAll(PageRequest.of(page-1, PAGE_SIZE)).map(x-> new ClassroomChipDTO(x.getId(),x.getName()));
        return all.getContent();
    }

    private   List<ClassroomChipDTO> CreateClassroomChipDTOs(List<Classroom> resultQuery ){
        return resultQuery.stream().map(x->new ClassroomChipDTO(x.getId(),x.getName())).collect(Collectors.toList());

    }

}
