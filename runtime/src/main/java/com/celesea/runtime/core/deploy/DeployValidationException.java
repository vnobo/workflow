package com.celesea.runtime.core.deploy;

import com.celesea.runtime.annotation.RuntimeException;
import org.springframework.http.HttpStatus;

/**
 * com.celesea.activiti.annotation.DeployValidationExcception
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/27
 */
public class DeployValidationException extends RuntimeException {

  public DeployValidationException(HttpStatus status, String titile) {
    super(status, titile);
  }

  public static DeployValidationException withDefault() {
    return new DeployValidationException(HttpStatus.BAD_REQUEST, "流程发布验证失败.");
  }
}