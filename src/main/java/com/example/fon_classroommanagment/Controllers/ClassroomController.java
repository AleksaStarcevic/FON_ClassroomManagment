package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Models.DTO.FilterDTO;
import com.example.fon_classroommanagment.Services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ClassroomController {

    @Autowired
    private ClassroomService service;

    @GetMapping("/filter")
    public ResponseEntity<String> filter(@RequestBody @Valid FilterDTO filterDTO){
        System.out.println(service.filter(filterDTO));
        return   ResponseEntity.ok("");
    }
}
