package org.example.orderapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST,"존재하지 않는 회원입니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
