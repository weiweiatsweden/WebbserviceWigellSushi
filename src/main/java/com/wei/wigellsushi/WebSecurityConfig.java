package com.wei.wigellsushi;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {



    @Bean
    public PasswordEncoder getPasswordEncoder(){ return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        //.requestMatchers("/","/home").permitAll()
                        .requestMatchers("/api/v3/sushis").hasRole("USER")
                        .requestMatchers("/api/v3/bookroom").hasRole("USER")
                        .requestMatchers("/api/v3/updatebooking").hasRole("USER")
                        .requestMatchers("/api/v3/mybookings/{id}").hasRole("USER")
                        .requestMatchers("/api/v3/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v3/add-dish").hasRole("ADMIN")
                        .requestMatchers("/api/v3/deletedish").hasRole("ADMIN")
                        .requestMatchers("/api/v3/updateroom").hasRole("ADMIN")
                        .requestMatchers("/api/v3/allrooms").hasRole("ADMIN")


                        .anyRequest().authenticated()
                )
                .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
              User.builder()
                      .username("September")
                      .password("{noop}123")
                      .roles("USER")
                      .build();

        UserDetails admin =
                User.builder()
                        .username("Admin1")
                        .password("{noop}admin1")
                        .roles("ADMIN")
                        .build();


        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}