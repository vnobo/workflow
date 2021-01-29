package com.celesea.engine.core;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * com.celesea.engine.core.FlowDefineConverter
 * <p>
 * 将非流程引擎的定义JSON,转换成标准流程引擎
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/28
 */
public interface FlowDefineConverter {

  /**
   * 自定义流程转换成标准流程XML  =
   *
   * @param source 自定义流程
   * @return 转后XML文档
   */
  String convertXml(JsonNode source);

  /**
   * xml 转换自定义流程
   *
   * @param xml 流程标准XML
   * @return 自定义流程
   */
  JsonNode convertJson(String xml);
}