package br.senai.sp.defaultproject.dtos.auth.input;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class RequirePasswordRecoveryInputDTO {
    @Email
    private String email;
}
