package com.example.fon_classroommanagment.Models.DTO;

import com.example.fon_classroommanagment.Anotations.CheckValues;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

import static com.example.fon_classroommanagment.Configuration.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CheckValues(message = "Krajnje vreme mora biti vece od pocetnog vremena")
public class ReserveDTO {



    @NotEmpty(message = "Email ne sme biti prazan")
    @Email(message = "Email nije u dobrom formatu")
    @NotNull(message = "Email ne sme biti prazan")
    private String email;




    @Positive(message = "Ucionica nije dobro uneta")
    private Long classroomId;



    @Length(max = 45,message = "Maksimalna duzina opisa je 45 slova")
    @NotNull(message = "Ime nije  uneto")
    @NotEmpty(message = "Ime ne sme biti prazno")
    private String name;

    @NotNull(message = "Datum nije  uneto")
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
    @Max(value = MAX_VREME_ZAKAZIVANJA,message = "Maksimalno se moze rezervisati do 20 h")
    @Min(value = MIN_VREME_ZAKAZIVANJA,message = "Minimalno se moze rezervisati od 8h")
    private int start_timeInHours;

    @Positive(message = "Krajnje vreme mora biti pozitivno")
    @Max(value = MAX_VREME_ZAKAZIVANJA,message = "Maksimalno se moze rezervisati do 20 h")
    @Min(value = MIN_VREME_ZAKAZIVANJA,message = "Minimalno se moze rezervisati od 8h")
    private int end_timeInHours;

 // @Positive(message = "Status mora biti pozitivan broj")
  @JsonIgnore
    @Min(value = 3)
    @Max(value = 3)
    @NotNull()
    private long status= STATUS_PENDING;


    @Positive(message = "Type mora biti pozitivan broj")
    private int type;


}
