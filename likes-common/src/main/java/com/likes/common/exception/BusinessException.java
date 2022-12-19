package com.likes.common.exception;

import com.likes.common.enums.StatusCode;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3876502758804606346L;

    private String code = StatusCode.SERVER_ERROR.getCode();

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(StatusCode statusCode) {
        super(statusCode.getValue());
        this.code = statusCode.getCode();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable th) {
        super(message, th);
    }

    public BusinessException(String code, String message, Throwable th) {
        super(message, th);
        this.code = code;
    }

    public BusinessException(Throwable th) {
        super(th);
    }

    public String getMessage() {
        return super.getMessage();
    }

    /**
     * @return the code
     */
    public String getCode() {
        if (this.code == null || "".equals(this.code.trim())) {
            return "-1";
        }
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}