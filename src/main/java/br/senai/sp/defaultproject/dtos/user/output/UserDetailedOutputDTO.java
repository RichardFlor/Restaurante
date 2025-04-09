package br.senai.sp.defaultproject.dtos.user.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailedOutputDTO {
    private UUID id;

    @Schema(example = "Richard")
    private String name;

    @Schema(example = "richard@gmail")
    private String email;

    private LocalDateTime disabledAt;
}
