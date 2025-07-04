package br.senai.sp.defaultproject.mappers.user;


import br.senai.sp.defaultproject.dtos.user.input.CreateUserInputDTO;
import br.senai.sp.defaultproject.dtos.user.output.UserDetailedOutputDTO;
import br.senai.sp.defaultproject.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserStructMapper {
    UserDetailedOutputDTO toUserDetailedOutputDTO(User entity);

    User toEntity(CreateUserInputDTO dto);
}
