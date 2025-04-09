package br.senai.sp.defaultproject.rest.specs;

import br.senai.sp.defaultproject.dtos.auth.input.LoginInputDTO;
import br.senai.sp.defaultproject.dtos.auth.input.RequirePasswordRecoveryInputDTO;
import br.senai.sp.defaultproject.dtos.auth.input.ValidatePasswordRecoveryCodeInputDTO;
import br.senai.sp.defaultproject.dtos.auth.output.LoginOutputDTO;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseBadRequest;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseBusinessRuleException;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseForbidden;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseInternalServerError;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseNotFound;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseUnauthorized;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ApiResponseBadRequest
@ApiResponseInternalServerError
@Tag(name = "1. Auth", description = "Auth operations")
public interface AuthControllerSpecs {

    @Operation(summary = "Login")
    @ApiResponseForbidden
    @ApiResponseUnauthorized
    @ApiResponse(responseCode = "200", description = "Ok", content = {
            @Content(schema = @Schema(implementation = LoginOutputDTO.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
    })
    LoginOutputDTO login(@RequestBody LoginInputDTO request);

    @Operation(summary = "Require Password Recovery")
    @ApiResponseNotFound
    @ApiResponse(responseCode = "204", description = "No Content")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void requirePasswordRecovery(@RequestBody RequirePasswordRecoveryInputDTO request);

    @Operation(summary = "Validate Password Recovery Code")
    @ApiResponseNotFound
    @ApiResponseBusinessRuleException
    @ApiResponse(responseCode = "204", description = "No Content")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void validatePasswordRecoveryCode(@RequestBody ValidatePasswordRecoveryCodeInputDTO request);
}
