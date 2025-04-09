package br.senai.sp.defaultproject.errors.responses;

import br.senai.sp.defaultproject.errors.ErrorResponse;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.details.InvalidFieldErrorDetails;

public class InvalidFieldErrorResponse extends ErrorResponse<InvalidFieldErrorDetails> {
    public InvalidFieldErrorResponse(InvalidFieldErrorDetails details) {
        super(ExceptionCode.API_FIELDS_INVALID, details);
    }
}
