package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import com.example.fon_classroommanagment.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findByEmail(String email) {
       return  employeeRepository.findByEmail(email);
    }

    public void changeEmail(String email, String emailToChanged) {
        Employee employee = findByEmail(email);
        employeeRepository.changeEmail(employee.getId(),emailToChanged);
    }



    public void save(String image, Long id, String firstName, String lastName) {
        employeeRepository.saveImage(image,id,firstName,lastName);
    }
}
