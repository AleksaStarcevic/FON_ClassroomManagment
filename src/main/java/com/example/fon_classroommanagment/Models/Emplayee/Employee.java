package com.example.fon_classroommanagment.Models.Emplayee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.fon_classroommanagment.Configuration.Constants.EMPLOYEE_TABLE_NAME;

@Entity
@Table(name = EMPLOYEE_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String firstName;
    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String lastName;

    //dodati su cascade da bi lakse testirali
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private  EmployeeDepartment department;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private  EducationTitle title;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private  EmployeeType type;

}
