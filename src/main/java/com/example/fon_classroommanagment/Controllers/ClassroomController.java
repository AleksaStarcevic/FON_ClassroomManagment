package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Constants.RC_TYPE_NAME;

@RestController
@Validated
public class ClassroomController {

    @Autowired
    private ClassroomService service;

    @GetMapping("/filter")
    public ResponseEntity<List<ClassroomCardDTO>> filter(@RequestBody @Valid FilterDTO filterDTO) {
List<Classroom> resultQuiery=service.filter(filterDTO);
List<ClassroomCardDTO> result=CreateClassroomPagingDTOs(resultQuiery);
        return ResponseEntity.ok(result );
    }

    @GetMapping("/getClassrooms")
    public ResponseEntity<List<ClassroomCardDTO>> getClassrooms(@RequestParam("page")  @Positive(message = "Page mora biti pozitivan broj") int page){

List<Classroom> resultQuery=service.getAllClassrooms(page-1);
List<ClassroomCardDTO> result=CreateClassroomPagingDTOs(resultQuery);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/searchClassroom")
    public ResponseEntity<List<ClassroomCardDTO>> searchClassroom(@RequestBody  @Valid SearchClassroomDTO dto) {
        List<Classroom> classrooms = service.searchClassroom(dto);
        List<ClassroomCardDTO> result=CreateClassroomPagingDTOs(classrooms);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }


    @GetMapping("/classroomDetails")
    public ResponseEntity<ClassroomDetailsDTO> classroomDetails(@RequestBody @Valid RequestClassroomDetailsDTO dto) throws ClassroomExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(service.classroomDetails(dto));
    }



    @GetMapping("/GetForDateClassroom")
    public ResponseEntity<List<GetForDateAppointmentDTO>> getAppointmentsForDateClassroom(@RequestBody @Valid RequestIsClassroomAvailableForDateDTO requestAppointmetDateDTO) throws ClassroomExistsException {

        return  ResponseEntity.ok(service.getForDateClassroom(requestAppointmetDateDTO));
    }

    private   List<ClassroomCardDTO> CreateClassroomPagingDTOs(List<Classroom> resultQuery ){
        return resultQuery.stream().map(x->new ClassroomCardDTO(x.getId(),x.getName(),x.getNumber_of_seats(),x.isProjector(),x.getType().getName().equals(RC_TYPE_NAME))).collect(Collectors.toList());

    }


}
