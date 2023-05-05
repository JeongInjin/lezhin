package com.lezhin.coding.infrastructure.evaluation

import com.lezhin.coding.domain.Evaluation
import com.lezhin.coding.domain.User
import com.lezhin.coding.domain.Work
import jdk.jfr.Description
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EvaluationRepository : JpaRepository<Evaluation, Long> {

    @Description("이미 평가한 작품인지")
    fun existsByUserAndWork(user: User, work: Work): Boolean
//
//    fun findByUserAndWork(user: User, work: Work): Optional<Evaluation>
//
//    fun findLikesByWork(work: Work): List<Evaluation>
//
//    fun findDislikesByWork(work: Work): List<Evaluation>
}

