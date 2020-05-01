package com.project.selflearningplatformserver.exception;

import org.springframework.http.HttpStatus;

/**
 * 非法参数
 *
 * @author itning
 * @date 2020/2/12 11:34
 */
public class IllegalFiledException extends BaseException {
    public IllegalFiledException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST);
    }
}
