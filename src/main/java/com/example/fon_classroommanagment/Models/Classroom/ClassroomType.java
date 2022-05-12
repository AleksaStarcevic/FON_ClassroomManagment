package com.example.fon_classroommanagment.Models.Classroom;

import com.example.fon_classroommanagment.Models.TypeClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.fon_classroommanagment.Models.Configuration.Constants.CLASSROOM_TYPE_TABLE_NAME;


@NoArgsConstructor
@Entity
@Table(name = CLASSROOM_TYPE_TABLE_NAME)
public class ClassroomType  extends TypeClass {
    public ClassroomType(Long id, String name) {
        super(id, name);
    }
}
