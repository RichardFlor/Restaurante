package br.senai.sp.defaultproject.rest.specs;

import br.senai.sp.defaultproject.dtos.order.input.CreateOrderInputDTO;
import br.senai.sp.defaultproject.dtos.order.output.OrderOutputDTO;
import br.senai.sp.defaultproject.errors.responses.DuplicatedResourceErrorResponse;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseBadRequest;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseForbidden;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseInternalServerError;
import br.senai.sp.defaultproject.rest.specs.commons.response.error.ApiResponseNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@ApiResponseForbidden
@ApiResponseBadRequest
@ApiResponseInternalServerError
@ApiResponseNotFound
@Tag(name = "5. Order", description = "Order operations")
public interface OrderControllerSpecs {
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create order")
    @ApiResponses({
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Duplicate Resource", content = {
                    @Content(schema = @Schema(implementation = DuplicatedResourceErrorResponse.class)),
            })
    })

    @SecurityRequirement(name="jwt")
    void create(@RequestBody @Valid CreateOrderInputDTO request);

    @Operation(summary = "List orders")
    @ApiResponse(responseCode = "200", description = "Ok", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = OrderOutputDTO.class)))
    })
    @SecurityRequirement(name="jwt")
    Set<OrderOutputDTO> findAll();

}