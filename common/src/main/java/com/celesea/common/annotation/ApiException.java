package com.celesea.common.annotation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * mspbots.data.cw.core.annotation.AutoTaskApiException
 *
 * @author <a href="https://github.com/vnobo">Alex bob</a>
 * @date Created by 2020/7/22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException implements Serializable {

    private Object msg;
    private HttpStatus status;

    public ApiException(HttpStatus status, Object msg) {
        super(msg.toString());
        this.msg = msg;
        this.status = status;
    }

    public static ApiException withMsg(HttpStatus status, Object msg) {
        return new ApiException(status, msg);
    }
}
