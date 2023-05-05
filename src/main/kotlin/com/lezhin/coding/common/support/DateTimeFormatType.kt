package com.lezhin.coding.common.support

class DateTimeFormatType(val description: String) {
    companion object {
        const val DEFAULT_PATTERN ="yyyy-MM-dd'T'HH:mm:ss"
        const val DEFAULT_KO_PATTERN ="yyyy년 MM월 dd일 HH:mm"
    }
}