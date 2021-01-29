package com.celesea.engine.core.define;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;
import lombok.Value;

/**
 * com.celesea.engine.core.define.DefineOnly
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/29
 */
@Value
public class FlowDefineOnly {

  Long id;

  String tenantId, key, name, category, describe;

  Integer version;

  JsonNode define, attributes;

  LocalDateTime createdTime, updatedTime;

  static FlowDefineOnly convert(FlowDefine flowDefine) {
    return new FlowDefineOnly(flowDefine.getId(), flowDefine.getTenantId(), flowDefine.getKey(),
        flowDefine.getName(), flowDefine.getCategory(), flowDefine.getDescribe(),
        flowDefine.getVersion(), flowDefine.getDefine(),
        flowDefine.getAttributes(), flowDefine.getCreatedTime(), flowDefine.getUpdatedTime());
  }

}