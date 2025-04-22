package br.senai.sp.defaultproject.rest.controllers;

import br.senai.sp.defaultproject.config.imageupload.ImageUploadEndpoint;
import br.senai.sp.defaultproject.dtos.user.input.ChangePasswordInputDTO;
import br.senai.sp.defaultproject.dtos.user.input.CreateUserInputDTO;
import br.senai.sp.defaultproject.dtos.user.output.UserDetailedOutputDTO;
import br.senai.sp.defaultproject.rest.specs.UserControllerSpecs;
import br.senai.sp.defaultproject.usecases.user.ChangePasswordUseCase;
import br.senai.sp.defaultproject.usecases.user.CreateUserUseCase;
import br.senai.sp.defaultproject.usecases.user.DeleteUserInformationUseCase;
import br.senai.sp.defaultproject.usecases.user.FindUserByIdUseCase;
import br.senai.sp.defaultproject.usecases.user.ValidateEmailUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController implements UserControllerSpecs {
    private final CreateUserUseCase createUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final ValidateEmailUseCase validateEmailUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final DeleteUserInformationUseCase deleteUserInformationUseCase;

    @ImageUploadEndpoint(body = CreateUserInputDTO.class)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void create(
            HttpServletRequest httpRequest,
            @RequestPart(required = false, name = "image") MultipartFile image,
            @RequestPart(name = "body") String body
    ) {
        var request = (CreateUserInputDTO) httpRequest.getAttribute("request");

        Optional.ofNullable(httpRequest.getAttribute("imageURI"))
                .map(Object::toString)
                .ifPresent(request::setProfileImageURI);

        this.createUserUseCase.execute(request);
    }

    @PatchMapping("/change-password")
    public void passwordRecovery(@RequestBody @Valid ChangePasswordInputDTO request) {
        this.changePasswordUseCase.execute(request);
    }

    @GetMapping("/{id}")
    public UserDetailedOutputDTO findById(@PathVariable UUID id) {
        return this.findUserByIdUseCase.execute(id);
    }

    @GetMapping("/{id}/validate-email")
    public String validateEmail(@PathVariable UUID id) {
        return this.validateEmailUseCase.execute(id);
    }

    @DeleteMapping
    public void delete() {
        this.deleteUserInformationUseCase.execute();
    }
}
