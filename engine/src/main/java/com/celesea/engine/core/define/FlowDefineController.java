package com.celesea.engine.core.define;

import com.celesea.engine.core.BaseController;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * com.celesea.engine.core.flow.FlowController
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/18
 */
@RestController
@RequestMapping("define")
@RequiredArgsConstructor
public class FlowDefineController extends BaseController {

  private final FlowDefineService defineService;

  @PostMapping
  public Mono<FlowDefineOnly> save(@Valid @RequestBody DefineRequest defineRequest) {
    return this.defineService.saveOrUpdate(defineRequest)
        .map(FlowDefineOnly::convert);
  }

}