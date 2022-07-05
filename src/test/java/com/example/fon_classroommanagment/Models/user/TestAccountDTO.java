package com.example.fon_classroommanagment.Models.user;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.user.AccountDTO;
import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import com.example.fon_classroommanagment.Models.ModelTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest(
        classes = TestAccountDTO.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestAccountDTO extends ModelTest<AccountDTO> {
    @Autowired
    ObjectMapper objectMapper;
    public static Stream<Arguments> generateInvalid() {
        return Stream.of(
                Arguments.of(new AccountDTO(
                                null,
                                null,
                                null,
                                null,
                               null,
                                null,
                                null,null
                )),
                        Arguments.of(
                        new AccountDTO(
                                "1234567891011121314151617",
                                "1234567891011121314151617",
                                null,
                                null,
                                null,
                                "gerrew2l.com",
                                "g1", new byte[10]))


                );

    }
    public static Stream<Arguments> generateValid() {
        return Stream.of(
                Arguments.of(new AccountDTO(
                        "ilija",
                        "radojkovic",
                        new EmployeeDepartment(1L,"test"),
                        new EducationTitle(1L,"test"),
                        new EmployeeType(1L,"test"),
                        "radojkovicika@gmail.com",
                        "1234", new byte[10])),
                        Arguments.of(new AccountDTO(
                        "test",
                        "testosew",
                        new EmployeeDepartment(1L,"test"),
                        new EducationTitle(1L,"test"),
                        new EmployeeType(1L,"test"),
                        "gerrew22@gmail.com",
                        "grertj431", new byte[10]))


                );

    }


    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(AccountDTO accountDTO) {
        Assertions.assertTrue(validator.validateProperty(accountDTO,"firstName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(accountDTO,"lastName").isEmpty());
        Assertions.assertTrue(validator.validateProperty(accountDTO,"email").isEmpty());
        Assertions.assertTrue(validator.validateProperty(accountDTO,"password").isEmpty());

        Assertions.assertTrue(validator.validateProperty(accountDTO,"department").isEmpty());

        Assertions.assertTrue(validator.validateProperty(accountDTO,"title").isEmpty());
        Assertions.assertTrue(validator.validateProperty(accountDTO,"type").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(AccountDTO accountDTO) {

        Assertions.assertFalse(validator.validateProperty(accountDTO, "firstName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(accountDTO,"lastName").isEmpty());
        Assertions.assertFalse(validator.validateProperty(accountDTO,"email").isEmpty());
        Assertions.assertFalse(validator.validateProperty(accountDTO,"password").isEmpty());

        Assertions.assertFalse(validator.validateProperty(accountDTO,"department").isEmpty());

        Assertions.assertFalse(validator.validateProperty(accountDTO,"title").isEmpty());
        Assertions.assertFalse(validator.validateProperty(accountDTO,"type").isEmpty());

    }
}
