package br.com.tarefas.handler;

import br.com.tarefas.dto.ErroResponse;
import br.com.tarefas.exception.ApiExceptionContrato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResponse> handlerApiException(ApiExceptionContrato contrato) {
        HttpStatus status = contrato.getHttpStatus();
        ErroResponse erro = new ErroResponse(
          contrato.getCode(),
          contrato.getMessage(),
          status.value()
        );
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handlerGenericException(Exception ex) {
        log.error("OCORREU UM ERRO INTERNO: " + ex.getMessage());
        log.error("CAUSA DO ERRO INTERNO: " + ex.getCause());
        ErroResponse erro = new ErroResponse(
          "INTERNAL_SERVER_ERRO",
                "Ocorreu um erro inesperado",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

}
