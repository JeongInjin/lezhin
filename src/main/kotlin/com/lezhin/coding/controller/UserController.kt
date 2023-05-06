package com.lezhin.coding.controller

import com.lezhin.coding.common.support.ApiResponse
import com.lezhin.coding.service.UserService
import jdk.jfr.Description
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @Description("최근 1주일간 등록 사용자 중 성인작품 3개 이상 조회한 사용자 목록")
    @GetMapping("/adult-work-viewers")
    fun getUsersWhoViewedAdultWorks(): ResponseEntity<Any> {
        val users = userService.getUsersWhoViewedAdultWorks()
        val apiResponse = ApiResponse(true, null, users)
        return ResponseEntity.ok(apiResponse)
    }

    @Description("특정 사용자 삭제 시 해당 사용자 정보와 사용자의 평가, 조회 이력 모두 삭제하는 API")
    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Any> {
        userService.deleteUserAndRelatedData(userId)
        val apiResponse = ApiResponse(true, null, null)
        return ResponseEntity.ok(apiResponse)
    }
}
