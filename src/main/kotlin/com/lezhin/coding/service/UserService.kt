package com.lezhin.coding.service

import com.lezhin.coding.common.exception.BusinessException
import com.lezhin.coding.domain.dto.user.UserResponse
import com.lezhin.coding.infrastructure.user.UserRepository
import com.me.init.initialproject.example.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(private val userRepository: UserRepository) {

    fun getUsersWhoViewedAdultWorks(): List<UserResponse>? {
        return userRepository.getUsersWhoViewedAdultWorks()
    }

    @Transactional
    fun deleteUserAndRelatedData(userId: Long) {
        val user = userRepository.findById(userId).orElseThrow {
            throw BusinessException(ErrorCode.USER_NOT_FOUND.message)
        }
        userRepository.deleteUserById(user.id!!)
    }

}
