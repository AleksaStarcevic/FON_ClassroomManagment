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
    @ManyToOne( optional = false)
    private  EmployeeDepartment department;

    @ManyToOne( optional = false)
    private  EducationTitle title;

    @ManyToOne( optional = false)
    private  EmployeeType type;

    public Employee(String firstName, String lastName, EmployeeDepartment department, EducationTitle title, EmployeeType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.title = title;
        this.type = type;
    }
}
