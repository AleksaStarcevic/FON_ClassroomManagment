package com.example.fon_classroommanagment.Models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.example.fon_classroommanagment.Configuration.Constants.EXPIRATION_TIME;
import static com.example.fon_classroommanagment.Configuration.Constants.VALIDATION_TOKEN_ACCOUNT;

@Entity
@Table(name = VALIDATION_TOKEN_ACCOUNT)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationToken {



    @Id
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate= new Date( Calendar.getInstance().getTimeInMillis() + (EXPIRATION_TIME));


    public boolean isExpired(){
        return new Date().getTime()>expirationDate.getTime();
    }
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Account account;

    public ValidationToken(String token, Account account) {
        this.token = token;
        this.account = account;
    }
    //    public Date calculateExpirationEndDate(int minutes)
}
