package com.example.fon_classroommanagment.Models.user;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.user.EmployeeAdminCardDTO;
import com.example.fon_classroommanagment.Models.ModelTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest(
        classes = TestEmployeeAdminCardDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestEmployeeAdminCardDTO extends ModelTest<EmployeeAdminCardDTO> {






    private static Stream<Arguments> generateValid(){
        return  Stream.of(
                Arguments.of(new EmployeeAdminCardDTO(UUID.randomUUID(),"gwegewg","gewgweg","gwegweg")));
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new EmployeeAdminCardDTO(null,null,null,null)));
    }


    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(EmployeeAdminCardDTO dto) {
        Assertions.assertTrue(validator.validateProperty(dto,"id").isEmpty());
        Assertions.assertTrue(validator.validateProperty(dto,"firstName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(dto,"lastName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(dto,"permissionTitle").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(EmployeeAdminCardDTO dto) {
        Assertions.assertFalse(validator.validateProperty(dto,"id").isEmpty());
        Assertions.assertFalse(validator.validateProperty(dto,"firstName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(dto,"lastName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(dto,"permissionTitle").isEmpty());

    }


}
