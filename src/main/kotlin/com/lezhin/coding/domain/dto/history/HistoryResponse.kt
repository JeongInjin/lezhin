package com.lezhin.coding.domain.dto.history

import com.lezhin.coding.domain.History
import com.lezhin.coding.domain.constant.ActionType
import com.lezhin.coding.domain.constant.ActionType.*
import java.time.LocalDateTime

data class HistoryResponse(
    val id: Long,
    val userId: Long,
    val workId: Long,
    val actionType: ActionType,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(history: History): HistoryResponse {
            return HistoryResponse(
                id = history.id!!,
                userId = history.user!!.id!!,
                workId = history.work!!.id!!,
                actionType = history.actionType ?: READ,
                createdAt = history.createdAt!!
            )
        }
    }
}
