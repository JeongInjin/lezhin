package com.lezhin.coding.domain.dto.user

import com.lezhin.coding.domain.User
import com.lezhin.coding.domain.constant.Gender
import com.lezhin.coding.domain.constant.Gender.*
import com.lezhin.coding.domain.constant.UserType
import com.lezhin.coding.domain.constant.UserType.*

data class UserResponse(
    val id: Long,
    val userName: String,
    val userEmail: String,
    val gender: Gender,
    val userType: UserType,
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                userName = user.userName ?: "",
                userEmail = user.userEmail ?: "",
                gender = user.gender ?: UNKNOWN_GENDER,
                userType = user.userType ?: UNKNOWN_USERTYPE,
            )
        }
    }
}
