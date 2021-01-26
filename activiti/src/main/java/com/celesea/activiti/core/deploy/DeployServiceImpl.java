package com.celesea.activiti.core.deploy;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.validation.ValidationError;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.celesea.activiti.core.deploy.DeployServiceImpl
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/26
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class DeployServiceImpl {

    private final RepositoryService repositoryService;
    private final ProcessRuntime processRuntime;

    @EventListener(ApplicationReadyEvent.class)
    public void  test(){
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        log.info("> Available Process definitions: " + processDefinitionPage.getTotalItems());
        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
            log.info("\t > Process definition: " + pd);
        }
    }

    public String loadBpmnXml() {
        BpmnXMLConverter bpmnXmlConverter = new BpmnXMLConverter();
        BpmnModel bpmnModel = this.initializeBpmnModel();
        byte[] xmlModel = bpmnXmlConverter.convertToXML(bpmnModel);
        return new String(xmlModel);
    }

    public BpmnModel initializeBpmnModel() {

        StartEvent startEvent = new StartEvent();
        startEvent.setId("SESTART");
        startEvent.setName("流程开始");

        UserTask userTask = new UserTask();
        userTask.setId("UTCHECK");
        userTask.setName("用户审核");

        EndEvent endEvent = new EndEvent();
        endEvent.setId("EEEND");
        endEvent.setName("测试流程结束");

        List<SequenceFlow> from = new ArrayList<SequenceFlow>();
        SequenceFlow s1 = new SequenceFlow();
        s1.setId("s1");
        s1.setName("s1");
        s1.setSourceRef("SESTART");
        s1.setTargetRef("UTCHECK");
        from.add(s1);

        List<SequenceFlow> to = new ArrayList<SequenceFlow>();
        SequenceFlow s2 = new SequenceFlow();
        s2.setName("s2");
        s2.setId("s2");
        s2.setSourceRef("UTCHECK");
        s2.setTargetRef("EEEND");
        to.add(s2);

        startEvent.setOutgoingFlows(from);
        userTask.setIncomingFlows(from);
        userTask.setOutgoingFlows(to);
        endEvent.setIncomingFlows(to);

        Process process = new Process();
        process.setName("测试流程");
        process.setId("WF20210126");
        process.addFlowElement(startEvent);
        process.addFlowElement(s1);
        process.addFlowElement(userTask);
        process.addFlowElement(endEvent);
        process.addFlowElement(s2);

        BpmnModel bpmnModel = new BpmnModel();
        bpmnModel.setTargetNamespace("http://bpmn.io/schema/bpmn");
        bpmnModel.addProcess(process);
        return bpmnModel;
    }

    public Deployment deployment() {
        return this.deployment("TestDeployment", this.initializeBpmnModel());
    }

    private Deployment deployment(String name, BpmnModel bpmnModel) {

       List<ValidationError> errors =repositoryService.validateProcess(bpmnModel);
       if(errors.size()>0) {
           throw new ExpressionException(errors.parallelStream().map(ValidationError::getDefaultDescription)
                   .collect(Collectors.joining()));
       }
        Deployment deployment= this.repositoryService.createDeployment()
                .name("测试数据发布")
                .category(bpmnModel.getTargetNamespace())
                .addBpmnModel(name+".bpmn20.xml", bpmnModel)
                .deploy();
       return deployment;

    }
}