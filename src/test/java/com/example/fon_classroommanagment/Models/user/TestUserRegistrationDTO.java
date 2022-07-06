package com.example.fon_classroommanagment.Models.user;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.user.UserRegistrationDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Stream;

@SpringBootTest(
        classes = TestUserRegistrationDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestUserRegistrationDTO extends ModelTest<UserRegistrationDTO> {






    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new UserRegistrationDTO("test@gmail.com","1234","test","test",null
                )
          ));
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new UserRegistrationDTO(null,null,null,null,null
                )),
                Arguments.of(
                        new UserRegistrationDTO("gewrwe","123","121r5312rf3223rf312f314124","gewf1231f2432tg43reyhg4q2h31f32f234231 t42etg",null)


                ));
    }


    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(UserRegistrationDTO dto) {
        Assertions.assertTrue(validator.validateProperty(dto,"email").isEmpty());
        Assertions.assertTrue(validator.validateProperty(dto,"password").isEmpty());
        Assertions.assertTrue(validator.validateProperty(dto,"firstName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(dto,"lastName").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(UserRegistrationDTO dto) {
        Assertions.assertFalse(validator.validateProperty(dto,"email").isEmpty());
        Assertions.assertFalse(validator.validateProperty(dto,"password").isEmpty());
        Assertions.assertFalse(validator.validateProperty(dto,"firstName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(dto,"lastName").isEmpty());
    }


}
