package com.celesea.engine.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

/**
 * com.celesea.engine.core.BaseService
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/27
 */
public abstract class BaseServiceImpl {

  protected RuntimeClient runtimeClient;
  protected ObjectMapper objectMapper;
  protected R2dbcEntityTemplate r2dbcEntityTemplate;

  @Autowired
  public void setActivitiClient(RuntimeClient runtimeClient) {
    this.runtimeClient = runtimeClient;
  }

  @Autowired
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Autowired
  public void setR2dbcEntityTemplate(R2dbcEntityTemplate r2dbcEntityTemplate) {
    this.r2dbcEntityTemplate = r2dbcEntityTemplate;
  }
}