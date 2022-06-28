package com.example.fon_classroommanagment.Models;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.GetForDateAppointmentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Stream;

@SpringBootTest(
        classes = TestAccountDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestGetForDateAppointmentDTO extends  ModelTest<GetForDateAppointmentDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(GetForDateAppointmentDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"Start_timeInHours").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"End_timeInHours").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"typeName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"classroomName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"decription").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(GetForDateAppointmentDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"Start_timeInHours").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"End_timeInHours").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"typeName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"classroomName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"decription").isEmpty());
    }
    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new GetForDateAppointmentDTO(10,14,"Test","Test","Test"))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new GetForDateAppointmentDTO(-1,-2,"","",""))
,
                Arguments.of(new GetForDateAppointmentDTO(-1,-2,null,null,null))
        );
    }
}
