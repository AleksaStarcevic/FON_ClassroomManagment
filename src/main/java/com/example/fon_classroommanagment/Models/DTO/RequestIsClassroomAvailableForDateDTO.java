package com.example.fon_classroommanagment.Models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestIsClassroomAvailableForDateDTO {

    @NotNull(message = "Datum nije  uneto")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Positive(message = "Ucionica nije dobro uneta")
    private Long classroomId;
}
