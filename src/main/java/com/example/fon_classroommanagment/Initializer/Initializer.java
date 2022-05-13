package com.example.fon_classroommanagment.Initializer;

import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import com.example.fon_classroommanagment.Models.User.UserProfile;
import com.example.fon_classroommanagment.Models.User.UserRole;
import com.example.fon_classroommanagment.Repository.UserRepository;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Initializer implements SmartInitializingSingleton {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void afterSingletonsInstantiated() {

//        UserProfile ilija=new UserProfile(UUID.randomUUID(),
//                "test@gmail.com"
//                ,encoder.encode("1234"),
//                new UserRole(1L,"USER"),
//                new Employee(1L,"ilija","radojkovic",new EmployeeDepartment(1L,"TESt"),new EducationTitle(1L,"TEST"),new EmployeeType(1L,"TEST")));
//userRepository.save(ilija);
    }
}
