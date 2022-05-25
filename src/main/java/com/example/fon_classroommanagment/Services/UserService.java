package com.example.fon_classroommanagment.Services;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.fon_classroommanagment.Configuration.UserProfileDetails;
import com.example.fon_classroommanagment.Exceptions.AppointmentsForUserException;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.Appointment.Appointment;
import com.example.fon_classroommanagment.Models.DTO.*;
import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import com.example.fon_classroommanagment.Models.User.UserProfile;
import com.example.fon_classroommanagment.Repository.AppointmentRepository;
import com.example.fon_classroommanagment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private AppointmentRepository appointmentRepository;

@Autowired
private BCryptPasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user=findByEmail(username);
        if(user==null) throw  new UsernameNotFoundException("Please register,user does not exit");
        return new UserProfileDetails(user);
    }

    public UserProfile findByEmail(String email){
       return userRepository.findByEmail(email);
    }
    //save vraca uvek ne null ker radi update ili insert
    public boolean saveUser(UserProfile user){
        if(user==null) return false;
        userRepository.save(user);
        return true;

    }

    public UserProfile findById(UUID id) throws UserExistsExcetion {
        Optional<UserProfile> profile = userRepository.findById(id);
        if(profile.isEmpty()) throw new  UserExistsExcetion("User does not exists");
        return profile.get();
    }

    public void ChangePassword(ChangePasswordDTO password,String email) throws TokenExpiredException {
        UserProfile userProfile=findByEmail(email);
        if(userProfile==null) throw new TokenExpiredException("Please login again,there is no user with given email");
        userRepository.updatePhone(userProfile.getId(),encoder.encode(password.getPassword()));
    }

    public void changeEmail(String email,ChangeEmailDTO dto) throws  TokenExpiredException {
        UserProfile userProfile=findByEmail(email);
        if(userProfile==null) throw new TokenExpiredException("Please login again,there is no user with given email");
        userRepository.changeEmail(userProfile.getId(),dto.getEmail());
    }

    public UserDetailsDTO getUserDetails(String email) throws  TokenExpiredException{
        UserProfile user=userRepository.findByEmail(email);
        if(user==null) throw new TokenExpiredException("Please login again,there is no user with given email");
        Employee employee=user.getEmployee();
        return  new UserDetailsDTO(employee.getFirstName(),employee.getLastName(),employee.getType().getName(),employee.getFile());


    }

    public List<AppointmentsForUserDTO> getAppointmentsForUser(String email) throws AppointmentsForUserException {

        UserProfile user = userRepository.findByEmail(email);
        if(user==null) throw new TokenExpiredException("Please login again,there is no user with given email");

        Employee employee = user.getEmployee();

        List<Appointment> appointments = appointmentRepository.findByEmployeeId(employee.getId());

        if (appointments.isEmpty()) throw new AppointmentsForUserException("No reservations for this user");

        List<AppointmentsForUserDTO> appointmentsForUserDTOS = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentsForUserDTOS.add(new AppointmentsForUserDTO(
                    appointment.getName(),
                    appointment.getClassroom().getName(),
                    appointment.getDate(),
                    appointment.getStart_timeInHours(),
                    appointment.getEnd_timeInHours()));

        }
        return appointmentsForUserDTOS;

    }

    public List<RequestedAppointmentsDTO> getRequestedAppointments() {
        return appointmentRepository.getRequestedAppointmentsForUsers();
    }
}
