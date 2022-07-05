package com.example.fon_classroommanagment.Models.appointment;


import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.appointment.UpdateAppointmentDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;



@SpringBootTest
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestUpdateAppointmentDTO extends ModelTest<UpdateAppointmentDTO> {

    private static Stream<Arguments> generateValid(){
        return Stream.of(
                Arguments.of(new UpdateAppointmentDTO(UUID.randomUUID(),
                        1L,
                        "Ispit",
                        new Date(),
                        "Ispit iz njt",
                        "test",
                        60,
                        1,
                        3,
                        1L)));

    }

    private static Stream<Arguments> generateInvalid(){
        return Stream.of(
                Arguments.of(new UpdateAppointmentDTO(null,
                        -2L,
                        null,
                        null,
                        "",
                        "",
                        -12,
                        15,
                        17,
                        -1L)),
                Arguments.of(new UpdateAppointmentDTO(null,
                        -1L,
                        "Those two men, the assassins. I believe I was their prime target, but that somehow they were steered away from me to my brother-in-law. Because of this 'intervention' I am alive",
                        null,
                        null,
                        null,
                        -7,
                        -5,
                        -3,
                        -1L)),
                Arguments.of(new UpdateAppointmentDTO(null,
                        -1L,
                        "Those two men, the assassins. I believe I was their prime target, but that somehow they were steered away from me to my brother-in-law. Because of this 'intervention' I am alive",
                        null,
                        "Those two men, the assassins. I believe I was their prime target, but that somehow they were steered away from me to my brother-in-law. Because of this 'intervention' I am alive",
                        "Those two men, the assassins. I believe I was their prime target, but that somehow they were steered away from me to my brother-in-law. Because of this 'intervention' I am alive",
                        -7,
                        12,
                        -3,
                        -1L))




                );


    }





    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(UpdateAppointmentDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"id").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"classroomId").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"name").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"date").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"decription").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"reason").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"number_of_attendies").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"start_timeInHours").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"end_timeInHours").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"type").isEmpty());


    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(UpdateAppointmentDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"id").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"classroomId").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"name").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"date").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"decription").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"reason").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"number_of_attendies").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"start_timeInHours").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"end_timeInHours").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"type").isEmpty());


    }
}
