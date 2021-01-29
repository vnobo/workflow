package com.celesea.engine.annotation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

/**
 * GlobalExceptionHandler
 *
 * @author <a href="https://github.com/vnobo">Alex bob</a>
 * @date Created by 2020/7/22
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ServerWebInputException.class)
  public ResponseEntity<ErrorResponse> handleBindException(ServerWebInputException ex) {

    log.debug("Request params error. msg:{}", ex.getMessage());
    List<String> errors = List.of(Objects.requireNonNull(ex.getLocalizedMessage()));
    if (ex instanceof WebExchangeBindException) {
      errors = ((WebExchangeBindException) ex).getBindingResult()
          .getAllErrors().parallelStream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.toList());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(ErrorResponse.withDefault(errors).status(HttpStatus.BAD_REQUEST.value()));

  }

  @ExceptionHandler(EngineException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(EngineException ex) {

    log.debug("Request run time server api error. msg: {}", ex.getMsg());
    ErrorResponse response = ErrorResponse.withErrors("流程运行引擎报错.",
        ex.getMsg()).status(ex.getStatus().value());
    return ResponseEntity.status(ex.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(response);

  }
}