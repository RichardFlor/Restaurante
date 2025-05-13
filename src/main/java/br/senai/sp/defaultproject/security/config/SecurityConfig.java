package br.senai.sp.defaultproject.security.config;

import br.senai.sp.defaultproject.enums.UserRole;
import br.senai.sp.defaultproject.security.filters.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationFilter authenticationFilter;

    private static final String[] SWAGGER_RESOURCES = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/docs",
            "/swagger-ui/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/docs/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/require-password-recovery").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/validate-password-recovery-code").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/user/change-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/*/validate-email").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/board").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/dish").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/order").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/v1/order").permitAll()
                        .requestMatchers(SWAGGER_RESOURCES).permitAll()



                        .requestMatchers(HttpMethod.POST, "/api/v1/board").hasAnyAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/board").hasAnyAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/board/*").hasAnyAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/dish").hasAnyAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/dish/*").hasAnyAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/order").hasAnyAuthority(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/order").hasAnyAuthority(UserRole.ADMIN.name())



                        .requestMatchers(HttpMethod.PUT, "/api/v1/board/*").hasAnyAuthority(UserRole.WAITER.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/order").hasAnyAuthority(UserRole.WAITER.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/order").hasAnyAuthority(UserRole.WAITER.name())

                        .anyRequest().authenticated()
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
