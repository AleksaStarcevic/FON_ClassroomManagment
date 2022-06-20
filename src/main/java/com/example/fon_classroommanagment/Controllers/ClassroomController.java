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

        return ResponseEntity.ok(service.filter(filterDTO));
    }

    @GetMapping("/getClassrooms")
    public ResponseEntity<List<ClassroomCardDTO>> getClassrooms(@RequestParam("page")  @Positive(message = "Page mora biti pozitivan broj") int page){

        return  ResponseEntity.ok(service.getAllClassrooms(page-1));


    }


    @PostMapping("/searchClassroom")
    public ResponseEntity<List<ClassroomCardDTO>> searchClassroom(@RequestBody  @Valid SearchClassroomDTO dto) {
       return ResponseEntity.ok(service.searchClassroom(dto));


    }


    @GetMapping("/classroomDetails")
    public ResponseEntity<ClassroomDetailsDTO> classroomDetails(@RequestBody @Valid RequestClassroomDetailsDTO dto) throws ClassroomExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(service.classroomDetails(dto));
    }



    @GetMapping("/GetForDateClassroom")
    public ResponseEntity<List<GetForDateAppointmentDTO>> getAppointmentsForDateClassroom(@RequestBody @Valid RequestIsClassroomAvailableForDateDTO requestAppointmetDateDTO) throws ClassroomExistsException {

        return  ResponseEntity.ok(service.getForDateClassroom(requestAppointmetDateDTO));
    }


    @GetMapping("getClassroomsChip")
    public ResponseEntity<List<ClassroomChipDTO>> getClassroomsAsChip(@RequestParam("name") String name){
        return ResponseEntity.ok(service.getClassroomsAsChips(name));
    }



}
