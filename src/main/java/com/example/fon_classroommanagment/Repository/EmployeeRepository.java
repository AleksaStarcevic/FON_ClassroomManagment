package com.example.fon_classroommanagment.Repository;

import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmail(String email);
}
