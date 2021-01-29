package com.celesea.runtime.core.deploy;

import com.celesea.common.request.DeployRequest;
import org.activiti.engine.repository.Deployment;

/**
 * com.celesea.runtime.core.deploy.DeployService
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/29
 */
public interface DeployService {

  /**
   * 获取bpmn xml
   *
   * @param processDefinitionId 流程定义ID
   * @return 流程BPMN xml
   */
  String loadBpmnXml(String processDefinitionId);

  /**
   * 发布流程,定义
   *
   * @param deployRequest 发布需要参数
   * @return 发布后的实体信息
   */
  Deployment deployment(DeployRequest deployRequest);

}