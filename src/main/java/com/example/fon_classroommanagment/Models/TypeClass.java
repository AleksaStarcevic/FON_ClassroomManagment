package com.example.fon_classroommanagment.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String name;
}
