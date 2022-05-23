package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Constants.RC_TYPE_NAME;

@RestController
public class ClassroomController {

    @Autowired
    private ClassroomService service;

    @GetMapping("/filter")
    public ResponseEntity<List<ClassroomPagingDTO>> filter(@RequestBody @Valid FilterDTO filterDTO) {
List<Classroom> resultQuiery=service.filter(filterDTO);
List<ClassroomPagingDTO> result=CreateClassroomPagingDTOs(resultQuiery);
        return ResponseEntity.ok(result );
    }

    @GetMapping("/getClassrooms")
    public ResponseEntity<List<ClassroomPagingDTO>> getClassrooms(@RequestParam("page") int page){

List<Classroom> resultQuery=service.getAllClassrooms(page-1);
List<ClassroomPagingDTO> result=CreateClassroomPagingDTOs(resultQuery);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/searchClassroom")
    public ResponseEntity<List<ClassroomPagingDTO>> searchClassroom(@RequestBody  @Valid SearchClassroomDTO dto) {
        List<Classroom> classrooms = service.searchClassroom(dto);
        List<ClassroomPagingDTO> result=CreateClassroomPagingDTOs(classrooms);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    private   List<ClassroomPagingDTO> CreateClassroomPagingDTOs( List<Classroom> resultQuery ){
        return resultQuery.stream().map(x->new ClassroomPagingDTO(x.getName(),x.getNumber_of_seats(),x.isProjector(),x.getType().getName().equals(RC_TYPE_NAME))).collect(Collectors.toList());

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
