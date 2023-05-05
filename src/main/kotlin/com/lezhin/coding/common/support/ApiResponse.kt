package com.lezhin.coding.common.support

data class ApiResponse<T>(
    val success: Boolean? = false, val message: String? = null, val result: T
)