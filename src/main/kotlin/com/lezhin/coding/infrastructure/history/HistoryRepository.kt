package com.lezhin.coding.infrastructure.history

import com.lezhin.coding.domain.History
import com.lezhin.coding.domain.constant.ActionType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HistoryRepository : JpaRepository<History, Long> {
    fun findAllByWorkIdAndActionTypeOrderByCreatedAtDesc(workId: Long, actionType: ActionType): List<History>
}

