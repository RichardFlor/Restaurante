package br.senai.sp.defaultproject.services;

import br.senai.sp.defaultproject.entities.User;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.errors.exceptions.ForbiddenException;
import br.senai.sp.defaultproject.security.dto.UserDetailsDTO;
import br.senai.sp.defaultproject.usecases.user.FindUserByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationContextServiceImpl {
    private final FindUserByIdUseCase findUserByIdUseCase;

    public UserDetailsDTO getData() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Optional)
            return this.handleOptionalData((Optional<UserDetailsDTO>) principal);

        if (principal instanceof UserDetailsDTO)
            return (UserDetailsDTO) principal;

        throw new ForbiddenException();
    }

    private UserDetailsDTO handleOptionalData(Optional<UserDetailsDTO> principal) {
        principal.orElseThrow(() -> new EntityNotFoundException(UserDetailsDTO.class));
        return principal.get();
    }

    public User getLoggedUser() {
        return this.getData().getUser();
    }
}
