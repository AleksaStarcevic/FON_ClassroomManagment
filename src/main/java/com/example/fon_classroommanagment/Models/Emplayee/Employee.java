package com.example.fon_classroommanagment.Models.Emplayee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

import static com.example.fon_classroommanagment.Configuration.Constants.EMPLOYEE_TABLE_NAME;


/**
 * Klasa predstavlja zaposlenog koji rezervise termine
 * @author Aleksa Starcevic
 * @version 1.0
 *
 */
@Entity
@Table(name = EMPLOYEE_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    /**
     * Identifikator zaposlenog tipa Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    /**
     * Ime zaposlenog tipa String
     */
    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String firstName;
    /**
     * Prezime zaposlenog tipa String
     */
    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String lastName;

    /**
     * Katedra zaposlenog tipa EmployeeDepartment
     */
    @ManyToOne( optional = false)
    private  EmployeeDepartment department;
    /**
     * Obrazovanje zaposlenog tipa EducationTitle
     */
    @ManyToOne( optional = false)
    private  EducationTitle title;
    /**
     * Tip zaposlenog tipa EmployeeType
     */
    @ManyToOne( optional = false)
    private  EmployeeType type;
    /**
     * Mejl zaposlenog tipa String
     */
    @Email(message = "Email mora biti u dobrom formatu")
    @Column(nullable = false,columnDefinition = "VARCHAR(70)")
    private String email;
    /**
     * Slika zaposlenog tipa byte[]
     */
//    @Lob
   // @Column(name = "image", columnDefinition = "LONGBLOB")
    private String image;


    /**
     * Parametarski konstruktor postavlja atribute na unete vrednosti
     * @param firstName ime zaposlenog
     * @param lastName prezime zaposlenog
     * @param department katedra zaposlenog
     * @param title obrazovanje zaposlenog
     * @param type tip zaposlenog
     * @param email email zaposlenog
     * @param image slika zaposlenog
     */
    public Employee(String firstName, String lastName, EmployeeDepartment department, EducationTitle title, EmployeeType type,String email,String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.title = title;
        this.type = type;
        this.email=email;
        this.image=image;
    }



}
