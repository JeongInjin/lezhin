package com.lezhin.coding.controller

import com.lezhin.coding.common.support.ApiResponse
import com.lezhin.coding.domain.dto.work.WorkResponse
import com.lezhin.coding.service.WorkService
import jdk.jfr.Description
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/works")
class WorkController(private val workService: WorkService) {

    @Description("좋아요 top 3 조회")
    @GetMapping("/most-liked")
    fun getMostLikedWorks(): ResponseEntity<Any> {
        val works = workService.getMostLikedWorks()
        val response = works.map { WorkResponse.from(it) }
        return ResponseEntity.ok(ApiResponse(true, null, response))
    }

    @Description("싫어서 top 3 조회")
    @GetMapping("/most-disliked")
    fun getMostDislikedWorks(): ResponseEntity<Any> {
        val works = workService.getMostDislikedWorks()
        val response = works.map { WorkResponse.from(it) }
        return ResponseEntity.ok(ApiResponse(true, null, response))
    }

    @Description("작품의 금액을 변경함(무료, 유료(100~500))")
    @PostMapping("/{workId}/change")
    fun changeWork(@PathVariable workId: Long, @RequestParam(required = false, defaultValue = "0") amount: Int): ResponseEntity<Any> {
        val work = workService.changeWorkAmount(workId, amount)
        val response = work?.let { WorkResponse.from(it) }
        val apiResponse = ApiResponse(true, null, response)
        return ResponseEntity.ok(apiResponse)
    }
}
