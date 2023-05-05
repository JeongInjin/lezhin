package com.lezhin.coding.domain.dto.Evaluation

import com.lezhin.coding.domain.Evaluation
import java.time.LocalDateTime

data class EvaluationResponse(
    val id: Long, val userId: Long, // 평가를 한 사용자의 ID
    val workId: Long, // 평가된 작품의 ID
    val likeDislike: Boolean, // 평가
    val comment: String?, // 댓글
    val createdAt: LocalDateTime? // 생성시간
) {
    companion object {
        fun from(evaluation: Evaluation): EvaluationResponse {
            return EvaluationResponse(
                id = evaluation.id!!,
                userId = evaluation.user!!.id!!,
                workId = evaluation.work!!.id!!,
                likeDislike = evaluation.likeDislike!!,
                comment = evaluation.comment,
                createdAt = evaluation.createdAt
            )
        }
    }
}