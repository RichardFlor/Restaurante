package br.senai.sp.defaultproject.rest.controllers;

import br.senai.sp.defaultproject.dtos.auth.input.LoginInputDTO;
import br.senai.sp.defaultproject.dtos.auth.input.RequirePasswordRecoveryInputDTO;
import br.senai.sp.defaultproject.dtos.auth.input.ValidatePasswordRecoveryCodeInputDTO;
import br.senai.sp.defaultproject.dtos.auth.output.LoginOutputDTO;
import br.senai.sp.defaultproject.rest.specs.AuthControllerSpecs;
import br.senai.sp.defaultproject.usecases.auth.LoginUseCase;
import br.senai.sp.defaultproject.usecases.auth.RequirePasswordRecoveryUseCase;
import br.senai.sp.defaultproject.usecases.auth.ValidatePasswordRecoveryCodeUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController implements AuthControllerSpecs {
    private final LoginUseCase loginUseCase;
    private final RequirePasswordRecoveryUseCase requirePasswordRecoveryUseCase;
    private final ValidatePasswordRecoveryCodeUseCase validatePasswordRecoveryCodeUseCase;

    @PostMapping("/login")
    public LoginOutputDTO login(@RequestBody @Valid LoginInputDTO request) {
        return loginUseCase.execute(request);
    }

    @PatchMapping("/require-password-recovery")
    public void requirePasswordRecovery(@RequestBody @Valid RequirePasswordRecoveryInputDTO request) {
        requirePasswordRecoveryUseCase.execute(request);
    }

    @PatchMapping("/validate-password-recovery-code")
    public void validatePasswordRecoveryCode(@RequestBody @Valid ValidatePasswordRecoveryCodeInputDTO request) {
        validatePasswordRecoveryCodeUseCase.execute(request);
    }
}