package br.senai.sp.defaultproject.usecases.auth;

import br.senai.sp.defaultproject.dtos.auth.input.RequirePasswordRecoveryInputDTO;
import br.senai.sp.defaultproject.dtos.email.input.SendEmailInputDTO;
import br.senai.sp.defaultproject.entities.User;
import br.senai.sp.defaultproject.enums.EmailTemplate;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import br.senai.sp.defaultproject.services.RandomCodeService;
import br.senai.sp.defaultproject.services.SmtpEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequirePasswordRecoveryUseCase {
    private final UserJpaRepository userJpaRepository;
    private final RandomCodeService randomCodeService;
    private final SmtpEmailService smtpEmailService;

    public void execute(RequirePasswordRecoveryInputDTO input){
        var user = userJpaRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        var code = randomCodeService.generate();
        updateUser(user, code);
        sendEmail(code, user.getEmail());
    }

    private void updateUser(User user, String code){
        user.setPasswordRecoveryCode(code);
        userJpaRepository.save(user);
    }

    private void sendEmail(String newPassword, String receiver){
        Map<String, Object> data = new HashMap<>();
        data.put("code", newPassword);

        var template = smtpEmailService.processTemplate(data, EmailTemplate.FORGOT_PASSWORD);
        log.info("Generating template: {}", template);

        var emailMessage =
                new SendEmailInputDTO(
                        List.of(receiver),
                        "Alteração de senha",
                        template
                );

        smtpEmailService.sendEmail(emailMessage);
    }
}
