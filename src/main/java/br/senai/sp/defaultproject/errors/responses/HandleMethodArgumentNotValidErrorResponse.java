package br.senai.sp.defaultproject.errors.responses;

import br.senai.sp.defaultproject.errors.ErrorResponse;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.details.HandleMethodArgumentNotValidErrorDetails;

public class HandleMethodArgumentNotValidErrorResponse extends ErrorResponse<HandleMethodArgumentNotValidErrorDetails> {
    public HandleMethodArgumentNotValidErrorResponse(HandleMethodArgumentNotValidErrorDetails details) {
        super(ExceptionCode.API_FIELDS_INVALID, details);
    }
}
