package br.senai.sp.defaultproject.errors.responses;

import br.senai.sp.defaultproject.errors.ErrorResponse;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.details.InternalServerErrorDetails;

public class InternalServerErrorResponse extends ErrorResponse<InternalServerErrorDetails> {
    public InternalServerErrorResponse(InternalServerErrorDetails details) {
        super(ExceptionCode.INTERNAL_SERVER_ERROR, details);
    }
}
