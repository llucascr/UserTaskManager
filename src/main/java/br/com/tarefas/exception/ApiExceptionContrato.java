package br.com.tarefas.exception;

import org.springframework.http.HttpStatus;

public interface ApiExceptionContrato {
    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
