package br.senai.sp.defaultproject.usecases.auth;

import br.senai.sp.defaultproject.dtos.auth.input.LoginInputDTO;
import br.senai.sp.defaultproject.dtos.auth.output.LoginOutputDTO;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.exceptions.ForbiddenException;
import br.senai.sp.defaultproject.security.dto.UserDetailsDTO;
import br.senai.sp.defaultproject.security.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginUseCase {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public LoginOutputDTO execute(LoginInputDTO input){
        var usernamePassword = new UsernamePasswordAuthenticationToken(input.getEmail().trim(), input.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var userDetails = (UserDetailsDTO) auth.getPrincipal();
        var userEmailIsNotValidated = Objects.isNull(userDetails.getUser().getEmailValidatedAt());

        if (userEmailIsNotValidated)
            throw new ForbiddenException(ExceptionCode.EMAIL_NOT_VALIDATED);

        var token = this.jwtTokenService.generateToken(userDetails);

        return new LoginOutputDTO(token);
    }
}
