package com.celesea.engine.core.define;

import com.celesea.common.request.DeployRequest;
import com.celesea.common.utils.FlowDefineUtils;
import com.celesea.engine.core.BaseServiceImpl;
import com.celesea.engine.core.FlowDefineConverter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


/**
 * com.celesea.engine.flow.define.FlowDefineServiceImpl
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/20
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FlowDefineServiceImpl extends BaseServiceImpl implements FlowDefineService {

  private final FlowDefineRepository defineRepository;
  private final FlowDefineConverter defineConverter;

  @Override
  public Mono<FlowDefine> saveOrUpdate(DefineRequest defineRequest) {

    Mono<FlowDefine> defineMono = defineRequest.isNew() ? Mono
        .just(FlowDefine.builder().key(FlowDefineUtils.randomKey(defineRequest.getName()))
            .build()) : this.defineRepository.findById(defineRequest.getId());

    return defineMono.map(flowDefine -> {
      BeanUtils.copyProperties(defineRequest, flowDefine);

      ObjectNode defineNode = flowDefine.getDefine().deepCopy();
      defineNode.put("key", flowDefine.getKey());
      flowDefine.setDefine(defineNode);

      flowDefine.setXml(this.defineConverter.convertXml(defineNode));

      return flowDefine;

    }).flatMap(this::save);
  }


  private Mono<FlowDefine> save(FlowDefine flowDefine) {
    return super.runtimeClient.post("/deploy/deployment", DeployRequest.builder()
        .key(flowDefine.getKey()).name(flowDefine.getName())
        .category(flowDefine.getCategory()).tenantId(flowDefine.getTenantId())
        .model(flowDefine.getXml()).build())
        .flatMap(deployment -> {
          ObjectNode objectNode = deployment.deepCopy();
          objectNode.remove("resources");
          return this.defineRepository.save(flowDefine
              .version(deployment.findValue("version").asInt(0))
              .deployment(objectNode));
        });
  }


}