package com.celesea.engine.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.lang.Nullable;

/**
 * com.mspbots.web.converter.JsonNodeReadConverter
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2019/12/27
 */
@Log4j2
@ReadingConverter
public class JsonNodeReadConverter implements Converter<Json, JsonNode> {

  @Nullable
  @Override
  public JsonNode convert(Json source) {
    try {
      return new ObjectMapper().readValue(source.asString(), JsonNode.class);
    } catch (JsonProcessingException e) {
      log.error("reade jsonb to json node is error,msg:{}", e.getMessage());
    }
    return null;
  }

}