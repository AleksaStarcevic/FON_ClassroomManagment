package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.ClassroomDetailsDTO;
import com.example.fon_classroommanagment.Models.DTO.RequestClassroomDetailsDTO;
import com.example.fon_classroommanagment.Models.DTO.FilterDTO;
import com.example.fon_classroommanagment.Models.DTO.SearchClassroomDTO;
import com.example.fon_classroommanagment.Services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClassroomController {

    @Autowired
    private ClassroomService service;

    @GetMapping("/filter")
    public ResponseEntity<List<Classroom>> filter(@RequestBody @Valid FilterDTO filterDTO) {

        return ResponseEntity.ok( service.filter(filterDTO));
    }

    @GetMapping("/getClassrooms")
    public ResponseEntity<List<Classroom>> getClassrooms(@RequestParam("page") int page){



    return ResponseEntity.ok( service.getAllClassrooms(page-1));
    }


    @GetMapping("/searchClassroom")
    public ResponseEntity<List<Classroom>> searchClassroom(@RequestBody  @Valid SearchClassroomDTO dto) {
        List<Classroom> classrooms = service.searchClassroom(dto);
        return ResponseEntity.status(HttpStatus.OK).body(classrooms);

    }
    @GetMapping("/classroomDetails")
    public ResponseEntity<ClassroomDetailsDTO> classroomDetails(@RequestBody @Valid RequestClassroomDetailsDTO dto) throws ClassroomExistsException {
        Classroom classroom = service.classroomDetails(dto);
        ClassroomDetailsDTO result=new ClassroomDetailsDTO(classroom.getName(),classroom.getNumber_of_seats(),classroom.getNumber_of_computers(),classroom.isAircondition(),classroom.isProjector(),classroom.getType());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @ExceptionHandler(ClassroomExistsException.class)
    public  ResponseEntity<String> HandleClassroomDoesNotExist(ClassroomExistsException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
