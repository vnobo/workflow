package com.celesea.engine.config;

import com.celesea.engine.converters.StringToLocalDateConverter;
import com.celesea.engine.converters.StringToLocalDateTimeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;


/**
 * data.connector.web.config.WebConfiguration
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2020/4/3
 */
@Configuration
@EnableR2dbcAuditing
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