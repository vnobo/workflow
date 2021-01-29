package com.celesea.engine.core.define;

import reactor.core.publisher.Mono;

/**
 * com.celesea.engine.flow.define.FlowDefineService
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/20
 */
public interface FlowDefineService {

  /**
   * 流程保存或者修改
   * <p>
   * id>0 修改 ,id为空保存
   *
   * @param defineRequest 请求实体对象
   * @return 流程定义对象
   */
  Mono<FlowDefine> saveOrUpdate(DefineRequest defineRequest);
}