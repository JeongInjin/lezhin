package com.lezhin.coding.controller

import com.lezhin.coding.common.support.ApiResponse
import com.lezhin.coding.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/adult-work-viewers")
    fun getUsersWhoViewedAdultWorks(): ResponseEntity<Any> {
        val users = userService.getUsersWhoViewedAdultWorks()
        val apiResponse = ApiResponse(true, null, users)
        return ResponseEntity.ok(apiResponse)
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Any> {
        userService.deleteUserAndRelatedData(userId)
        val apiResponse = ApiResponse(true, null, null)
        return ResponseEntity.ok(apiResponse)
    }
}
