package com.example.fon_classroommanagment.Models.classroom;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.classroom.RequestClassroomsDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
import com.example.fon_classroommanagment.Models.user.TestAccountDTO;
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
public class TestRequestClassroomsDTO extends ModelTest<RequestClassroomsDTO> {


    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(RequestClassroomsDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"page").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(RequestClassroomsDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"page").isEmpty());

    }
    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new RequestClassroomsDTO(2))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new RequestClassroomsDTO(-5))

        );
    }
}
