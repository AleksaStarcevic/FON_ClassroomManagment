package com.example.fon_classroommanagment.Repository;

import com.example.fon_classroommanagment.Models.User.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<UserProfile, UUID> {
    UserProfile findByEmail(String email);

}
