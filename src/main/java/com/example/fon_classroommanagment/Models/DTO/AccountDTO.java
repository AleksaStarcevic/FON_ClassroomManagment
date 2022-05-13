package com.example.fon_classroommanagment.Models.DTO;

import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import com.example.fon_classroommanagment.Models.User.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountDTO {

    private UUID id;

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

    private Date timeStamp=new Date();

    private String token;

    public Account CreateAccount() {
       return  new Account(getEmail(),
                getFirstName(),
                getLastName(),
                getDepartment(),
                getTitle(),
                getType(),
              getPassword()
        );
    }
}
