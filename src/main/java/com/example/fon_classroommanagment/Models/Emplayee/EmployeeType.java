package com.example.fon_classroommanagment.Models.Emplayee;

import com.example.fon_classroommanagment.TypeClass;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.fon_classroommanagment.Models.Configuration.Constants.EMPLOYEE_TYPE_TABLE_NAME;
import static com.example.fon_classroommanagment.Models.Configuration.Constants.USER_ROLE_TABLE_NAME;

@Entity
@Table(name = EMPLOYEE_TYPE_TABLE_NAME)
@NoArgsConstructor
public class EmployeeType extends TypeClass {
    public EmployeeType(Long id, String name) {
        super(id, name);
    }


}
