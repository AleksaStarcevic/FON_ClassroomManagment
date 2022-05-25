package com.example.fon_classroommanagment.Controllers;
import static com.example.fon_classroommanagment.Configuration.Constants.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHENTICATION;
import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHORIZATION;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
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
        mockMvc.perform(delete("/reserve")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }

    @Test
    public void Test_ConfirmAllAppointments_Exists_Valid() throws Exception {
        List<ConfirmAppointmentDTO> list=Arrays.asList(getValidConfirmAppointmentDTO(),getValidConfirmAppointmentDTO());
        String json=ConvertObjectToJson(list);
        String token=CreateJWTToken("USER");
        mockMvc.perform(post("/confirmAllAppointment").header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isBadRequest());

    }
    @Test
    public void Test_ConfirmAllAppointments_Exists_Invalid() throws Exception {


    }



    @Test
    public void Test_FilterAppointment_Exists() throws Exception {


        String json=ConvertObjectToJson(getValidReserveDTO());
        mockMvc.perform(get("/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }

    @Test
    public void Test_DeleteReservation_Valid() throws Exception {
        String token=CreateJWTToken("USER");

        String json=ConvertObjectToJson(getValidDeleteDTO(UUID.randomUUID()));
        mockMvc.perform(delete("/DeleteReservation").header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isOk());
    }

    @Test
    public void Test_ConfirmAppointment() throws Exception {
        String token=CreateJWTToken("ROLE_ADMIN");
        mockMvc.perform(post("/confirmAppointment").header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void Test_ConfirmAppointment_Forbidden() throws Exception {
        String token=CreateJWTToken("ROLE_USER");
        mockMvc.perform(post("/confirmAppointment").header("Authorization","Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    private ReserveDTO getValidReserveDTO() {
        return  new ReserveDTO("radojkovicika@gmail.com",1L,"ilija",new Date(),"testtestest","reasonreasonreason",
                100,2,4,1,1);
    }

    private  DeleteReservationDTO getValidDeleteDTO(UUID id){
        return new DeleteReservationDTO(id);
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



    private DeleteReservationDTO getValidDeleteReservationDTO(){
        return new DeleteReservationDTO(UUID.randomUUID());
    }
    private String ConvertObjectToJson(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    private ConfirmAppointmentDTO getValidConfirmAppointmentDTO(){
        return  new ConfirmAppointmentDTO(UUID.randomUUID());
    }

    private ConfirmAppointmentDTO getInvalidConfirmAppointmentDTO() {
        return new ConfirmAppointmentDTO();
    }
}
