package com.celesea.engine.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * com.celesea.sync.config.RabbitConfigurer
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2019/7/29
 */
@Configuration
public class RabbitConfiguration {

    @Bean
    public MessageConverter rabbitMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
