package com.example.fon_classroommanagment.Repository;

import com.example.fon_classroommanagment.Models.User.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<UserProfile, UUID> {
    UserProfile findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update UserProfile u set u.password = :password where u.id = :id")
    void updatePhone(@Param(value = "id") UUID id, @Param(value = "password") String password);
}
