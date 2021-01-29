package com.celesea.engine.annotation;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

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
  public ErrorResponse(@NotEmpty String message, Object errors) {
    this.message = message;
    this.errors = errors;
    this.requestId = UUID.randomUUID();
    this.time = LocalDateTime.now();
  }

  /**
   * default query params max records 25, id>0
   *
   * @return default query body
   */
  public static ErrorResponse withDefault(Object errors) {
    return new ErrorResponse("请求验证参数错误,检查你的请求参数.", errors);
  }

  /**
   * default query params max records 25, id>0
   *
   * @return default query body
   */
  public static ErrorResponse withErrors(String message, Object errors) {
    ErrorResponse response = withDefault(errors);
    response.setMessage(message);
    return response;
  }

  public ErrorResponse status(int status) {
    this.status = status;
    return this;
  }
}