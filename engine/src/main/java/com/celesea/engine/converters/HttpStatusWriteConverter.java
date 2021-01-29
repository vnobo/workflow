package com.celesea.engine.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.http.HttpStatus;

/**
 * mspbots.cw.data.converter.HttpStatusWriteConverter
 *
 * @author <a href="https://github.com/vnobo">Alex bob</a>
 * @date Created by 2020/11/19
 */
@WritingConverter
public class HttpStatusWriteConverter implements Converter<HttpStatus, Integer> {

  @Override
  public Integer convert(HttpStatus source) {
    return source.value();
  }

}