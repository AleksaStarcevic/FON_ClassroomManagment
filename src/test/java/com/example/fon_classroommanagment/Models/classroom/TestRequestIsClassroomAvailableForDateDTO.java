package com.example.fon_classroommanagment.Models.classroom;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.classroom.RequestIsClassroomAvailableForDateDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
import com.example.fon_classroommanagment.Models.user.TestAccountDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.stream.Stream;

import static com.example.fon_classroommanagment.Configuration.Constants.MAX_VREME_ZAKAZIVANJA;
import static com.example.fon_classroommanagment.Configuration.Constants.MIN_VREME_ZAKAZIVANJA;

@SpringBootTest(
        classes = TestAccountDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestRequestIsClassroomAvailableForDateDTO extends ModelTest<RequestIsClassroomAvailableForDateDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(RequestIsClassroomAvailableForDateDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"date").isEmpty());

        Assertions.assertTrue(validator.validateProperty(entity,"classroomId").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"start_timeInHours").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"end_timeInHours").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(RequestIsClassroomAvailableForDateDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"date").isEmpty());

        Assertions.assertFalse(validator.validateProperty(entity,"classroomId").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"start_timeInHours").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"end_timeInHours").isEmpty());
    }
    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new RequestIsClassroomAvailableForDateDTO(new Date(),2L,13,14))
        );
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new RequestIsClassroomAvailableForDateDTO(null,-2L,-1,-4)),

                Arguments.of(new RequestIsClassroomAvailableForDateDTO(null,0L,MAX_VREME_ZAKAZIVANJA+1,MAX_VREME_ZAKAZIVANJA+1)),
                Arguments.of(new RequestIsClassroomAvailableForDateDTO(null,0L,MIN_VREME_ZAKAZIVANJA-1,MIN_VREME_ZAKAZIVANJA-1))

        );
    }
}
