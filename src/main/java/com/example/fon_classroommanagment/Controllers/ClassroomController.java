package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.ClassroomDetailsDTO;
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
    public ResponseEntity<String> filter(@RequestBody @Valid FilterDTO filterDTO) {
        System.out.println(service.filter(filterDTO));
        return ResponseEntity.ok("");
    }

    @GetMapping("/getClassrooms")
    public ResponseEntity<List<Classroom>> getClassrooms(@RequestParam("page") int page){



    return ResponseEntity.ok( service.getAllClassrooms(page-1));
    }


    @GetMapping("/searchClassroom")
    public ResponseEntity<List<Classroom>> searchClassroom(@RequestBody SearchClassroomDTO dto) {
        List<Classroom> classrooms = service.searchClassroom(dto);
        return ResponseEntity.status(HttpStatus.OK).body(classrooms);

    }
    @GetMapping("/classroomDetails")
    public ResponseEntity<Classroom> classroomDetails(@RequestBody ClassroomDetailsDTO dto) throws ClassroomExistsException {
        Classroom classroom = service.classroomDetails(dto);
        return ResponseEntity.status(HttpStatus.OK).body(classroom);
    }


    @ExceptionHandler(ClassroomExistsException.class)
    public  ResponseEntity<String> HandleClassroomDoesNotExist(ClassroomExistsException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
