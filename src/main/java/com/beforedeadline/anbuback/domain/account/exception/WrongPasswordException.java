package com.beforedeadline.anbuback.domain.account.exception;

public class WrongPasswordException extends RuntimeException{

    public WrongPasswordException() {
        super("잘못된 패스워드를 입력하였습니다.");
    }
}
