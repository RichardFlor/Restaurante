package br.senai.sp.defaultproject.usecases.user;

import br.senai.sp.defaultproject.dtos.email.input.SendEmailInputDTO;
import br.senai.sp.defaultproject.dtos.user.input.CreateUserInputDTO;
import br.senai.sp.defaultproject.entities.User;
import br.senai.sp.defaultproject.enums.EmailTemplate;
import br.senai.sp.defaultproject.enums.UserRole;
import br.senai.sp.defaultproject.errors.exceptions.DuplicatedResourceException;
import br.senai.sp.defaultproject.mappers.user.UserStructMapper;
import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import br.senai.sp.defaultproject.services.SmtpEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserJpaRepository userJpaRepository;
    private final SmtpEmailService smtpEmailService;
    private final UserStructMapper userStructMapper;

    @Transactional
    public void execute(CreateUserInputDTO input) {
        if (this.userJpaRepository.findByEmail(input.getEmail()).isPresent())
            throw new DuplicatedResourceException(User.class, Map.of("email", input.getEmail()));

        var user = userStructMapper.toEntity(input).withRole(UserRole.WAITER);
        user.setPassword(new BCryptPasswordEncoder().encode(input.getPassword()));

        log.warn("Creating user with email: {} ", input.getEmail());
        var createdUser = userJpaRepository.save(user);

        sendEmailToValidation(createdUser);
    }

    private void sendEmailToValidation(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("link", generateUrlToValidateEmail(user.getId()));

        var template = smtpEmailService.processTemplate(data, EmailTemplate.EMAIL_VALIDATION);

        var emailMessage =
                new SendEmailInputDTO(
                        List.of(user.getEmail()),
                        "Validação de E-mail",
                        template
                );

        smtpEmailService.sendEmail(emailMessage);
    }

    private String generateUrlToValidateEmail(UUID userId) {
        var baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/v1";
        return MessageFormat.format(
                "{0}/user/{1}/validate-email",
                baseUrl,
                userId
        );
    }
}
