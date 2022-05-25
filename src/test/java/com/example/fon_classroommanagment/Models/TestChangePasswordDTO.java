package com.example.fon_classroommanagment.Models;


import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.ChangeEmailDTO;
import com.example.fon_classroommanagment.Models.DTO.ChangePasswordDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestAccountDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestChangePasswordDTO extends  ModelTest<ChangePasswordDTO> {



    @ParameterizedTest
    @MethodSource("generateValid")
    @Override
    public void TestValid(ChangePasswordDTO dto){
        Assertions.assertTrue(validator.validateProperty(dto,"password").isEmpty());


    }
    @ParameterizedTest
    @MethodSource("generateInvalid")
    @Override
    public void TestInvalid(ChangePasswordDTO dto){
        Assertions.assertFalse(validator.validateProperty(dto,"password").isEmpty());



    }

    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new ChangePasswordDTO("1234")),
                Arguments.of(
                        new ChangePasswordDTO("etestes2")


                ));
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new ChangePasswordDTO("123",null)
                ),
                Arguments.of(new ChangePasswordDTO("123",null)
                ),
                Arguments.of(
                        new ChangePasswordDTO()


                ));
    }
}
