package com.me.init.initialproject.example.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String
) {
    // common errors
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "E0001", "입력값이 올바르지 않습니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "E0002", "입력값의 타입이 올바르지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "E1003", "해당 사용자를 찾을 수 없습니다."),
    WORK_NOT_FOUND(HttpStatus.NOT_FOUND, "E1004", "해당 작품을 찾을 수 없습니다."),
    DUPLICATE_EVALUATION(HttpStatus.CONFLICT, "E1005", "해당 사용자와 작품에 대한 평가가 이미 존재합니다.")
}
