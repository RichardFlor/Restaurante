package br.senai.sp.defaultproject;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "jwt", scheme = "bearer", bearerFormat = "JWT")
public class DefaultProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefaultProjectApplication.class, args);

        log.info("""
                 \s
                .______       __    ______  __    __       ___      .______       _______ \s
                |   _  \\     |  |  /      ||  |  |  |     /   \\     |   _  \\     |       \\\s
                |  |_)  |    |  | |  ,----'|  |__|  |    /  ^  \\    |  |_)  |    |  .--.  |
                |      /     |  | |  |     |   __   |   /  /_\\  \\   |      /     |  |  |  |
                |  |\\  \\----.|  | |  `----.|  |  |  |  /  _____  \\  |  |\\  \\----.|  '--'  |
                | _| `._____||__|  \\______||__|  |__| /__/     \\__\\ | _| `._____||_______/\s
                                                                                          \s
                 \s
                  RICHARD PROJECT :: 1.0
                \s""");
    }
}
