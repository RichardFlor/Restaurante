package br.senai.sp.defaultproject.errors.responses;

import br.senai.sp.defaultproject.errors.ErrorResponse;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.details.DuplicatedResourceErrorDetails;

public class DuplicatedResourceErrorResponse extends ErrorResponse<DuplicatedResourceErrorDetails> {
    public DuplicatedResourceErrorResponse(DuplicatedResourceErrorDetails details) {
        super(ExceptionCode.DUPLICATED_RESOURCE, details);
    }
}
