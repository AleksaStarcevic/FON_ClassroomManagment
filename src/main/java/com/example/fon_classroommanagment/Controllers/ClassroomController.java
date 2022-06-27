package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

import static com.example.fon_classroommanagment.Configuration.Constants.MAX_CAPACITY;
import static com.example.fon_classroommanagment.Configuration.Constants.MIN_CAPACITY;
import static com.example.fon_classroommanagment.Configuration.Routes.*;

@RestController
@RequestMapping(CLASSROOM_PREFIX)
@Validated
public class ClassroomController {

    @Autowired
    private ClassroomService service;

//    @PostMapping(CLASSROOM_FILTER)
//    public ResponseEntity<List<ClassroomCardDTO>> filter(@RequestBody @Valid FilterDTO filterDTO) {
//
//        return ResponseEntity.ok(service.filter(filterDTO));
//    }

    @PostMapping(CLASSROOM_PAGING)
    public ResponseEntity<List<ClassroomCardDTO>> getClassrooms(
            @PathVariable("page")  @Valid @Positive(message = "Page mora biti pozitivan broj") int page,
             @RequestBody FilterDTO filterDTO

    ){
        System.out.println(filterDTO.toString());
        return  ResponseEntity.ok(service.getAllClassrooms(page-1,filterDTO));


    }


    @GetMapping(CLASSROOM_SEARCH)
    public ResponseEntity<List<ClassroomCardDTO>> searchClassroom(@PathVariable("page")  @Positive(message = "Page mora biti pozitivan broj") int page,@RequestParam("name") String name) {
       return ResponseEntity.ok(service.searchClassroom(page,name));


    }


    @GetMapping(CLASSROOM_DETAILS)
    public ResponseEntity<ClassroomDetailsDTO> classroomDetails(
            @RequestParam("id")
            @Positive(message = "Id ucionice more biti pozitivan")
            @NotNull(message = "Id ucionice ne sme biti prazan") Long classroomId) throws ClassroomExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(service.classroomDetails(classroomId));
    }



//    @PostMapping(CLASSROOM_APPOITMENTS)
//    public ResponseEntity<List<GetForDateAppointmentDTO>> getAppointmentsForDateClassroom(@RequestBody @Valid RequestIsClassroomAvailableForDateDTO requestAppointmetDateDTO) throws ClassroomExistsException {
//
//        return  ResponseEntity.ok(service.getForDateClassroom(requestAppointmetDateDTO));
//    }


    @GetMapping(CLASSROOM_PARTIAL_INFO)
    public ResponseEntity<List<ClassroomChipDTO>> getClassroomsAsChip(@RequestParam("name") String name){
        return ResponseEntity.ok(service.getClassroomsAsChips(name));
    }
    @GetMapping(CLASSROOM_PAGING_PARTIAL_INFO)
    public ResponseEntity<List<ClassroomChipDTO>> getClassroomsAsChip( @PathVariable("page") @Positive(message = "Page mora biti pozitivan broj")  int page ){
        return ResponseEntity.ok(service.getAllClassroomsAsChips(page));
    }






}
