package com.lezhin.coding.infrastructure.user

import com.lezhin.coding.domain.dto.user.UserResponse
import org.h2.engine.User

interface UserRepositorySupport {
    fun getUsersWhoViewedAdultWorks(): List<UserResponse>?
}