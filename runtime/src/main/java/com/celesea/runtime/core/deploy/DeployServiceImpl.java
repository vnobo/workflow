package com.celesea.runtime.core.deploy;

import com.celesea.common.request.DeployRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.util.io.StringStreamSource;
import org.activiti.engine.repository.Deployment;
import org.activiti.validation.ValidationError;
import org.springframework.stereotype.Service;

/**
 * com.celesea.activiti.core.deploy.DeployServiceImpl
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/26
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class DeployServiceImpl implements DeployService {

  private final RepositoryService repositoryService;

  private final BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();

  @Override
  public String loadBpmnXml(String processDefinitionId) {
    BpmnModel bpmnModel = this.repositoryService.getBpmnModel(processDefinitionId);
    return new String(this.bpmnXMLConverter.convertToXML(bpmnModel));
  }

  @Override
  public Deployment deployment(DeployRequest deployRequest) {

    StringStreamSource xmlStreamReader = new StringStreamSource(deployRequest.getModel());

    BpmnModel bpmnModel = this.bpmnXMLConverter.convertToBpmnModel(xmlStreamReader,
        true, true);

    this.validationModel(bpmnModel);

    return this.deployment(deployRequest, bpmnModel);

  }

  private void validationModel(BpmnModel bpmnModel) {
    List<ValidationError> errors = this.repositoryService.validateProcess(bpmnModel);
    if (errors.size() > 0) {
      throw DeployValidationException.withDefault()
          .errors(errors);
    }

  }

  private Deployment deployment(DeployRequest deployRequest, BpmnModel bpmnModel) {
    return this.repositoryService.createDeployment()
        .tenantId(deployRequest.getTenantId())
        .key(deployRequest.getKey())
        .name(deployRequest.getName())
        .category(deployRequest.getCategory())
        .addBpmnModel(deployRequest.getKey() + ".bpmn20.xml", bpmnModel)
        .deploy();
  }

}