package com.celesea.engine.model;

import com.celesea.engine.model.define.FlowDefineRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;

/**
 * com.celesea.engine.core.flow.FlowService
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/18
 */
@Server
@RequiredArgsConstructor
public class FlowServiceImpl {

    private final FlowDefineRepository flowDefineRepository;

}
