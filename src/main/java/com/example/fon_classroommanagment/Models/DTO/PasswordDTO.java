package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {

    @NotNull(message = "password ne sme biti null")
    @Size(min = 4,message = "Password mora imate bar 4 slova")
    private String password;

    private Date timeStamp=new Date();
}
