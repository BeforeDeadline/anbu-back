package com.beforedeadline.anbuback.domain.common.exception;

public class NotFoundDataException extends RuntimeException{

    public NotFoundDataException(String field, String value) {
        super(field + "에 대해 값을 찾을 수 없습니다. 입력값 : " + value);
    }
}
