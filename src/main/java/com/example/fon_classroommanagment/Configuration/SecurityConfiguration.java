package com.example.fon_classroommanagment.Configuration;

import com.example.fon_classroommanagment.Filters.BeforeRequestTokenFilter;
import com.example.fon_classroommanagment.Filters.UserFilter;
import com.example.fon_classroommanagment.Models.Email.EmailSender;
import com.example.fon_classroommanagment.Services.UserService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import javax.xml.crypto.Data;

import java.util.Arrays;
import java.util.List;

import static com.example.fon_classroommanagment.Configuration.Routes.*;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder encoder;

private final UserService userService;

    public SecurityConfiguration(BCryptPasswordEncoder encoder, UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers(REGISTER_CONFIRM,
                        REGISTER,
                        COMMON_PREFIX+COMMON_ALL_CLASSROOM_TYPES,
                        COMMON_PREFIX+COMMON_ALL_EMPLOYEE_TYPES,
                        COMMON_PREFIX+COMMON_ALL_EDUCATION_TITLES,
                        COMMON_PREFIX+COMMON_ALL_EMPLOYEE_DEPARTMENTS,
                        COMMON_PREFIX+COMMON_ALL_CLASSROOM_NAMES,
                        LOGIN,
                        LOGOUT).permitAll()
               // .mvcMatchers().permitAll()
                .and()
                .authorizeRequests().mvcMatchers(APPOINTMENT_PREFIX+APPOINTMENT_DECLINE,APPOINTMENT_PREFIX+APPOINTMENT_CONFIRM,APPOINTMENT_PREFIX+APPOINTMENT_CONFIRM_ALL,USER_PREFIX+USER_REQUESTED_APPOINTMENTS,USER_PREFIX+USER_UPDATE_ROLE).hasAuthority("ADMIN").and()
                .authorizeRequests().anyRequest().authenticated().and()
                .formLogin().and().httpBasic()
                .and().addFilter(new UserFilter(authenticationManager()))
                .addFilterBefore(new BeforeRequestTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

        http.cors(c ->{
            CorsConfigurationSource cs = r -> {
                CorsConfiguration cc = new CorsConfiguration();
                cc.setAllowedOrigins(List.of("*"));
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowedMethods(List.of("*"));
                cc.setExposedHeaders(List.of("*"));

                return  cc;
            };
            c.configurationSource(cs);

        });

    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
       auth.authenticationProvider(getProvider());
    }

    @Bean
    public DaoAuthenticationProvider getProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(encoder);
        return  provider;
    }




}
