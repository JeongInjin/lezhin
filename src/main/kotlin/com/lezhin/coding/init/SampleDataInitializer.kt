package com.lezhin.coding.init

import com.lezhin.coding.domain.Evaluation
import com.lezhin.coding.domain.History
import com.lezhin.coding.domain.User
import com.lezhin.coding.domain.Work
import com.lezhin.coding.domain.constant.*
import com.lezhin.coding.infrastructure.evaluation.EvaluationRepository
import com.lezhin.coding.infrastructure.history.HistoryRepository
import com.lezhin.coding.infrastructure.user.UserRepository
import com.lezhin.coding.infrastructure.work.WorkRepository
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import kotlin.random.Random

@Component
class SampleDataInitializer @Autowired constructor(
    private val userRepository: UserRepository,
    private val workRepository: WorkRepository,
    private val evaluationRepository: EvaluationRepository,
    private val historyRepository: HistoryRepository,
) {

    @PostConstruct
    fun init() {
        // 사용자 만들기
        for (i in 1..20) {
            var newEmail = "user$i@lezhin.com"
            if (userRepository.findByUserEmail(newEmail).isPresent) continue // Check for duplicates

            val user = User().apply {
                userName = "사용자$i"
                userEmail = newEmail
                gender = if (i % 2 == 0) Gender.MALE else Gender.FEMALE
                userType = if (i % 3 == 0) UserType.ADULT else UserType.GENERAL
            }
            userRepository.save(user)
        }

        // 작품 만들기
        for (i in 1..30) {
            val workType = when (Random.nextInt(4)) {
                0 -> WorkType.WEBTOON
                1 -> WorkType.NOVEL
                2 -> WorkType.ADULT_WEBTOON
                else -> WorkType.ADULT_NOVEL
            }

            val coinAmount = when (Random.nextInt(6)) {
                0 -> 0
                1 -> 100
                2 -> 200
                3 -> 300
                4 -> 400
                else -> 500
            }

            val work = Work().apply {
                contentsName = "works$i"
                author = "Author$i"
                this.workType = workType
                this.coinAmount = coinAmount
                openDate = LocalDateTime.now().minusDays(i.toLong())
                likeCount = Random.nextInt(0, 101)
                dislikeCount = Random.nextInt(0, 101)
            }
            workRepository.save(work)
        }

        // 평가 만들기
        val users = userRepository.findAll().toList()
        val works = workRepository.findAll().toList()
        for (i in 1..20) {
            val user = users.random()
            val work = works.random()

            if (evaluationRepository.existsByUserAndWork(user, work)) continue // Check for duplicates

            val evaluation = Evaluation().apply {
                this.user = user
                this.work = work
                likeDislike = i % 2 == 0
                comment = "평가 $i"
            }
            evaluationRepository.save(evaluation)
        }

        // 기록 만들기
        for (i in 1..200) {
            val user = users.random()
            val work = works.random()

            val history = History().apply {
                this.user = user
                this.work = work
                actionType = ActionType.values().random()
            }
            historyRepository.save(history)
        }
    }
}
