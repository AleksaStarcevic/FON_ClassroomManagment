package com.example.fon_classroommanagment.Models;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import com.example.fon_classroommanagment.Models.DTO.ClassroomDetailsDTO;
import com.example.fon_classroommanagment.Models.DTO.ClassroomPagingDTO;
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
public class TestClassroomPagingDTO extends  ModelTest<ClassroomPagingDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(ClassroomPagingDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"name").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"number_of_seats").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(ClassroomPagingDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"name").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"number_of_seats").isEmpty());

    }

    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new ClassroomPagingDTO("TestName",
                        23,true,true))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new ClassroomPagingDTO(null,-4,false,false)
                ),
                Arguments.of(
                        new ClassroomPagingDTO("",-6,false,false)


                ));
    }
}
