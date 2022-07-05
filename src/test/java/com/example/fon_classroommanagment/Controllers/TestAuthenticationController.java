package com.example.fon_classroommanagment.Controllers;


import com.example.fon_classroommanagment.Configuration.SecurityConfiguration;
import com.example.fon_classroommanagment.FonClassroomManagmentApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TestAuthenticationController.class)
@AutoConfigureMockMvc

@ContextConfiguration(classes= {FonClassroomManagmentApplication.class, SecurityConfiguration.class})
public class TestAuthenticationController {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AccountService accountService;
//
//
//
//    @Autowired
//    ObjectMapper objectMapper;
//    @MockBean
//    private UserRepository userRepository;
//
//
//@MockBean
//private UserFilter userFilter;
//
//@Test
//public void Test_RegisterRoute_Exists() throws Exception {
//
//    final String expectedResponseContent = ConvertObjectToJson(CreateValidAccountDTO());
//
//    mockMvc.perform(post("/register")
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON)
//            .content(expectedResponseContent));
//}
//
//@Test
//public void Test_RegistrationConfirmed_Exists() throws Exception {
//    mockMvc.perform(get("/registerConfirmed/{token}","gergwe1232g")
//           );
//}
//
//
//@Test
//public void Test_ChangePassword_Exists() throws Exception {
//    final String expectedResponseContent = ConvertObjectToJson(CreateChangePasswordDTOValid());
//
//    mockMvc.perform(post("/ChangePassword")
//            .contentType(MediaType.APPLICATION_JSON).content(expectedResponseContent));
//
//}
//
//
//@Test
//public void Test_Register_Valid()  {
//
//}
//    private String CreateJWTToken(String role) {
//        Algorithm algorithm=Algorithm.HMAC256(SECRET.getBytes());
//
//        Set<SimpleGrantedAuthority> admin = Collections.singleton(new SimpleGrantedAuthority(role));
//        return JWT.create()
//                .withSubject("radojkovicika@gmail.com")
//                .withExpiresAt(new Date( Calendar.getInstance().getTimeInMillis() + (VALIDATION_TOKEN_EXPIRATION)))
//                .withClaim("roles",admin.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()) )
//                .sign(algorithm);
//    }
////@ParameterizedTest
////@MethodSource("getInvalidRegisterObjects")
////public void Test_InvalidBodyRegister(AccountDTO dto) throws Exception {
////    final String expectedResponseContent = ConvertObjectToJson(dto);
////
////    mockMvc.perform(post("/register")
////            .contentType(MediaType.APPLICATION_JSON)
////            .accept(MediaType.APPLICATION_JSON)
////            .content(expectedResponseContent)).andExpect(status().isBadRequest());
////}
//
//private static Stream<Arguments> getInvalidRegisterObjects(){
//    return   Stream.of(
//
//
//
//            Arguments.of(new AccountDTO(
//                    "ilija",
//                    "radojkovic",
//                    new EmployeeDepartment(1L,"cao"),
//                    new EducationTitle(1L,"cao"),
//                    new EmployeeType(1L,"cao"),
//                    "radojkovic@gmail.com",
//                    "1",new byte[10]
//            )),
//            Arguments.of(new AccountDTO(
//                    "ilija",
//                    "radojkovic",
//                    new EmployeeDepartment(1L,"cao"),
//                    new EducationTitle(1L,"cao"),
//                    new EmployeeType(1L,"cao"),
//                    "radojkovic@gmail.com",
//                    "12",
//                    new byte[10]
//            )),
//            Arguments.of(new AccountDTO(
//                    "ilija",
//                    "radojkovic",
//                    new EmployeeDepartment(1L,"cao"),
//                    new EducationTitle(1L,"cao"),
//                    new EmployeeType(1L,"cao"),
//                    "radojkovic@gmail.com",
//                    "123",
//                    new byte[10]
//            )),
//            Arguments.of(new AccountDTO(
//                    "ilija",
//                    "radojkovic",
//                   null,
//                    new EducationTitle(1L,"cao"),
//                    new EmployeeType(1L,"cao"),
//                    "radojkovic@gmail.com",
//                    "1",
//                    new byte[10]
//            )),
//            Arguments.of(new AccountDTO(
//                    "ilija",
//                    "radojkovic",
//                    new EmployeeDepartment(1L,"cao"),
//                  null,
//                    new EmployeeType(1L,"cao"),
//                    "radojkovic@gmail.com",
//                    "1"
//                    , new byte[10]
//            )),  Arguments.of(  new AccountDTO(
//            "ilija",
//            "radojkovic",
//            new EmployeeDepartment(1L,"cao"),
//            new EducationTitle(1L,"cao"),
//           null,
//            "radojkovic@gmail.com",
//            "1",
//                    new byte[10]
//    ))
//    );
//}
//    private AccountDTO CreateInvalidRegisterDTO() {
//        return  new AccountDTO(
//                "ilija",
//                "radojkovic",
//                new EmployeeDepartment(1L,"cao"),
//                new EducationTitle(1L,"cao"),
//                new EmployeeType(1L,"cao"),
//                "radojkovic@gmail.com",
//                "1"
//                , new byte[10]
//        );
//    }
//
//
//
//
//    private String ConvertObjectToJson(Object dto) throws JsonProcessingException {
//    return objectMapper.writeValueAsString(dto);
//    }
//
//    private AccountDTO CreateValidAccountDTO() {
//    return  new AccountDTO(
//            "ilija",
//            "radojkovic",
//            new EmployeeDepartment(1L,"cao"),
//            new EducationTitle(1L,"cao"),
//            new EmployeeType(1L,"cao"),
//            "radojkovic@gmail.com",
//            "1234"
//            , new byte[10]
//         );
//    }
//    private ChangePasswordDTO CreateChangePasswordDTOValid(){
//    return  new ChangePasswordDTO("1234",new Date());
//    }
//


}
