package com.celesea.engine.core;

import com.celesea.engine.annotation.EngineException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lombok.RequiredArgsConstructor;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.springframework.stereotype.Component;

/**
 * com.celesea.engine.converter.DefaultFlowDefineConverter
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/28
 */
@Component
@RequiredArgsConstructor
public class DefaultFlowDefineConverter implements FlowDefineConverter {

  private static final BpmnXMLConverter BPMN_XML_CONVERTER = new BpmnXMLConverter();

  private final ObjectMapper objectMapper;

  @Override
  public String convertXml(JsonNode source) {
    BpmnModel bpmnModel = this.initializeBpmnModel(source);
    byte[] xmlByte = BPMN_XML_CONVERTER.convertToXML(bpmnModel);
    return new String(xmlByte);
  }

  @Override
  public JsonNode convertJson(String xml) {
    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
      XMLStreamReader xmlStreamReader = xmlInputFactory
          .createXMLStreamReader(new StringReader(xml));
      BpmnModel bpmnModel = BPMN_XML_CONVERTER.convertToBpmnModel(xmlStreamReader);
      return this.objectMapper.convertValue(bpmnModel, JsonNode.class);
    } catch (XMLStreamException e) {
      throw new EngineException(e.getMessage());
    }
  }

  private BpmnModel initializeBpmnModel(JsonNode flowDefine) {

    StartEvent startEvent = new StartEvent();
    startEvent.setId("SESTART");
    startEvent.setName("流程开始");

    UserTask userTask = new UserTask();
    userTask.setId("UTCHECK");
    userTask.setName("用户审核");

    EndEvent endEvent = new EndEvent();
    endEvent.setId("EEEND");
    endEvent.setName("测试流程结束");

    List<SequenceFlow> from = new ArrayList<>();
    SequenceFlow s1 = new SequenceFlow();
    s1.setId("s1");
    s1.setName("s1");
    s1.setSourceRef("SESTART");
    s1.setTargetRef("UTCHECK");
    from.add(s1);

    List<SequenceFlow> to = new ArrayList<>();
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
    process.setName(flowDefine.get("name").asText());
    process.setId(flowDefine.get("key").asText());
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
}