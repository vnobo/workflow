package com.celesea.engine.config;


import com.celesea.common.converter.HttpStatusReadConverter;
import com.celesea.common.converter.HttpStatusWriteConverter;
import com.celesea.common.converter.JsonNodeReadConverter;
import com.celesea.common.converter.JsonNodeWriteConverter;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * com.mspbots.web.config.MongoConfiguration
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2019/9/30
 */
@Configuration
public class R2dbcConfiguration extends AbstractR2dbcConfiguration {

    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get("r2dbc:…");
    }

    @Override
    public List<Object> getCustomConverters() {
        List<Object> converterList = new ArrayList<>();
        converterList.add(new JsonNodeReadConverter());
        converterList.add(new JsonNodeWriteConverter());
        converterList.add(new HttpStatusReadConverter());
        converterList.add(new HttpStatusWriteConverter());
        return converterList;
    }


}
