package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Configuration.UserProfileDetails;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.DTO.ChangeEmailDTO;
import com.example.fon_classroommanagment.Models.DTO.ChangePasswordDTO;
import com.example.fon_classroommanagment.Models.User.UserProfile;
import com.example.fon_classroommanagment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
   @Autowired
   private UserRepository userRepository;

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

    public void ChangePassword(ChangePasswordDTO password) throws UserExistsExcetion {
        UserProfile userProfile=findById(password.getId());
        userRepository.updatePhone(userProfile.getId(),encoder.encode(password.getPassword()));
    }

    public void changeEmail(ChangeEmailDTO dto) throws UserExistsExcetion {
        UserProfile userProfile=findById(dto.getId());
        userRepository.changeEmail(userProfile.getId(),userProfile.getEmail());
    }
}
