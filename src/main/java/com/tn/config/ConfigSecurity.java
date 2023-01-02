package com.tn.config;

import com.tn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/**", "api/v1/**/**").hasAnyAuthority("ADMIN")

                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
