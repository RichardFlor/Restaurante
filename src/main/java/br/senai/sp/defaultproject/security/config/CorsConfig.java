//TODO: ARRUMAR DEPOIS

//package br.senai.sp.defaultproject.security.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    @Value("${cht.front.url}")
//    private String baseUrlFront;
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins(
//                        baseUrlFront,
//                        "http://localhost:3000"
//                )
//                .allowedHeaders("*")
//                .exposedHeaders("content-range", "total-pages")
//                .allowedMethods("GET", "POST", "PUT", "PATCH","DELETE", "HEAD", "OPTIONS")
//                .allowCredentials(true);
//    }
//}