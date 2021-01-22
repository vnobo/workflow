package com.celesea.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * mspbots.cw.common.converter.StringToLocalDateTimeConverter
 *
 * @author <a href="https://github.com/vnobo">Alex bob</a>
 * @date Created by 2020/11/19
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        return LocalDateTime.parse(source, DateTimeFormatter.ISO_DATE_TIME);
    }
}
