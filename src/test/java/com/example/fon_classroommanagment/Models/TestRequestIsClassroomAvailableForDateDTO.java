package com.example.fon_classroommanagment.Models;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.RequestClassroomDetailsDTO;
import com.example.fon_classroommanagment.Models.DTO.RequestIsClassroomAvailableForDateDTO;
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
public class TestRequestIsClassroomAvailableForDateDTO extends  ModelTest<RequestIsClassroomAvailableForDateDTO>{
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(RequestIsClassroomAvailableForDateDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"date").isEmpty());

        Assertions.assertTrue(validator.validateProperty(entity,"classroomId").isEmpty());


    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(RequestIsClassroomAvailableForDateDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"date").isEmpty());

        Assertions.assertFalse(validator.validateProperty(entity,"classroomId").isEmpty());
    }
    private static Stream<Arguments> generateValid(){
        return  Stream.of(
//                Arguments.of(new RequestIsClassroomAvailableForDateDTO(new Date(),2L))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
//                Arguments.of(new RequestIsClassroomAvailableForDateDTO(null,-2L)),
//
//                Arguments.of(new RequestIsClassroomAvailableForDateDTO(null,0L))

        );
    }
}
