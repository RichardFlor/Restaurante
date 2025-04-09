package br.senai.sp.defaultproject.errors.details;

public record HandleMethodArgumentNotValidErrorDetails(
        String field,
        String[] messages
) {
}
