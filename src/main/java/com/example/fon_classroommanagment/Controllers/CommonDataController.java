package com.example.fon_classroommanagment.Controllers;

import com.example.fon_classroommanagment.Models.Appointment.AppointmentType;
import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import com.example.fon_classroommanagment.Models.User.UserRole;
import com.example.fon_classroommanagment.Services.ClassroomService;
import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.fon_classroommanagment.Configuration.Routes.*;

@RestController
@RequestMapping(COMMON_PREFIX)
public class CommonDataController {

    @Autowired private UserService userService;
    @Autowired
    private ClassroomService classrromService;

    @GetMapping(COMMON_ALL_EMPLOYEE_TYPES)
    public ResponseEntity<List<EmployeeType>> getAllEmployeeTypes(){
        return ResponseEntity.ok(userService.getAllEmpoyeeTypes());
    }

    @GetMapping(COMMON_ALL_EDUCATION_TITLES)
    public ResponseEntity<List<EducationTitle>> getAllEducationTitles(){
        return ResponseEntity.ok(userService.getAllEducationTitles());
    }
    @GetMapping(COMMON_ALL_EMPLOYEE_DEPARTMENTS)
    public ResponseEntity<List<EmployeeDepartment>> getAllEmployeeDepartments(){
        return ResponseEntity.ok(userService.getAllEmployeeDepartments());
    }

    @GetMapping(COMMON_ALL_CLASSROOM_TYPES)
    public ResponseEntity<List<ClassroomType>> getAllClassroomTypes(){
        return ResponseEntity.ok(classrromService.getAllClassroomTypes());
    }

    @GetMapping(COMMON_ALL_APPOINTMENT_TYPES)
    public ResponseEntity<List<AppointmentType>> getAllAppointmentTypes(){
        return ResponseEntity.ok(classrromService.getAllAppointmentTypes());
    }
    @GetMapping(COMMON_USER_ROLES)
    public ResponseEntity<List<UserRole>> getAllUserRoles(){
        return ResponseEntity.ok(classrromService.getAllUserRoles());
    }
}
