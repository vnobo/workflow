package com.celesea.runtime.annotation;

import java.util.List;
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

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleBindException(ServerWebInputException ex) {
    log.debug("请求报错. 错误:{}", ex.getMessage());
    List<String> errors = List.of("你的请求参数不能为空.");
    if (ex instanceof WebExchangeBindException) {
      errors = ((WebExchangeBindException) ex).getBindingResult()
          .getAllErrors().parallelStream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.toList());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(ErrorResponse.withMessage("请求参数错误.")
            .status(HttpStatus.BAD_REQUEST).errors(errors));

  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
    log.debug("服务器发生错误. 错误: {}", ex.getErrors());
    ErrorResponse response = ErrorResponse.withMessage("请求服务器错误.")
        .status(ex.getStatus()).errors(ex.getErrors());
    return ResponseEntity.status(ex.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(response);

  }
}