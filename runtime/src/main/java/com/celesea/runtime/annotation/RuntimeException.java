package com.celesea.runtime.annotation;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * mspbots.data.cw.core.annotation.AutoTaskApiException
 *
 * @author <a href="https://github.com/vnobo">Alex bob</a>
 * @date Created by 2020/7/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RuntimeException extends java.lang.RuntimeException implements Serializable {

  private String title;
  private HttpStatus status;
  private Object errors;

  public RuntimeException(HttpStatus status, String title) {
    super(title);
    this.status = status;
  }


  public RuntimeException title(String message) {
    this.title = message;
    return this;
  }

  public RuntimeException status(HttpStatus status) {
    this.status = status;
    return this;
  }

  public RuntimeException errors(Object errors) {
    this.errors = errors;
    return this;
  }
}