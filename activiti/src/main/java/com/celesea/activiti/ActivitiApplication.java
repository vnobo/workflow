package com.celesea.activiti;

import org.activiti.api.runtime.conf.impl.ProcessModelAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * com.celesea.activiti.ActivitiApplication
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/22
 */
@SpringBootApplication(exclude = {ProcessModelAutoConfiguration.class})
public class ActivitiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }
}