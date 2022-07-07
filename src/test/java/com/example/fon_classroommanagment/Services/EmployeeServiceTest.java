package com.example.fon_classroommanagment.Services;


import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import com.example.fon_classroommanagment.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


class EmployeeServiceTest {

    private  static EmployeeService service;
    private  static EmployeeRepository employeeRepository;

    @BeforeAll
    static void Start(){
       employeeRepository = mock(EmployeeRepository.class);
       service = new EmployeeService(employeeRepository);
    }


    @Test
    void findByEmail() {
        String email = "a@gmail.com";
      Employee employee = new Employee(1L,"aleks","starc",new EmployeeDepartment(1L,"test"),new EducationTitle(1L,"test"),new EmployeeType(1L,"test"),"a@gmail.com","");

        when(employeeRepository.findByEmail(email)).thenReturn(employee);

    }

    @Test
    void changeEmail() {
        String newEmail="b@gmail.com";
        Employee employee = new Employee(1L,"aleks","starc",new EmployeeDepartment(1L,"test"),new EducationTitle(1L,"test"),new EmployeeType(1L,"test"),"a@gmail.com","");

        when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(employee);
        service.findByEmail(employee.getEmail());
        verify(employeeRepository,times(1)).findByEmail(employee.getEmail());

        service.changeEmail(employee.getEmail(),newEmail);
        verify(employeeRepository,times(1)).changeEmail(employee.getId(),newEmail);
    }

    @Test
    void save() {
        String image="";
        Long id = 1L;
        String firstName = "A";
        String lastName = "B";

        service.save(image,id,firstName,lastName);
        verify(employeeRepository,times(1)).saveImage(image,id,firstName,lastName);
    }
}