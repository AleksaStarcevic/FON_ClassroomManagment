package com.example.fon_classroommanagment.Repository;

import com.example.fon_classroommanagment.Models.DTO.EmployeeAdminCardDTO;
import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Employee u set u.email=:email where u.id=:id")

    void changeEmail(@Param("id") Long id,@Param("email") String emailToChanged);


}
