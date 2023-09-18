package com.orange.securedfootballapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.POST, "/api/leagues").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/managers").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/referees").hasRole("LEAGUE_MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/teams").hasRole("LEAGUE_MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/coaches").hasRole("LEAGUE_MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/players").hasRole("COACH")
                .requestMatchers(HttpMethod.POST, "/api/matches").hasRole("REFEREE")
                .requestMatchers(HttpMethod.PUT, "/api/matches/**").hasRole("PLAYER")
                .anyRequest().permitAll());

        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password FROM users WHERE username=?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM users WHERE username=?");
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}pass123")
                .roles("ADMIN")
                .build();
        UserDetails coach = User.builder()
                .username("coach")
                .password("{noop}pass123")
                .roles("COACH")
                .build();
        UserDetails player = User.builder()
                .username("player")
                .password("{noop}pass123")
                .roles("PLAYER")
                .build();
        UserDetails manager = User.builder()
                .username("manager")
                .password("{noop}pass123")
                .roles("LEAGUE_MANAGER")
                .build();
        UserDetails referee = User.builder()
                .username("referee")
                .password("{noop}pass123")
                .roles("REFEREE")
                .build();

        return new InMemoryUserDetailsManager(admin, coach, player, manager, referee);
    }

}

