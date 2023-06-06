package com.csaba79coder.littersnap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This is a temporary solution to provide a security configuration for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * This is a temporary solution to provide a password encoder for the application.
     * @return
     * @see <a href="https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-hello-bcrypt">Spring Security documentation</a>
     * @see <a href="https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt">Spring Security Registration Password Encoding with Bcrypt</a>
     * @see <a href="https://www.baeldung.com/spring-security-registration-i-password-encoding-bcrypt">Spring Security Registration Password Encoding with Bcrypt</a>
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This is a temporary solution to provide a security filter chain for the application.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()  // Allow access to all endpoints
                .and()
                .formLogin()
                .loginPage("/security/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();  // Disable CSRF protection
        return http.build();
    }

    /**
     * This is a temporary solution to provide a user for the application.
     * This is not a good practice, but it is enough for the moment.
     * <p>
     */
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") // password
                .roles("USER_ROLE")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW") // password
                .roles("ADMIN_ROLE")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
