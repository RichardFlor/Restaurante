package br.senai.sp.defaultproject.usecases.user;

import br.senai.sp.defaultproject.entities.User;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidateEmailUseCase {
    private final UserJpaRepository userJpaRepository;
    private final TemplateEngine templateEngine;

    public String execute(UUID id){
        var foundUser = userJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        log.info("Validating email: {}",foundUser.getEmail());
        foundUser.setEmailValidatedAt(LocalDateTime.now());
        userJpaRepository.save(foundUser);

        return this.getValidatedEmailPage();
    }

    private String getValidatedEmailPage(){
        var context = new Context();
        return templateEngine.process("email-validation-page", context);
    }
}
