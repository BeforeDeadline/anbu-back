package com.beforedeadline.anbuback.domain.common.exception;

public class DuplicateDataException extends RuntimeException{
    public DuplicateDataException(String field, String value) {
        super(field + "에 중복된 값이 존재합니다. 입력값 : " + value);
    }
}
