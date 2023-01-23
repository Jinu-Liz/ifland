package com.archive.ifland.config;

import com.archive.ifland.config.auth.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() { return PasswordEncoderFactories.createDelegatingPasswordEncoder(); }

  @Bean
  public AuthenticationProvider authenticationProvider() { return new CustomAuthenticationProvider(); }

  @Override
  public void configure(AuthenticationManagerBuilder auth) { auth.authenticationProvider(authenticationProvider()); }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {

    http
      .csrf()
        .disable();
//        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    http
      .authorizeRequests(
        request -> request
                    .antMatchers("/","/sign-up","/test","/login","/members/new","/mail/**").permitAll()
//                    .anyRequest().authenticated()
      )
      .formLogin(
        login -> login
          .loginPage("/login")
          .loginProcessingUrl("/login-process")
          .defaultSuccessUrl("/")
//          .successHandler()
//          .failureHandler()
          .permitAll()
      )
      .logout(
        logout -> logout
          .logoutSuccessUrl("/")
          .invalidateHttpSession(true)
      )
      .exceptionHandling(
        error -> error
          .accessDeniedPage("/")
      );
  }
}
