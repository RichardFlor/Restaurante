package br.senai.sp.defaultproject.errors.exceptions;

import br.senai.sp.defaultproject.errors.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BusinessRuleException extends RuntimeException {
    private final ExceptionCode code;
    private Map<String, Object> details;

    public BusinessRuleException(ExceptionCode code,  Map<String, Object> details) {
        super(code.toString());
        this.code = code;
        this.details = details;
    }

    public BusinessRuleException(ExceptionCode code) {
        super(code.toString());
        this.code = code;
        this.details = null;
    }
}

