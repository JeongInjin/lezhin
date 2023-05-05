package com.me.init.initialproject.example.exception

import java.util.stream.Collectors
import org.springframework.validation.BindingResult
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.ArrayList

class ErrorResponse {

    var status: Int = 0
    var code: String? = null
    var message: String? = null
    var errors: List<FieldError>? = null


    private constructor(code: ErrorCode, errors: List<FieldError>) {
        this.status = code.status.value()
        this.code = code.code
        this.message = code.message
        this.errors = errors
    }

    private constructor(code: ErrorCode) {
        this.status = code.status.value()
        this.code = code.code
        this.message = code.message
        this.errors = ArrayList()
    }

    private constructor(code: ErrorCode, message: String?) {
        this.status = code.status.value()
        this.code = code.code
        this.message = message
        this.errors = ArrayList()
    }

    data class FieldError(var field: String, var reason: String) {

        companion object {

            fun of(field: String, reason: String): List<FieldError> {
                val fieldErrors = ArrayList<FieldError>()
                fieldErrors.add(FieldError(field, reason))
                return fieldErrors
            }

            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrors = bindingResult.fieldErrors
                return fieldErrors.stream()
                        .map { error ->
                            FieldError(
                                    error.field,
                                    error.defaultMessage!!)
                        }.collect(Collectors.toList())
            }
        }
    }

    companion object {

        fun of(code: ErrorCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(code, FieldError.of(bindingResult))
        }

        fun of(code: ErrorCode): ErrorResponse {
            return ErrorResponse(code)
        }

        fun of(code: ErrorCode, errors: List<FieldError>): ErrorResponse {
            return ErrorResponse(code, errors)
        }

        fun of(e: MethodArgumentTypeMismatchException): ErrorResponse {
            // val value = if (e.value == null) "" else e.value.toString()
            val errors = ErrorResponse.FieldError.of(e.name, e.errorCode)
            return ErrorResponse(ErrorCode.INVALID_TYPE_VALUE, errors)
        }

        fun of(code: ErrorCode, message: String?): ErrorResponse {
            return ErrorResponse(code, message)
        }
    }


}