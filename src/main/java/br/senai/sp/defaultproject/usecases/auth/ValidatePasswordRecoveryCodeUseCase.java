package br.senai.sp.defaultproject.usecases.auth;

import br.senai.sp.defaultproject.dtos.auth.input.ValidatePasswordRecoveryCodeInputDTO;
import br.senai.sp.defaultproject.entities.User;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.exceptions.BusinessRuleException;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatePasswordRecoveryCodeUseCase {
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public void execute(ValidatePasswordRecoveryCodeInputDTO input){
        var user = this.userJpaRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        if (!input.getCode().equals(user.getPasswordRecoveryCode()))
            throw new BusinessRuleException(ExceptionCode.INVALID_PASSWORD_RECOVERY_CODE);
    }
}
