package com.example.fon_classroommanagment.Models.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

import static com.example.fon_classroommanagment.Configuration.Constants.MAX_VREME_ZAKAZIVANJA;
import static com.example.fon_classroommanagment.Configuration.Constants.MIN_VREME_ZAKAZIVANJA;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestIsClassroomAvailableForDateDTO {

    @NotNull(message = "Datum nije  unet")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Positive(message = "Ucionica nije dobro uneta")
    @NotNull(message = "Ucionica ne sme biti prazna")
    private Long classroomId;

    @Positive(message = "Pocetno vreme mora biti pozitivno")
    @Max(value = MAX_VREME_ZAKAZIVANJA,message = "Maksimalno se moze rezervisati do 20 h")
    @Min(value = MIN_VREME_ZAKAZIVANJA,message = "Minimalno se moze rezervisati od 8h")
    private int start_timeInHours;

    @Positive(message = "Krajnje vreme mora biti pozitivno")
    @Max(value = MAX_VREME_ZAKAZIVANJA,message = "Maksimalno se moze rezervisati do 20 h")
    @Min(value = MIN_VREME_ZAKAZIVANJA,message = "Minimalno se moze rezervisati od 8h")
    private int end_timeInHours;
}
