package com.lezhin.coding.service

import com.lezhin.coding.common.exception.BusinessException
import com.lezhin.coding.domain.Work
import com.lezhin.coding.infrastructure.work.WorkRepository
import com.me.init.initialproject.example.exception.ErrorCode.INVALID_INPUT_VALUE
import com.me.init.initialproject.example.exception.ErrorCode.WORK_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WorkService(private val workRepository: WorkRepository) {

    fun getMostLikedWorks(): List<Work> {
        return workRepository.findTop3ByOrderByLikeCountDesc().take(3)
    }

    fun getMostDislikedWorks(): List<Work> {
        return workRepository.findTop3ByOrderByDislikeCountDesc().take(3)
    }

    @Transactional
    fun changeWorkAmount(workId: Long, amount: Int): Work? {

        // 음수이거나, 0 ~ 100 사이의 금액이거나, 500 초과일 경우
        if (amount < 0 || (amount in 1..99) || amount > 500) {
            throw BusinessException(INVALID_INPUT_VALUE.message)
        }

        val work = workRepository.findById(workId).orElseThrow { throw BusinessException(WORK_NOT_FOUND.message) }
        work.changeCoinAmount(amount)

        return work
    }
}
