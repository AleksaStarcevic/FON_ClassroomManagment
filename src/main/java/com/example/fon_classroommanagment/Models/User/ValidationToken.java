package com.example.fon_classroommanagment.Models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

import static com.example.fon_classroommanagment.Configuration.Constants.VALIDATION_TOKEN_ACCOUNT;

@Entity
@Table(name = VALIDATION_TOKEN_ACCOUNT)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationToken {

    private  static  final int EXPIRATION_TIME=60*10;
    @Id
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    private String email;

//    public Date calculateExpirationEndDate(int minutes)
}
