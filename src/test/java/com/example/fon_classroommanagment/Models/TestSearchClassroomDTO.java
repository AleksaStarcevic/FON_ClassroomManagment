package com.example.fon_classroommanagment.Models;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.RequestIsClassroomAvailableForDateDTO;
import com.example.fon_classroommanagment.Models.DTO.SearchClassroomDTO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestAccountDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestSearchClassroomDTO extends  ModelTest<SearchClassroomDTO>{
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(SearchClassroomDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"name").isEmpty());

    }

    @Override

    protected void TestInvalid(SearchClassroomDTO entity) {

    }

    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new SearchClassroomDTO("Test"))
        );
    }


}
