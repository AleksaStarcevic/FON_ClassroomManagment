package com.example.fon_classroommanagment.Models.user;

import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.user.UpdateRoleDTO;
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
        classes = TestUpdateRole.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestUpdateRole extends ModelTest<UpdateRoleDTO> {






    private static Stream<Arguments> generateValid(){
        return  Stream.of(
               Arguments.of(
                        new UpdateRoleDTO(UUID.randomUUID(), 1L)


        ));
    }

    private static Stream<Arguments> generateInvalid(){
        return  Stream.of(
                Arguments.of(new UpdateRoleDTO(null,null
                ))
               );
    }


    @Override
    @ParameterizedTest
    @MethodSource("generateValid")
    protected void TestValid(UpdateRoleDTO dto) {
        Assertions.assertTrue(validator.validateProperty(dto,"id_user").isEmpty());
        Assertions.assertTrue(validator.validateProperty(dto,"id_role").isEmpty());

    }

    @Override
    @ParameterizedTest
    @MethodSource("generateInvalid")
    protected void TestInvalid(UpdateRoleDTO dto) {
        Assertions.assertFalse(validator.validateProperty(dto,"id_user").isEmpty());
        Assertions.assertFalse(validator.validateProperty(dto,"id_role").isEmpty());

    }


}
