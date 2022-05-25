package com.example.fon_classroommanagment.Models;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.ChangeEmailDTO;
import com.example.fon_classroommanagment.Models.DTO.FilterDTO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestAccountDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestChangeEmailDTO extends  ModelTest {



    @ParameterizedTest
    @MethodSource("generateValid")
    public void Test_Valid(ChangeEmailDTO dto){
        Assertions.assertTrue(validator.validateProperty(dto,"email").isEmpty());

    }
    @ParameterizedTest
    @MethodSource("generateInvalid")
    public void Test_Invalid(ChangeEmailDTO dto){

    }

    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new ChangeEmailDTO(UUID.randomUUID(),"test@gmail.com"
                )),
                Arguments.of(
                        new ChangeEmailDTO(UUID.randomUUID(),"test123@gmail.com")


        ));
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new ChangeEmailDTO(UUID.randomUUID(),"testgmail.com"
                )),
                Arguments.of(
                        new ChangeEmailDTO(UUID.randomUUID(),"test123gcom")


                ));
    }


    @Override
    protected void TestValid(Object entity) {

    }

    @Override
    protected void TestInvalid(Object entity) {

    }
}
