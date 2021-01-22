package com.celesea.engine.config;

import com.celesea.common.converter.StringToLocalDateConverter;
import com.celesea.common.converter.StringToLocalDateTimeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;


/**
 * data.connector.web.config.WebConfiguration
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2020/4/3
 */
@Configuration
@EnableScheduling
@Import({com.celesea.common.annotation.GlobalExceptionHandler.class})
public class AppConfiguration implements WebFluxConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateTimeConverter());
        registry.addConverter(new StringToLocalDateConverter());
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
    }

}
