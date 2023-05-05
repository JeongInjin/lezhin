package com.lezhin.coding.infrastructure.user

import com.lezhin.coding.domain.User
import com.lezhin.coding.domain.Work
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long>, UserRepositorySupport {

    fun findByUserEmail(userEmail: String): Optional<User>

    fun deleteUserById(id: Long)
}
