package com.example.fon_classroommanagment.Models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static com.example.fon_classroommanagment.Configuration.Constants.VALIDATION_TOKEN_ACCOUNT;

@Entity
@Table(name = VALIDATION_TOKEN_ACCOUNT)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationToken {

    private  static  final int EXPIRATION_TIME=60*10*1000;

    @Id
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate= new Date( Calendar.getInstance().getTimeInMillis() + (EXPIRATION_TIME));

    @Transient
    public boolean isExpired=expirationDate.before(new Date());
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private Account account;

    public ValidationToken(String token, Account account) {
        this.token = token;
        this.account = account;
    }
    //    public Date calculateExpirationEndDate(int minutes)
}
