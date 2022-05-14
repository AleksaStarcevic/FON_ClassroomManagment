package com.example.fon_classroommanagment.Configuration;

import com.example.fon_classroommanagment.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.crypto.Data;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
@Autowired
private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().mvcMatchers("/register","/registerConfirmed/{token}","/test").permitAll().and()

                .authorizeRequests().anyRequest().authenticated().and()
                .formLogin().and().httpBasic()
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
        provider.setPasswordEncoder(getEncoder());
        return  provider;
    }
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }



}
