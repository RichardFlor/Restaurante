package br.senai.sp.defaultproject.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://127.0.0.1:5500",
                        "http://10.107.131.52:3000" // se tiver um front rodando em outro IP da rede
                )
                .allowedHeaders("*")
                .exposedHeaders("content-range", "total-pages")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "HEAD", "OPTIONS")
                .allowCredentials(true);
    }
}
