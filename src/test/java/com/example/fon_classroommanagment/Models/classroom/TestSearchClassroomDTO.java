package com.example.fon_classroommanagment.Models.classroom;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.classroom.SearchClassroomDTO;
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
public class TestSearchClassroomDTO extends ModelTest<SearchClassroomDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(SearchClassroomDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"page").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"name").isEmpty());

    }

    @Override
@ParameterizedTest
    @MethodSource("generateIValid")
    protected void TestInvalid(SearchClassroomDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"page").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"name").isEmpty());

    }

    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new SearchClassroomDTO("Test",3))
        );
    }
    private static Stream<Arguments> generateIValid(){
        return  Stream.of(
                Arguments.of(new SearchClassroomDTO(null,-3))
        );
    }


}
