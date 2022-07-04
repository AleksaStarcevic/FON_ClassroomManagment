package com.example.fon_classroommanagment.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Genericka klasa koju nasledjuju klase AppointmentStatus,AppointmentType,ClassroomType...
 * @author Aleksa Starcevic
 * @version 1.0
 *
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeClass {

    /**
     * Identifikator tipa Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ime tipa string
     */
    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String name;
}
