package com.celesea.runtime.core.deploy;

import com.celesea.common.request.DeployRequest;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.repository.Deployment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.celesea.activiti.core.deploy.DeployController
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/26
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("deploy")
public class DeployController {

  private final DeployService deployService;

  @GetMapping("xml/{processDefinitionId}")
  public Callable<String> loadBpmnXml(@PathVariable String processDefinitionId) {
    return () -> this.deployService.loadBpmnXml(processDefinitionId);
  }


  @PostMapping("deployment")
  public Callable<Deployment> deployment(@RequestBody DeployRequest deployRequest) {
    return () -> this.deployService.deployment(deployRequest);
  }
}