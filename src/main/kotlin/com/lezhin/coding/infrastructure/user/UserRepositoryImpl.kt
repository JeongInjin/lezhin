package com.lezhin.coding.infrastructure.user

import com.lezhin.coding.domain.QHistory.history
import com.lezhin.coding.domain.QUser.user
import com.lezhin.coding.domain.QWork.work
import com.lezhin.coding.domain.User
import com.lezhin.coding.domain.constant.WorkType.ADULT_NOVEL
import com.lezhin.coding.domain.constant.WorkType.ADULT_WEBTOON
import com.lezhin.coding.domain.dto.user.UserResponse
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UserRepositoryImpl(private val queryFactory: JPAQueryFactory) : QuerydslRepositorySupport(User::class.java),
    UserRepositorySupport {

    override fun getUsersWhoViewedAdultWorks(): List<UserResponse>? {
        val oneWeekAgo = LocalDateTime.now().minusWeeks(1)

        val adultWorkIds =
            queryFactory.select(work.id).from(work).where(work.workType.`in`(ADULT_WEBTOON, ADULT_NOVEL)).fetch()

        val users = queryFactory.select(user).from(user).join(history).on(user.eq(history.user))
            .where(user.createdAt.goe(oneWeekAgo), history.work.id.`in`(adultWorkIds)).groupBy(user.id)
            .having(history.count().goe(3)).fetch()

        return users.map { UserResponse.from(it) }
    }
}
