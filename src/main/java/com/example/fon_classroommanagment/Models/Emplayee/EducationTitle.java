package com.example.fon_classroommanagment.Models.Emplayee;

import com.example.fon_classroommanagment.Models.TypeClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.fon_classroommanagment.Models.Configuration.Constants.EDUCATION_TITLE_TABLE_NAME;

@Entity
@Table(name = EDUCATION_TITLE_TABLE_NAME)
@NoArgsConstructor
public class EducationTitle extends TypeClass {
    public EducationTitle(Long id, String name) {
        super(id, name);
    }
}
