package com.beforedeadline.anbuback.domain.account.exception;

public class WrongOwnerException extends RuntimeException{

    public WrongOwnerException(String field) {
        super("해당 account 소유의 " + field + "를 입력해주세요.");
    }
}
