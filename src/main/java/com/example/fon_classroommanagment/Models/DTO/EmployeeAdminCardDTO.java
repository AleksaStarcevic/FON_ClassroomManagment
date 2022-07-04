package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class EmployeeAdminCardDTO {
            private UUID id;
            private String  firstName;
            private String  lastName;
            private String permissionTitle;

}
