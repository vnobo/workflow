package com.celesea.engine.core;

import com.celesea.engine.annotation.EngineException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * com.celesea.engine.core.ActivitiClient
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/27
 */
@Log4j2
@Service
public class RuntimeClient {

  private final WebClient webClient;

  public RuntimeClient(WebClient.Builder builder,
      ReactorLoadBalancerExchangeFilterFunction filterFunction) {
    this.webClient = builder
        .baseUrl("Http://celesea-runtime")
        .defaultHeaders(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
        .filter(filterFunction)
        .build();
  }

  public Mono<JsonNode> post(String path, Object body) {
    return this.webClient.post()
        .uri(uriBuilder -> uriBuilder.path(path).build())
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .exchangeToMono(this::handlerRequestResult);
  }

  private Mono<JsonNode> handlerRequestResult(ClientResponse response) {

    if (response.statusCode().isError()) {
      return response.bodyToMono(JsonNode.class)
          .doOnNext(errNode -> log.error("请求错误,CODE:[{}],信息: [{}]",
              response.statusCode(), errNode))
          .flatMap(errNode -> Mono.error(EngineException.withMsg(response.statusCode(), errNode)));

    }
    return response.bodyToMono(JsonNode.class);
  }
}