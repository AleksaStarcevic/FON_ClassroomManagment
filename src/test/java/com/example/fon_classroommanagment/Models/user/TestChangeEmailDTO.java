package com.example.fon_classroommanagment.Models.user;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.user.ChangeEmailDTO;
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
        classes = TestChangeEmailDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestChangeEmailDTO extends ModelTest<ChangeEmailDTO> {






    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new ChangeEmailDTO("test@gmail.com"
                )),
                Arguments.of(
                        new ChangeEmailDTO("test123@gmail.com")


        ));
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new ChangeEmailDTO("testgmail.com"
                )),
                Arguments.of(
                        new ChangeEmailDTO("test123gcom")


                ));
    }


    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(ChangeEmailDTO dto) {
        Assertions.assertTrue(validator.validateProperty(dto,"email").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(ChangeEmailDTO dto) {
        Assertions.assertFalse(validator.validateProperty(dto,"email").isEmpty());

    }


}
