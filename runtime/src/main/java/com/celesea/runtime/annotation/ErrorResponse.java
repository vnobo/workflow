package com.celesea.runtime.annotation;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * ErrorResponse
 *
 * @author <a href="https://github.com/vnobo">Alex bob</a>
 * @date Created by 2020/7/22
 */
@Data
@ApiModel("Request controller error entity model")
public class ErrorResponse implements Serializable {

  private UUID requestId;
  private LocalDateTime time;
  private String message;
  private int status;
  private Object errors;

  @Builder
  private ErrorResponse(@NotNull HttpStatus status, @NotEmpty String message, Object errors) {
    this.message = message;
    this.errors = errors;
    this.requestId = UUID.randomUUID();
    this.time = LocalDateTime.now();
    this.errors = status.value();
  }

  public static ErrorResponse withMessage(String message) {
    return ErrorResponse.builder().message(message).build();

  }

  public ErrorResponse message(String message) {
    this.message = message;
    return this;
  }

  public ErrorResponse status(HttpStatus status) {
    this.status = status.value();
    return this;
  }

  public ErrorResponse errors(Object errors) {
    this.errors = errors;
    return this;
  }

}