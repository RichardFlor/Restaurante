package br.senai.sp.defaultproject.usecases.user;

import br.senai.sp.defaultproject.dtos.user.input.ChangePasswordInputDTO;
import br.senai.sp.defaultproject.entities.User;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.exceptions.BusinessRuleException;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangePasswordUseCase {
    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    @Transactional
    public void execute(ChangePasswordInputDTO input) {
        var user = userJpaRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        validate(user, input);

        user.setPasswordRecoveryCode(null);
        user.setPassword(bcryptPasswordEncoder.encode(input.getPassword()));

        userJpaRepository.save(user);
    }

    private void validate(User user, ChangePasswordInputDTO input) {
        if (!input.getCode().equals(user.getPasswordRecoveryCode()))
            throw new BusinessRuleException(ExceptionCode.INVALID_PASSWORD_RECOVERY_CODE);
    }
}
