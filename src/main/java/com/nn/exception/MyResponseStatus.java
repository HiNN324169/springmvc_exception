package com.nn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "数组越界22！！！")
public class MyResponseStatus extends Exception { // 自定义异常
    static final long serialVersionUID = -7034897190745788931L;
    public MyResponseStatus() {
        super();
    }


    public MyResponseStatus(String message) {
        super(message);
    }

    public MyResponseStatus(String message, Throwable cause) {
        super(message, cause);
    }


    public MyResponseStatus(Throwable cause) {
        super(cause);
    }


    protected MyResponseStatus(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
