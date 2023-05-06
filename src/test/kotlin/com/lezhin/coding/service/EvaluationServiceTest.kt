package com.lezhin.coding.service

import com.lezhin.coding.domain.User
import com.lezhin.coding.domain.Work
import com.lezhin.coding.domain.constant.Gender.*
import com.lezhin.coding.domain.constant.UserType
import com.lezhin.coding.domain.constant.WorkType.*
import com.lezhin.coding.domain.dto.Evaluation.EvaluationRequest
import com.lezhin.coding.infrastructure.evaluation.EvaluationRepository
import com.lezhin.coding.infrastructure.user.UserRepository
import com.lezhin.coding.infrastructure.work.WorkRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class EvaluationServiceTest(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val workRepository: WorkRepository,
    @Autowired private val evaluationRepository: EvaluationRepository,
    @Autowired private val evaluationService: EvaluationService,
) {

    @BeforeEach
    fun setUp() {
        workRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun `평가 생성`() {
        // given
        val user = userRepository.save(User().apply {
            userName = "User1"
            userEmail = "user1@lezhin.com"
            gender = FEMALE
            userType = UserType.ADULT
        })

        val work = workRepository.save(Work().apply {
            contentsName = "Work1"
            author = "Author1"
            workType = ADULT_WEBTOON
            coinAmount = 100
            openDate = LocalDateTime.now()
            likeCount = 10
            dislikeCount = 5
        })

        val evaluationRequest = EvaluationRequest(
            userId = user.id!!, workId = work.id!!, likeDislike = true, comment = "좋습니다"
        )

        // when
        val evaluation = evaluationService.createEvaluation(user.id!!, evaluationRequest)

        // then
        assertThat(evaluation.id).isNotNull()
        assertThat(evaluation.user).isEqualTo(user)
        assertThat(evaluation.work).isEqualTo(work)
        assertThat(evaluation.likeDislike).isEqualTo(true)
        assertThat(evaluation.comment).isEqualTo("좋습니다")

        val updatedWork = workRepository.findById(work.id!!).orElse(null)
        assertThat(updatedWork).isNotNull()
        assertThat(updatedWork.likeCount).isEqualTo(11)
        assertThat(updatedWork.dislikeCount).isEqualTo(5)
    }
}
