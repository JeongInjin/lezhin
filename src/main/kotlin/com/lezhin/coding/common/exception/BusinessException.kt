package com.lezhin.coding.common.exception

import com.me.init.initialproject.example.exception.ErrorCode
import java.lang.RuntimeException

class BusinessException : RuntimeException {

    var errorCode: ErrorCode? = null
        private set

    constructor(message: String) : super(message)
}