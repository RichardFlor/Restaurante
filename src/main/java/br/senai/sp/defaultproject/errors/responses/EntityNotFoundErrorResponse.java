package br.senai.sp.defaultproject.errors.responses;

import br.senai.sp.defaultproject.errors.ErrorResponse;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.details.EntityNotFoundErrorDetails;

public class EntityNotFoundErrorResponse extends ErrorResponse<EntityNotFoundErrorDetails> {
    public EntityNotFoundErrorResponse(EntityNotFoundErrorDetails details) {
        super(ExceptionCode.ENTITY_NOT_FOUND, details);
    }
}
