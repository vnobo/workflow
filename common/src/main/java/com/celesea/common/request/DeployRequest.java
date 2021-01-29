package com.celesea.common.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * com.celesea.common.request.DeployRequest
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/27
 */
@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeployRequest {

  @NotBlank
  private String key;

  @NotBlank
  private String name;

  @NotBlank
  private String category;

  @NotBlank
  private String tenantId;

  @NotNull
  private String model;


}