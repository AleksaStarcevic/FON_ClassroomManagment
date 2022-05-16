package com.example.fon_classroommanagment;

import com.example.fon_classroommanagment.Configuration.SecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes={  SecurityConfiguration.class})
class FonClassroomManagmentApplicationTests {

    @Test
    void contextLoads() {
    }

}
