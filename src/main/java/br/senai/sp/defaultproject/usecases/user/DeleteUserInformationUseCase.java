package br.senai.sp.defaultproject.usecases.user;

import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import br.senai.sp.defaultproject.services.AuthenticationContextServiceImpl;
import br.senai.sp.defaultproject.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserInformationUseCase {
    private final UserJpaRepository repository;
    private final AuthenticationContextServiceImpl authService;
    private final ImageService imageService;

    public void execute() {
        var user = authService.getLoggedUser();
        //Todo Miau pra funcionar o delete :)
        //imageService.delete(user.getProfileImageUri());
        repository.deleteById(user.getId());
    }
}
