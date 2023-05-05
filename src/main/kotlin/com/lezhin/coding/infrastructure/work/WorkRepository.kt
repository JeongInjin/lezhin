package com.lezhin.coding.infrastructure.work

import com.lezhin.coding.domain.Work
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WorkRepository : JpaRepository<Work, Long>, WorkRepositorySupport {

}
