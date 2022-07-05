package com.example.fon_classroommanagment.Models.DTO.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppointmentDTO {

    @NotNull(message = "ID nije unet")
    private UUID id;

    @Positive(message = "Ucionica nije dobro uneta")
    private Long classroomId;


    @Length(max = 45,message = "Maksimalna duzina opisa je 45 slova")
    @NotNull(message = "Ime nije  uneto")
    private String name;

    @NotNull(message = "Datum nije unet")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;


    @Length(max = 45,message = "Maksimalna duzina opisa je 45 slova")
    @NotEmpty(message = "Opis ne sme biti prazan")
    @NotNull(message = "Opis nije  uneto")
    private  String decription;

    @Length(max = 45,message = "Maksimalna duzina razloga je 45 slova")
    @NotEmpty(message = "Razlog ne sme biti prazan")
    @NotNull(message = "Razlog nije  uneto")
    private String reason;


    @Positive(message = "Broj prisutnih mora biti pozitivan broj")
    private int number_of_attendies;


    @Positive(message = "Pocetno vreme mora biti pozitivno")
    @Max(value = 10,message = "Maksimalno se moze rezervisati 10 h")
    private int start_timeInHours;

    @Positive(message = "Krajnje vreme mora biti pozitivno")
    @Max(value = 10,message = "Maksimalno se moze rezervisati 10 h")
    private int end_timeInHours;


    @Positive(message = "Type mora biti pozitivan broj")
    private Long type;
}
