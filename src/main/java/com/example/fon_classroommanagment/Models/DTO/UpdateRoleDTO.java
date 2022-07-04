package com.example.fon_classroommanagment.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleDTO {
    @NotNull(message = "Id ne sme biti null")
    private UUID id;
    @NotNull(message = "Id uloge ne sme biti null")
    private Long id_role;
}
