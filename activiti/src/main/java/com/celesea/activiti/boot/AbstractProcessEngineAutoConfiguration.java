/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.celesea.activiti.boot;

import org.activiti.engine.*;
import org.activiti.engine.impl.persistence.entity.integration.IntegrationContextManager;
import org.activiti.engine.integration.IntegrationContextService;
import org.activiti.spring.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides sane definitions for the various beans required to be productive with Activiti in Spring.
 *
 */
public abstract class AbstractProcessEngineAutoConfiguration
        extends AbstractProcessEngineConfiguration {

  @Bean
  public SpringAsyncExecutor springAsyncExecutor(TaskExecutor applicationTaskExecutor) {
    return new SpringAsyncExecutor(applicationTaskExecutor, springRejectedJobsHandler());
  }

  @Bean
  public SpringRejectedJobsHandler springRejectedJobsHandler() {
    return new SpringCallerRunsRejectedJobsHandler();
  }

  protected Set<Class<?>> getCustomMybatisMapperClasses(List<String> customMyBatisMappers) {
    Set<Class<?>> mybatisMappers = new HashSet<>();
    for (String customMybatisMapperClassName : customMyBatisMappers) {
      try {
        Class customMybatisClass = Class.forName(customMybatisMapperClassName);
        mybatisMappers.add(customMybatisClass);
      } catch (ClassNotFoundException e) {
        throw new IllegalArgumentException("Class " + customMybatisMapperClassName + " has not been found.", e);
      }
    }
    return mybatisMappers;
  }

  @Bean
  public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration configuration) {
    return super.springProcessEngineBean(configuration);
  }

  @Bean
  @ConditionalOnMissingBean
  @Override
  public RuntimeService runtimeServiceBean(ProcessEngine processEngine) {
    return super.runtimeServiceBean(processEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  @Override
  public RepositoryService repositoryServiceBean(ProcessEngine processEngine) {
    return super.repositoryServiceBean(processEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  @Override
  public TaskService taskServiceBean(ProcessEngine processEngine) {
    return super.taskServiceBean(processEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  @Override
  public HistoryService historyServiceBean(ProcessEngine processEngine) {
    return super.historyServiceBean(processEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  @Override
  public ManagementService managementServiceBeanBean(ProcessEngine processEngine) {
    return super.managementServiceBeanBean(processEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskExecutor taskExecutor() {
    return new SimpleAsyncTaskExecutor();
  }

  @Bean
  @ConditionalOnMissingBean
  @Override
  public IntegrationContextManager integrationContextManagerBean(ProcessEngine processEngine) {
    return super.integrationContextManagerBean(processEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  @Override
  public IntegrationContextService integrationContextServiceBean(ProcessEngine processEngine) {
    return super.integrationContextServiceBean(processEngine);
  }
}