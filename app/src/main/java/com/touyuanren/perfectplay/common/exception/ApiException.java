package com.touyuanren.perfectplay.common.exception;

/**
 * Created by Liang on 2017/10/11 0011.
 */

public class ApiException extends BaseException {
    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
