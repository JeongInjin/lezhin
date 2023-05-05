package com.lezhin.coding.controller

import com.lezhin.coding.common.support.ApiResponse
import com.lezhin.coding.domain.constant.ActionType
import com.lezhin.coding.service.HistoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/history")
class HistoryController(
    private val historyService: HistoryService,
) {

    @GetMapping("/{workId}/{actionType}")
    fun getHistoryByWorkIdAndActionType(@PathVariable("workId") workId: Long, @PathVariable("actionType") actionType: ActionType): ResponseEntity<Any> {
        val historyResponses = historyService.getHistoryByWorkIdAndActionType(workId, actionType)
        return ResponseEntity.ok(ApiResponse(true, null, historyResponses))
    }
}
