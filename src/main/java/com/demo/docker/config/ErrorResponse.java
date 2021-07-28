package com.demo.docker.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class ErrorResponse {

    String exceptionType;
    String message;

    static ErrorResponse from(Exception e) {
        return new ErrorResponse(e.getClass()
            .getName(), e.getMessage());
    }

}
