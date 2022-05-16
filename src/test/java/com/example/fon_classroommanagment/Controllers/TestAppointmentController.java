package com.example.fon_classroommanagment.Controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.fon_classroommanagment.Configuration.UserProfileDetails;
import com.example.fon_classroommanagment.Filters.UserFilter;
import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import com.example.fon_classroommanagment.Models.DTO.ConfirmAppointmentDTO;
import com.example.fon_classroommanagment.Models.DTO.DeleteReservationDTO;
import com.example.fon_classroommanagment.Models.DTO.ReserveDTO;
import com.example.fon_classroommanagment.Models.User.UserProfile;
import com.example.fon_classroommanagment.Models.User.UserRole;
import com.example.fon_classroommanagment.Services.AppointmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.fon_classroommanagment.Configuration.Constants.SECRET;
import static com.example.fon_classroommanagment.Configuration.Constants.VALIDATION_TOKEN_EXPIRATION;
import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHENTICATION;
import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHORIZATION;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TestAppointmentController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes= FonClassroomManagmentApplication.class)
public class TestAppointmentController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

@MockBean
private UserFilter userFilter;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void Test_DeleteAppointment_Exists() throws Exception {
    String json=ConvertObjectToJson(getValidDeleteReservationDTO());
        mockMvc.perform(delete("/DeleteReservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }
    @Test
    public void Test_ConfirmAppointment_Exists() throws Exception {


        String json=ConvertObjectToJson(getValidConfirmAppointmentDTO());
        mockMvc.perform(delete("/confirmAppointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }
    @Test
    public void Test_ReserveAppointment_Exists() throws Exception {


        String json=ConvertObjectToJson(getValidReserveDTO());
        mockMvc.perform(delete("/ reserve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }


//    @Test
//    public void Test_DeleteReservation_Valid() throws Exception {
//
//
//        String json=ConvertObjectToJson(getValidDeleteDTO(UUID.randomUUID()));
//        mockMvc.perform(delete("/DeleteReservation").with(jwt())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json)).andExpect(status().isOk());
//    }
    private ReserveDTO getValidReserveDTO() {
        return  new ReserveDTO("radojkovicika@gmail.com",1,"ilija",new Date(),"testtestest","reasonreasonreason",
                100,2,4,1,1);
    }

    private  DeleteReservationDTO getValidDeleteDTO(UUID id){
        return new DeleteReservationDTO(id);
    }

//    @Test
//    public void Test_DeleteReservation_Success() throws Exception {
//
//        String id="13g3erg12125g";
//
//        mockMvc.perform(delete("/DeleteReservation").param("id",id))
//                .andExpect(status().isOk());
//    }
//


    private DeleteReservationDTO getValidDeleteReservationDTO(){
        return new DeleteReservationDTO(UUID.randomUUID());
    }
    private String ConvertObjectToJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    private ConfirmAppointmentDTO getValidConfirmAppointmentDTO(){
        return  new ConfirmAppointmentDTO(UUID.randomUUID());
    }


}
