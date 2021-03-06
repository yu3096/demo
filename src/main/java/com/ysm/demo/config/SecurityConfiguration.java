package com.ysm.demo.config;

import com.ysm.demo.config.jwt.JwtAuthenticationFilter;
import com.ysm.demo.config.jwt.JwtAuthorizationFilter;
import com.ysm.demo.security.repository.UserRepository;
import com.ysm.demo.security.service.UserPrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserPrincipalDetailsService userPrincipalDetailsService;
  private final UserRepository userRepository;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

    return daoAuthenticationProvider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
          .authorizeRequests()
            //.antMatchers("/h2-console/**").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .antMatchers("/api/public/management/*").hasRole("MANAGER")
            .antMatchers("/api/public/admin/*").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
          .headers().frameOptions().disable()
        .and()
          .addFilter(new JwtAuthenticationFilter(authenticationManager()))
          .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository));
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
