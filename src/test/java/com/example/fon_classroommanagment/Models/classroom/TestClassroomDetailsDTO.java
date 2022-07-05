package com.example.fon_classroommanagment.Models.classroom;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.Classroom.ClassroomType;
import com.example.fon_classroommanagment.Models.DTO.classroom.ClassroomDetailsDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestClassroomDetailsDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestClassroomDetailsDTO extends ModelTest<ClassroomDetailsDTO> {


    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(ClassroomDetailsDTO entity) {



            Assertions.assertTrue(super.validator.validateProperty(entity,"name").isEmpty());

            Assertions.assertTrue(super.validator.validateProperty(entity,"number_of_seats").isEmpty());

            Assertions.assertTrue(super.validator.validateProperty(entity,"number_of_computers").isEmpty());
            Assertions.assertTrue(super.validator.validateProperty(entity,"type").isEmpty());
            Assertions.assertTrue(super.validator.validateProperty(entity,"povrsina").isEmpty());
            Assertions.assertTrue(super.validator.validateProperty(entity,"sprat").isEmpty());
            Assertions.assertTrue(super.validator.validateProperty(entity,"br_tabli").isEmpty());
           Assertions.assertTrue(super.validator.validateProperty(entity,"monthPercntage").isEmpty());


    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(ClassroomDetailsDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"name").isEmpty());

        Assertions.assertFalse(validator.validateProperty(entity,"number_of_seats").isEmpty());

        Assertions.assertFalse(validator.validateProperty(entity,"number_of_computers").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"type").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"povrsina").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"sprat").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"br_tabli").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"monthPercntage").isEmpty());

    }
    private static Stream<Arguments> generateValid(){
        List<Double> arr =  Arrays.asList(0.5,0.6);

        return  Stream.of(
                Arguments.of(new ClassroomDetailsDTO("Testname",
                        23,
                        23,
                        true,
                        true,
                        new ClassroomType(1L, "TestName"), 30, 1, 2, arr))
               );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new ClassroomDetailsDTO(null,
                        -4,
                        -4,
                        true,
                        true,
                        null,
                        -20,
                        -1,
                        -2,
                        null)
                ),
                Arguments.of(new ClassroomDetailsDTO("",
                                -5,
                                -5,
                                false,
                                false,
                                null,
                                -20,
                                -1,
                                -1,
                                null)


                ));
    }
}
