package com.example.fon_classroommanagment.Models;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.SearchReservationDTO;
import com.example.fon_classroommanagment.Models.DTO.UserDetailsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestAccountDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestUserDetailsDTO extends  ModelTest<UserDetailsDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(UserDetailsDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"firstName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"lastName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"typeName").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(UserDetailsDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"firstName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"lastName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"typeName").isEmpty());

    }
    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new UserDetailsDTO("Test","Test","Test",new byte[100])),
                Arguments.of(new UserDetailsDTO("Test","Test","Test",null))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new UserDetailsDTO("","","",new byte[100])),
                Arguments.of(new UserDetailsDTO(null,null,null,null))



                );
    }
}
