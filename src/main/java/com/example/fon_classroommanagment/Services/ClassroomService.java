package com.example.fon_classroommanagment.Services;


import com.example.fon_classroommanagment.Exceptions.ClassroomExistsException;
import com.example.fon_classroommanagment.Models.Classroom.Classroom;
import com.example.fon_classroommanagment.Models.DTO.ClassroomDetailsDTO;
import com.example.fon_classroommanagment.Models.DTO.FilterDTO;
import com.example.fon_classroommanagment.Models.DTO.SearchClassroomDTO;
import com.example.fon_classroommanagment.Repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.fon_classroommanagment.Configuration.Constants.PAGE_SIZE;

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

    public List<Classroom> searchClassroom(SearchClassroomDTO dto)  {
        //if(classrooms.isEmpty()) throw new ClassroomExistsException("Classroom with that name doesn't exist");
        return repository.findByNameContaining(dto.getName());

    }

    public Classroom classroomDetails(ClassroomDetailsDTO dto) throws ClassroomExistsException {
       Optional<Classroom> classroom = repository.findById(dto.getId());
       if(!classroom.isPresent()) throw new ClassroomExistsException("Classroom with given id doesn't exist");
       return classroom.get();
    }


    public List<Classroom> getAllClassrooms(int page) {



        Page<Classroom> all = repository.findAll(PageRequest.of(page, PAGE_SIZE));
        return all.getContent();
    }
}
