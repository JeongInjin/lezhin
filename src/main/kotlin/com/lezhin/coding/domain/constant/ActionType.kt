package com.lezhin.coding.domain.constant

enum class ActionType(val description: String) {
    CREATE("생성"),
    READ("조회"),
    UPDATE("수정"),
    DELETE("삭제");
}