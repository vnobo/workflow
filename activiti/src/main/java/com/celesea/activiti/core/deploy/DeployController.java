package com.celesea.activiti.core.deploy;

import lombok.RequiredArgsConstructor;
import org.activiti.engine.repository.Deployment;
import org.springframework.web.bind.annotation.GetMapping;
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

   private final DeployServiceImpl deployService;

    @GetMapping("xml")
    public String loadBpmnXml(){
        return this.deployService.loadBpmnXml();
    }


    @GetMapping("deployment")
    public Deployment deployment(){
        return this.deployService.deployment();
    }
}