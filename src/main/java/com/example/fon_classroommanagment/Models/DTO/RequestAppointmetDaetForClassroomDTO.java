package com.example.fon_classroommanagment.Models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@AllArgsConstructor
public class RequestAppointmetDaetForClassroomDTO {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message="Id ucionice ne sme biti null")
    @Positive(message="Id ucionice mora biti pozitivan")
    private Long classroomId;
}
