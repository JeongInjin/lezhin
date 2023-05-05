package com.lezhin.coding.domain.dto.Evaluation

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class EvaluationRequest(
    @field:NotNull(message = "사용자 ID는 필수 입력 항목입니다.")
    val userId: Long,

    @field:NotNull(message = "작품 ID는 필수 입력 항목입니다.")
    val workId: Long,

    @field:NotNull(message = "좋아요/싫어요 값은 필수 입력 항목입니다.")
    val likeDislike: Boolean,

    @field:Size(max = 255, message = "댓글은 최대 255자여야 합니다.")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣 ]*\$",
        message = "댓글은 한글과 영어, 숫자 및 공백만 포함할 수 있습니다."
    )
    val comment: String?
)