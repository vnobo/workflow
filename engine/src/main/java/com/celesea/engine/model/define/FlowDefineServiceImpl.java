package com.celesea.engine.model.define;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * com.celesea.engine.flow.define.FlowDefineServiceImpl
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2021/1/20
 */
@Service
@RequiredArgsConstructor
public class FlowDefineServiceImpl implements FlowDefineService {

    private final FlowDefineRepository defineRepository;

}