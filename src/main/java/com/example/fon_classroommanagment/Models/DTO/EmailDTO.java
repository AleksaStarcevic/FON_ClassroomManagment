package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    @NotNull(message = "email ne sme biti null")
    @Email(message = "email nije u dobrom formatu")
    private String email;

    private Date timeStamp=new Date();
}
