package com.lezhin.coding.controller

import com.lezhin.coding.common.support.ApiResponse
import com.lezhin.coding.domain.dto.Evaluation.EvaluationRequest
import com.lezhin.coding.domain.dto.Evaluation.EvaluationResponse
import com.lezhin.coding.service.EvaluationService
import jdk.jfr.Description
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/{userId}/evaluations")
class EvaluationController(private val evaluationService: EvaluationService) {

    @Description("평가 만들기")
    @PostMapping
    fun createEvaluation(
        @PathVariable userId: Long, @RequestBody request: EvaluationRequest
    ): ResponseEntity<Any> {
        val evaluation = evaluationService.createEvaluation(userId, request)
        val response = EvaluationResponse.from(evaluation)
        val apiResponse = ApiResponse(true, null, response)
        return ResponseEntity.ok(apiResponse)
    }
}
