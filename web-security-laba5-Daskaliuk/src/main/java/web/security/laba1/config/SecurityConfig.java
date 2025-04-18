package web.security.laba1.config;

/*
    @author Daskaliuk
    @project laba1
    @class SecurityConfig
    @version 1.0.0
    @since 13.03.2025 - 11.47
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/index.html").permitAll()
                        .requestMatchers("api/v1/persons/admin").hasRole("ADMIN")
                        .requestMatchers("api/v1/persons/delete/").hasRole("ADMIN")
                        .requestMatchers("api/v1/persons/update").hasRole("ADMIN")
                        .requestMatchers("api/v1/persons/getById/").hasRole("ADMIN")
                        .requestMatchers("api/v1/persons/").hasRole("ADMIN")
                        .requestMatchers("api/v1/persons/post").hasRole("ADMIN")
                        .requestMatchers("api/v1/persons/user").hasRole("USER")
                        .requestMatchers("api/v1/persons/getById/").hasRole("USER")
                        .requestMatchers("api/v1/persons/").hasRole("USER")
                        .requestMatchers("api/v1/persons/unknown").hasRole("UNKNOWN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build();

        UserDetails unknown = User.builder()
                .username("unknown")
                .password(passwordEncoder().encode("unknown"))
                .roles("UNKNOWN")
                .build();

        return new InMemoryUserDetailsManager(admin, user, unknown);
    }
}
