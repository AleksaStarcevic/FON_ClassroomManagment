package com.example.fon_classroommanagment.Models;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.ReserveDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.stream.Stream;


@SpringBootTest
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
class TestReserveDTO extends ModelTest<ReserveDTO> {

    private static Stream<Arguments> generateValid(){
        return Stream.of(
                Arguments.of(new ReserveDTO("ale@gmail.com",
                        1L,
                        "Ispit",
                        new Date(),
                        "Ispit iz njt",
                        "ispit",
                        50,
                        1,
                        3,
                        3,
                        2)));

    }

    private static Stream<Arguments> generateInvalid(){
        return Stream.of(
                Arguments.of(new ReserveDTO("aaaagmail.com",
                        -1L,
                        null,
                        null,
                        "",
                        "",
                        -2,
                        22,
                        23,
                        1,
                        -2)),
                Arguments.of(new ReserveDTO("",
                        -1L,
                        null,
                        null,
                        "I continue cooking, you and I both forget about Pinkman. We forget this ever happened. We consider this a lone hiccup in an otherwise long and fruitful business arrangement. I prefer ",
                        null,
                        -2,
                        -5,
                        -7,
                        -1,
                        -2)),
                Arguments.of(new ReserveDTO(null,
                        -1L,
                        "I continue cooking, you and I both forget about Pinkman. We forget this ever happened. We consider this a lone hiccup in an otherwise long and fruitful business arrangement. I prefer",
                        null,
                        null,
                        "I continue cooking, you and I both forget about Pinkman. We forget this ever happened. We consider this a lone hiccup in an otherwise long and fruitful business arrangement. I prefer ",
                        -2,
                        -2,
                        21,
                        2,
                        -2)));

    }





    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(ReserveDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"email").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"classroomId").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"name").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"date").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"decription").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"reason").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"number_of_attendies").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"start_timeInHours").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"end_timeInHours").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"status").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"type").isEmpty());
    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(ReserveDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"email").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"classroomId").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"name").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"date").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"decription").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"reason").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"number_of_attendies").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"start_timeInHours").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"end_timeInHours").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"status").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"type").isEmpty());
    }
}