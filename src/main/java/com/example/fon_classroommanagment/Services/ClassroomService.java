package com.example.fon_classroommanagment.Services;


import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.FilterDTO;
import com.example.fon_classroommanagment.Models.DTO.SearchClassroomDTO;
import com.example.fon_classroommanagment.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository repository;
    public List<Classroom> filter(FilterDTO filterDTO) {
        if(filterDTO.getType()==0 && !filterDTO.isSortByCapacity())
          return   repository.filter(filterDTO.getMin_capacity(),filterDTO.getMax_capacity());
        else if(!filterDTO.isSortByCapacity() && filterDTO.getType()!=0){
           return repository.filterWithType(filterDTO.getMin_capacity(),filterDTO.getMax_capacity(),filterDTO.getType());
        }
        else{
           return repository.filterAll(filterDTO.getMin_capacity(),filterDTO.getMax_capacity(),filterDTO.getType());
        }
    }

    public List<Classroom> searchClassroom(SearchClassroomDTO dto) throws ClassroomExistsException {
        List<Classroom> classrooms = repository.findByName(dto.getName());
        if(classrooms.isEmpty()) throw new ClassroomExistsException("Classroom with that name doesn't exist");
        return classrooms;

    }
}
