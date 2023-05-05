package com.lezhin.coding.service

import com.lezhin.coding.domain.constant.ActionType
import com.lezhin.coding.domain.dto.history.HistoryResponse
import com.lezhin.coding.infrastructure.history.HistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class HistoryService(
    private val historyRepository: HistoryRepository,
) {
    fun getHistoryByWorkIdAndActionType(workId: Long, actionType: ActionType): List<HistoryResponse> {
        val histories = historyRepository.findAllByWorkIdAndActionTypeOrderByCreatedAtDesc(workId, actionType)
        return histories.map { HistoryResponse.from(it) }
    }
}