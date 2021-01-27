package com.me.interview.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.POST).permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/customers/*").permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.PUT, "/customers/*").permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/cities/*").denyAll();
    http.authorizeRequests().antMatchers(HttpMethod.PUT, "/cities/*").denyAll();
  }

}
