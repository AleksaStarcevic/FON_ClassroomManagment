package com.example.fon_classroommanagment.Models.Classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import static com.example.fon_classroommanagment.Configuration.Constants.CLASSROOM_TABLE_NAME;

@Entity
@Table(name = CLASSROOM_TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String name;

    @Column(columnDefinition = "INT(11) UNSIGNED",nullable = false)
    private int number_of_seats;

    @Column(columnDefinition = "INT(11) UNSIGNED",nullable = false)
    private int number_of_computers;

    @Column(columnDefinition = "TINYINT(1)",nullable = false)

    private boolean aircondition;
    @Column(columnDefinition = "TINYINT(1)",nullable = false)
    private boolean projector;

    @Positive(message = "Povrsina mora biti pozitivan broj")
    private int povrsina;

    @Positive(message = "Sprat mora biti pozitivan broj")
    private int sprat;

    @Positive(message = "Broj tabli mora biti pozitivan broj")
    private int br_tabli;

    @ManyToOne( optional = false)
    private ClassroomType type;

    public Classroom(long classroomId) {
        setId(classroomId);
    }
}
