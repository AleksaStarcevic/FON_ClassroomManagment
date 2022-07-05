package com.example.fon_classroommanagment.Models.appointment;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.appointment.AppointmentRequestedUserDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestAppointmentRequestedUserDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestAppointmentRequestedUserDTO extends ModelTest<AppointmentRequestedUserDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(AppointmentRequestedUserDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"id").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"classroomName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"title").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"date").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"startTime").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"endTime").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(AppointmentRequestedUserDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"id").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"classroomName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"title").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"date").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"startTime").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"endTime").isEmpty());

    }
    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new AppointmentRequestedUserDTO(UUID.randomUUID(),"c001","fewfw",new Date(),12,14))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new AppointmentRequestedUserDTO(null,null,null,null,-1,-2))

             );
    }
}
