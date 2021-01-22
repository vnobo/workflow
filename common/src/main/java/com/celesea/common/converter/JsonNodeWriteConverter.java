package com.celesea.common.converter;

import com.fasterxml.jackson.databind.JsonNode;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * com.mspbots.web.converter.JsonNodeWriteConverter
 *
 * @author Alex bob(https://github.com/vnobo)
 * @date Created by 2019/12/27
 */
@WritingConverter
public class JsonNodeWriteConverter implements Converter<JsonNode, Json> {

    @Override
    public Json convert(JsonNode source) {
        return Json.of(source.toString());
    }
}
