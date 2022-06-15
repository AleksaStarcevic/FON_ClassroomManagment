package com.example.fon_classroommanagment.Configuration;

import com.example.fon_classroommanagment.Filters.BeforeRequestTokenFilter;
import com.example.fon_classroommanagment.Filters.UserFilter;
import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.xml.crypto.Data;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
@Autowired
private UserService userService;
@Autowired
private BCryptPasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().mvcMatchers("/allClassroomTypes","/allEmployeeTypes","/allEducationTitle","/allEmployeeDepartment","/test","/register","/registerConfirmed/{token}").permitAll().and()
                .authorizeRequests().mvcMatchers("/confirmAppointment","/requestedAppointments").hasAuthority("ADMIN").and()
                .authorizeRequests().anyRequest().authenticated().and()
                .formLogin().and().httpBasic()
                .and().addFilter(new UserFilter(authenticationManager()))
                .addFilterBefore(new BeforeRequestTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

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
