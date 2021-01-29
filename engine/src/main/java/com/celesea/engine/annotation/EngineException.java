package com.celesea.engine.annotation;

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
public class EngineException extends RuntimeException implements Serializable {

  private Object msg;
  private HttpStatus status;

  public EngineException(Object msg) {
    this(HttpStatus.INTERNAL_SERVER_ERROR, msg);
  }

  public EngineException(HttpStatus status, Object msg) {
    super(msg.toString());
    this.msg = msg;
    this.status = status;
  }

  public static EngineException withMsg(HttpStatus status, Object msg) {
    return new EngineException(status, msg);
  }
}