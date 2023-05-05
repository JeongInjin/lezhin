package com.lezhin.coding.infrastructure.work

import com.lezhin.coding.domain.QEvaluation.evaluation
import com.lezhin.coding.domain.QWork.work
import com.lezhin.coding.domain.Work
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class WorkRepositoryImpl(private val queryFactory: JPAQueryFactory) : QuerydslRepositorySupport(Work::class.java),
    WorkRepositorySupport {

    override fun findTop3ByOrderByLikeCountDesc(): List<Work> {
        return queryFactory.selectFrom(work).leftJoin(work.evaluations, evaluation).fetchJoin()
            .orderBy(work.likeCount.desc()).limit(3).fetch()
    }

    override fun findTop3ByOrderByDislikeCountDesc(): List<Work> {
        return queryFactory.selectFrom(work).leftJoin(work.evaluations, evaluation).fetchJoin()
            .orderBy(work.dislikeCount.desc()).limit(3).fetch()
    }
}
