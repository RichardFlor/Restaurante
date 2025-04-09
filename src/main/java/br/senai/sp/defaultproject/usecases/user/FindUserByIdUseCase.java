package br.senai.sp.defaultproject.usecases.user;

import br.senai.sp.defaultproject.dtos.user.output.UserDetailedOutputDTO;
import br.senai.sp.defaultproject.entities.User;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.mappers.user.UserStructMapper;
import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindUserByIdUseCase {
    private final UserJpaRepository userJpaRepository;
    private final UserStructMapper userStructMapper;

    public UserDetailedOutputDTO execute(UUID id) {
        var user = this.userJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        return this.userStructMapper.toUserDetailedOutputDTO(user);
    }
}
