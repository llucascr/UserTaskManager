package br.com.tarefas.exception;

import org.springframework.http.HttpStatus;

public class TarefaNotFound extends RuntimeException implements ApiExceptionContrato {

    private final String code = "TAREFA_NOT_FOUND";
    private String message;

    public TarefaNotFound(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
