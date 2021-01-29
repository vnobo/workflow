package com.celesea.engine.core.define;

import com.fasterxml.jackson.databind.JsonNode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.validation.annotation.Validated;

/**
 * com.celesea.engine.core.define.DefineRequest
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/28
 */
@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class DefineRequest implements Persistable<Long> {

  private Long id;
  /**
   * 租户ID
   */
  private String tenantId = "celesea";
  /**
   * 流程分类
   */
  private String category = "finance";
  /**
   * 流程名
   */
  @NotBlank(message = "流程名字[name]不能为空.")
  private String name;
  /**
   * 流程描述
   */
  private String describe;
  /**
   * 流程定义
   */
  @NotNull(message = "流程定义[define]不能为空.")
  private JsonNode define;
  /**
   * 流程定义的全局属性
   */
  private JsonNode attributes;

  DefineRequest withId(Long id) {
    return new DefineRequest(id, this.tenantId, this.name, this.category, this.describe,
        this.define, this.attributes);
  }

  @Override
  public boolean isNew() {
    return this.getId() == null || this.getId() == 0;
  }
}