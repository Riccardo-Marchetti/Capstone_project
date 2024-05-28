package riccardo.BACKEND.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecFilterChainConfig {
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        // DISABLE DEFAULT BEHAVIORS
        // DISABLE LOGIN FORM
        httpSecurity.formLogin(http -> http.disable());
        // DISABLE CSRF PROTECTION
        httpSecurity.csrf(http -> http.disable());
        // REMOVE SESSIONS
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.cors(Customizer.withDefaults());
        // ALLOW ALL REQUESTS TO PASS THROUGH THE FILTER CHAIN, ALL RESOURCES ARE ACCESSIBLE TO ALL USERS WITH OR WITHOUT AUTHENTICATION
        httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder (){
        // Returns a BCryptPasswordEncoder with a strength of 11
        return new BCryptPasswordEncoder(11);
    }
}
