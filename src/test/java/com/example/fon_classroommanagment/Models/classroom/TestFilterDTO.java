package com.example.fon_classroommanagment.Models.classroom;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.classroom.FilterDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.LinkedList;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestFilterDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestFilterDTO extends ModelTest<FilterDTO> {



    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new FilterDTO(10,20, new LinkedList<>(),true,true,true
                      )),
                Arguments.of(
                        new FilterDTO(
                             20,20,new LinkedList<>(),false,false,false))


        );
    }


    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new FilterDTO(-2,-2,null,true,true,true
                )),
                Arguments.of(
                        new FilterDTO(
                                -1,-2,null,false,true,true)),
                Arguments.of(new FilterDTO(
                                -1,-1,null,false,false,false))



        );
    }

    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(FilterDTO filter) {
        Assertions.assertTrue(validator.validateProperty(filter,"min_capacity").isEmpty());
        Assertions.assertTrue(validator.validateProperty(filter,"max_capacity").isEmpty());
        Assertions.assertTrue(validator.validateProperty(filter,"types").isEmpty());
        Assertions.assertTrue(validator.validateProperty(filter,"sortByCapacity").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(FilterDTO filter) {

        Assertions.assertFalse(validator.validateProperty(filter,"min_capacity").isEmpty());
        Assertions.assertFalse(validator.validateProperty(filter,"max_capacity").isEmpty());
        Assertions.assertFalse(validator.validateProperty(filter,"types").isEmpty());

    }
}
