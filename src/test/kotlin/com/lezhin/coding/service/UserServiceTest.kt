package com.lezhin.coding.service

import com.lezhin.coding.common.exception.BusinessException
import com.lezhin.coding.domain.History
import com.lezhin.coding.domain.User
import com.lezhin.coding.domain.Work
import com.lezhin.coding.domain.constant.ActionType.*
import com.lezhin.coding.domain.constant.Gender.*
import com.lezhin.coding.domain.constant.UserType.*
import com.lezhin.coding.domain.constant.WorkType.ADULT_WEBTOON
import com.lezhin.coding.infrastructure.history.HistoryRepository
import com.lezhin.coding.infrastructure.user.UserRepository
import com.lezhin.coding.infrastructure.work.WorkRepository
import com.me.init.initialproject.example.exception.ErrorCode
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
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
class UserServiceTest(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val userService: UserService,
    @Autowired private val workRepository: WorkRepository,
    @Autowired private val historyRepository: HistoryRepository,
) {

    @BeforeEach
    fun setUp() {
        initData()
    }

    @AfterEach
    fun tearDown() {
        deleteAllData()
    }

    private fun deleteAllData() {
        historyRepository.deleteAll()
        workRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun `최근 1주일간 등록 사용자 중 성인작품 3개 이상 조회한 사용자 목록을 반환한다`() {
        // when
        val result = userService.getUsersWhoViewedAdultWorks()

        // then
        assertThat(result).hasSize(1)
        assertThat(result?.first()?.userName).isEqualTo("User1")
    }

    @Test
    fun `존재하지 않는 사용자를 삭제할 때 비즈니스 예외를 발생한다`() {
        // given
        val userId = 999L

        // when & then
        assertThatThrownBy { userService.deleteUserAndRelatedData(userId) }.isInstanceOf(BusinessException::class.java)
            .hasMessage(ErrorCode.USER_NOT_FOUND.message)
    }

    private fun initData() {
        deleteAllData()
        val user1 = userRepository.save(User().apply {
            userName = "User1"
            userEmail = "user1@lezhin.com"
            gender = FEMALE
            userType = ADULT
        })

        val user2 = userRepository.save(User().apply {
            userName = "User2"
            userEmail = "user2@lezhin.com"
            gender = MALE
            userType = ADULT
        })

        val work1 = Work().apply {
            contentsName = "Work1"
            author = "Author1"
            workType = ADULT_WEBTOON
            coinAmount = 100
            openDate = LocalDateTime.now()
            likeCount = 10
            dislikeCount = 5
        }
        workRepository.save(work1)

        val work2 = Work().apply {
            contentsName = "Work2"
            author = "Author2"
            workType = ADULT_WEBTOON
            coinAmount = 100
            openDate = LocalDateTime.now()
            likeCount = 10
            dislikeCount = 5
        }
        workRepository.save(work2)

        val work3 = Work().apply {
            contentsName = "Work3"
            author = "Author3"
            workType = ADULT_WEBTOON
            coinAmount = 100
            openDate = LocalDateTime.now()
            likeCount = 10
            dislikeCount = 5
        }
        workRepository.save(work3)

        val history1 = History().apply {
            this.user = user1
            this.work = work1
            actionType = READ
        }
        historyRepository.save(history1)

        val history2 = History().apply {
            this.user = user1
            this.work = work2
            actionType = READ
        }
        historyRepository.save(history2)

        val history3 = History().apply {
            this.user = user1
            this.work = work3
            actionType = READ
        }
        historyRepository.save(history3)
    }
}
