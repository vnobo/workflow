package com.celesea.engine.core.define;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

/**
 * com.celesea.engine.core.flow.FlowDine
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/18
 */
@Data
@Table("wf_defines")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlowDefine implements Serializable {

  @Id
  private Long id;

  @NotBlank
  private String tenantId;

  @NotNull
  private String key;

  @NotBlank
  private String name;

  @NotBlank
  private String category;

  @Min(0)
  @Max(Integer.MAX_VALUE)
  private Integer version;

  private String describe;

  @NotNull
  private JsonNode define;

  private JsonNode attributes;

  private JsonNode deployment;

  private String xml;

  @CreatedDate
  private LocalDateTime createdTime;

  @LastModifiedDate
  private LocalDateTime updatedTime;

  FlowDefine deployment(JsonNode deployment) {
    this.deployment = deployment;
    return this;
  }

  FlowDefine version(Integer version) {
    this.version = version;
    return this;
  }

}