package com.example.fon_classroommanagment.Models;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.Appointment.AppointmentStatus;
import com.example.fon_classroommanagment.Models.DTO.ChangeStatusAppointmentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestAccountDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestConfirmAppointmentDTO extends  ModelTest<ChangeStatusAppointmentDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(ChangeStatusAppointmentDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"id").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"status").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(ChangeStatusAppointmentDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"id").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"status").isEmpty());

    }
    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new ChangeStatusAppointmentDTO(UUID.randomUUID(),new AppointmentStatus(1L,"Test")))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new ChangeStatusAppointmentDTO(null,null)
                )
             );
    }
}
