package com.example.fon_classroommanagment.Models.DTO;

import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountDTO {

    private UUID id;



    private String firstName;

    private String lastName;

    private EmployeeDepartment department;


    private EducationTitle title;


    private EmployeeType type;


    private String email;

    private String password;
    private Date timeStamp=new Date();

}
