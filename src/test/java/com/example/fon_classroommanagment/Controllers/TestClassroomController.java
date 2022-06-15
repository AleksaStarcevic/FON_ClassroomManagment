package com.example.fon_classroommanagment.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Services.ClassroomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Constants.SECRET;
import static com.example.fon_classroommanagment.Configuration.Constants.VALIDATION_TOKEN_EXPIRATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = TestAppointmentController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestClassroomController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private ClassroomService service;

    @MockBean
    private ClassroomController controller;
    @Test
    public void Test_GetClassroomsPaging() throws Exception {
//        int page=1;
//        List<Classroom> lista=Arrays.asList(
//                new Classroom(1L,"Test1",10,10,true,true,new ClassroomType(1L,"Ucionica")),
//                        new Classroom(1L,"Test2",10,10,true,true,new ClassroomType(1L,"Ucionica")
//                ));
//
//        List<ClassroomPagingDTO> results=Arrays.asList(new ClassroomPagingDTO("Test1",10,true,true),new ClassroomPagingDTO("Test2",10,true,true));
//       String paresJsonResult=objectMapper.writeValueAsString(results);
//        String token=CreateJWTToken("ROLE_USER");
//        when(service.getAllClassrooms(page)).thenReturn(lista);
//        when(controller.getClassrooms(page)).thenReturn(ResponseEntity.ok(results));
//        mockMvc.perform(get("/getClassrooms").header("Authorization","Bearer "+ token).param("page",String.valueOf(page)))
//                .andExpect(content().json(paresJsonResult))
//                .andExpect(status().isOk());

    }

    private String CreateJWTToken(String role) {
        Algorithm algorithm=Algorithm.HMAC256(SECRET.getBytes());

        Set<SimpleGrantedAuthority> admin = Collections.singleton(new SimpleGrantedAuthority(role));
        return JWT.create()
                .withSubject("radojkovicika@gmail.com")
                .withExpiresAt(new Date( Calendar.getInstance().getTimeInMillis() + (VALIDATION_TOKEN_EXPIRATION)))
                .withClaim("roles",admin.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()) )
                .sign(algorithm);
    }


}
