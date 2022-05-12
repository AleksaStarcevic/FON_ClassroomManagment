package com.example.fon_classroommanagment;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class TypeClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(45)",nullable = false)
    private String name;
}
