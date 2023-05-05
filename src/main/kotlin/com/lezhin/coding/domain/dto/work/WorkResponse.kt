package com.lezhin.coding.domain.dto.work

import com.lezhin.coding.domain.Work
import com.lezhin.coding.domain.constant.WorkType
import java.time.LocalDateTime

data class WorkResponse(
    val id: Long,
    val contentsName: String,
    val author: String,
    val workType: WorkType,
    val coinAmount: Int,
    val likeCount: Int,
    val dislikeCount: Int,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(work: Work): WorkResponse {
            return WorkResponse(
                id = work.id!!,
                contentsName = work.contentsName!!,
                author = work.author!!,
                workType = work.workType!!,
                coinAmount = work.coinAmount!!, // change type to Int
                likeCount = work.likeCount,
                dislikeCount = work.dislikeCount,
                createdAt = work.createdAt!!
            )
        }
    }
}
