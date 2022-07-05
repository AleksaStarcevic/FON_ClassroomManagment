package com.example.fon_classroommanagment.Models.appointment;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.appointment.RequestedAppointmentsDTO;
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
public class TestRequestedAppointmentsDTO extends ModelTest<RequestedAppointmentsDTO> {
    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(RequestedAppointmentsDTO entity) {
        Assertions.assertTrue(validator.validateProperty(entity,"firstName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"lastName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(entity,"number_of_requests").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(RequestedAppointmentsDTO entity) {
        Assertions.assertFalse(validator.validateProperty(entity,"firstName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"lastName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(entity,"number_of_requests").isEmpty());
    }

    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new RequestedAppointmentsDTO(13L,"cest",new byte[100],"test","test",23L)));
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new RequestedAppointmentsDTO(-1L,null,null,null,null,-1L))
,                Arguments.of(new RequestedAppointmentsDTO(-1L,null,null,"","",-1L))

        );
    }
}
