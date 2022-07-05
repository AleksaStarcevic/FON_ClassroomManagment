package com.example.fon_classroommanagment.Models.classroom;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.classroom.ClassroomCardDTO;
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
public class TestClassroomCardDTO extends ModelTest<ClassroomCardDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(ClassroomCardDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"name").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"number_of_seats").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(ClassroomCardDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"name").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"number_of_seats").isEmpty());

    }

    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new ClassroomCardDTO(1L,"TestName",
                        23,true,true))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new ClassroomCardDTO(null,null,-4,false,false)
                ),
                Arguments.of(
                        new ClassroomCardDTO(null,"",-6,false,false)


                ));
    }
}
