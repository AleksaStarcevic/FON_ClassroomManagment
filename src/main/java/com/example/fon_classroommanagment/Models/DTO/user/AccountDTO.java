package com.example.fon_classroommanagment.Models.DTO.user;

import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import com.example.fon_classroommanagment.Models.User.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {



    @NotNull(message = "firstName ne sme biti null")
    @Size(max = 20,message = "Ime moze imati najvise 20 karaktera")
    private String firstName;

    @NotNull(message = "Prezime ne sme biti null")
    @Size(max = 20,message = "Prezime moze imate najvise 20 karaktera")
    private String lastName;

    @NotNull(message = "department ne sme biti null")
    private EmployeeDepartment department;

    @NotNull(message = "title ne sme biti null")
    private EducationTitle title;

    @NotNull(message = "type ne sme biti null")
    private EmployeeType type;

    @NotNull(message = "email ne sme biti null")
    @Email(message = "email nije u dobrom formatu")
    private String email;

    @NotNull(message = "password ne sme biti null")
    @Size(min = 4,message = "Password mora imate bar 4 slova")
    private String password;

    private byte[] image;

    private Date timeStamp=new Date();

    private String token;



    public AccountDTO( String firstName, String lastName, EmployeeDepartment department, EducationTitle title, EmployeeType type, String email, String password,byte[] image) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.title = title;
        this.type = type;
        this.email = email;
        this.password = password;
        this.image=image;
    }
}
