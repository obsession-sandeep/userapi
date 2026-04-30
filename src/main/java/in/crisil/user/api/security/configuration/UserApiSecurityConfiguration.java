package in.crisil.user.api.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UserApiSecurityConfiguration implements WebMvcConfigurer{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/**").permitAll() // Public endpoints
                        .anyRequest().authenticated() // Secure all other endpoints
                )
                .httpBasic(httpBasic -> httpBasic.disable()); // We’ll use JWT instead of basic auth

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000", "http://10.0.2.2:8080") // emulator
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
