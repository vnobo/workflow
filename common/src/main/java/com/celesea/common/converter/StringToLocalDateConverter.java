package com.celesea.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * mspbots.cw.common.converter.StringToLocalDateConverter
 *
 * @author <a href="https://github.com/vnobo">Alex bob</a>
 * @date Created by 2020/11/19
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
