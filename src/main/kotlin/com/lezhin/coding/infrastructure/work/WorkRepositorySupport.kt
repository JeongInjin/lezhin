package com.lezhin.coding.infrastructure.work

import com.lezhin.coding.domain.Work

interface WorkRepositorySupport {

    fun findTop3ByOrderByLikeCountDesc(): List<Work>

    fun findTop3ByOrderByDislikeCountDesc(): List<Work>
}