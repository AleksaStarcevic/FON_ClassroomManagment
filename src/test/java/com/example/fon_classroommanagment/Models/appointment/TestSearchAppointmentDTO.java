package com.example.fon_classroommanagment.Models.appointment;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.appointment.SearchAppointmentDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
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
        classes = TestSearchAppointmentDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestSearchAppointmentDTO extends ModelTest<SearchAppointmentDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(SearchAppointmentDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"classroomId").isEmpty());

        Assertions.assertTrue(validator.validateProperty(entity,"date").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"start_timeInHours").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(SearchAppointmentDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"classroomId").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"date").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"start_timeInHours").isEmpty());

    }
    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new SearchAppointmentDTO(new Date(),2L,8))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new SearchAppointmentDTO(null,-2L,-2)),
                Arguments.of(new SearchAppointmentDTO(null,null,11))



        );
    }
}
