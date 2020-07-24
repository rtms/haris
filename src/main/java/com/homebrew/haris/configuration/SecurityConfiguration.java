package com.homebrew.haris.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}").roles("USER")
                .and()
                .withUser("admin").password("{noop}").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/machines/**").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/machines").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/machines/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/machines/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/machines/**").hasRole("ADMIN")
            .and()
            .csrf().disable()
            .formLogin().disable();
    }

}
