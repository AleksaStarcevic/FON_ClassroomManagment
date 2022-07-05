package com.example.fon_classroommanagment.Models.appointment;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.appointment.DeleteAppointmentDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
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
        classes = TestDeleteAppointmentDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestDeleteAppointmentDTO extends ModelTest<DeleteAppointmentDTO> {


    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(DeleteAppointmentDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"id").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(DeleteAppointmentDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"id").isEmpty());

    }


    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new DeleteAppointmentDTO(UUID.randomUUID()))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new DeleteAppointmentDTO(null)
                )
              );
    }
}
