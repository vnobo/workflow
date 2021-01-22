package com.celesea.common.converter;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;

/**
 * mspbots.cw.data.converter.HttpStatusReadConverter
 *
 * @author <a href="https://github.com/vnobo">Alex bob</a>
 * @date Created by 2020/11/19
 */
@ReadingConverter
public class HttpStatusReadConverter implements Converter<Integer, HttpStatus> {

    @Override
    public HttpStatus convert(Integer source) {
        return HttpStatus.valueOf(source);
    }
}
