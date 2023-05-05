package com.lezhin.coding.service

import com.lezhin.coding.domain.Evaluation
import com.lezhin.coding.domain.dto.Evaluation.EvaluationRequest
import com.lezhin.coding.common.exception.BusinessException
import com.lezhin.coding.infrastructure.evaluation.EvaluationRepository
import com.lezhin.coding.infrastructure.user.UserRepository
import com.lezhin.coding.infrastructure.work.WorkRepository
import com.me.init.initialproject.example.exception.ErrorCode.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class EvaluationService(
    private val evaluationRepository: EvaluationRepository,
    private val userRepository: UserRepository,
    private val workRepository: WorkRepository
) {

    @Transactional
    fun createEvaluation(userId: Long, request: EvaluationRequest): Evaluation {
        val user = userRepository.findById(userId).orElseThrow {
            throw BusinessException(USER_NOT_FOUND.message)
        }
        val work = workRepository.findById(request.workId).orElseThrow {
            throw BusinessException(WORK_NOT_FOUND.message)
        }
        if (evaluationRepository.existsByUserAndWork(user, work)) {
            throw BusinessException(DUPLICATE_EVALUATION.message)
        }

        val evaluation = Evaluation()
        evaluation.setData(user, work, request.likeDislike, request.comment)
        // 작품 등록 후 좋아요/싫어요 count update
        work.likeDislikeCountUpdate(request.likeDislike)

        return evaluation
    }
}