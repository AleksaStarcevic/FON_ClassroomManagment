package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Models.Appointment.AppointmentType;
import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import com.example.fon_classroommanagment.Services.ClassroomService;
import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonDataController {

    @Autowired private UserService userService;
    @Autowired
    private ClassroomService classrromService;

    @GetMapping("/allEmployeeTypes")
    public ResponseEntity<List<EmployeeType>> getAllEmployeeTypes(){
        return ResponseEntity.ok(userService.getAllEmpoyeeTypes());
    }

    @GetMapping("/allEducationTitle")
    public ResponseEntity<List<EducationTitle>> getAllEducationTitles(){
        return ResponseEntity.ok(userService.getAllEducationTitles());
    }
    @GetMapping("/allEmployeeDepartment")
    public ResponseEntity<List<EmployeeDepartment>> getAllEmployeeDepartments(){
        return ResponseEntity.ok(userService.getAllEmployeeDepartments());
    }

    @GetMapping("/allClassroomTypes")
    public ResponseEntity<List<ClassroomType>> getAllClassroomTypes(){
        return ResponseEntity.ok(classrromService.getAllClassroomTypes());
    }

    @GetMapping("/allAppointmentTypes")
    public ResponseEntity<List<AppointmentType>> getAllAppointmentTypes(){
        return ResponseEntity.ok(classrromService.getAllAppointmentTypes());
    }
}
