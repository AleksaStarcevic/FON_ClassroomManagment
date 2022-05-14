package com.example.fon_classroommanagment.Controllers;


import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.AccountDTO;
import com.example.fon_classroommanagment.Models.Emplayee.EducationTitle;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeDepartment;
import com.example.fon_classroommanagment.Models.Emplayee.EmployeeType;
import com.example.fon_classroommanagment.Models.User.Account;
import com.example.fon_classroommanagment.Models.User.ValidationToken;
import com.example.fon_classroommanagment.Services.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TestAuthenticationController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestAuthenticationController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;


    @Autowired
    ObjectMapper objectMapper;





@Test
    public void TestRegisterSuccess_Route() throws Exception {


        AccountDTO dto=CreateValidAccountDTO();
        Account account=dto.CreateAccount();


    when(accountService.createValidationToken(account)).thenReturn(new ValidationToken(UUID.randomUUID().toString(),account));


    final String expectedResponseContent = ConvertObjectToJson(dto);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(expectedResponseContent)).andExpect(status().isOk());


    }

    private String ConvertObjectToJson(Object dto) throws JsonProcessingException {
    return objectMapper.writeValueAsString(dto);
    }

    private AccountDTO CreateValidAccountDTO() {
    return  new AccountDTO(
            "ilija",
            "radojkovic",
            new EmployeeDepartment(1L,"cao"),
            new EducationTitle(1L,"cao"),
            new EmployeeType(1L,"cao"),
            "radojkovic@gmail.com",
            "1234"
         );
    }


}
