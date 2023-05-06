package com.lezhin.coding.service

import com.lezhin.coding.common.exception.BusinessException
import com.lezhin.coding.domain.Work
import com.lezhin.coding.domain.constant.WorkType.*
import com.lezhin.coding.infrastructure.work.WorkRepository
import com.me.init.initialproject.example.exception.ErrorCode.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WorkServiceTest(
    @Autowired private val workRepository: WorkRepository,
    @Autowired private val workService: WorkService
) {

    @BeforeEach
    fun setUp() {
        val works = (1..10).map {
            Work().apply {
                contentsName = "Work $it"
                author = "Author $it"
                workType = WEBTOON
                coinAmount = it * 100
                openDate = LocalDateTime.now()
                likeCount = it * 10
                dislikeCount = it * 5
            }
        }
        workRepository.saveAll(works)
    }

    @AfterEach
    fun afterDown() {
        workRepository.deleteAll()
    }

    @Test
    fun `좋아요 top 3 를 반환한다`() {
        // when
        val mostLikedWorks = workService.getMostLikedWorks()

        // then
        assertThat(mostLikedWorks).hasSize(3)
        assertThat(mostLikedWorks).extracting("likeCount").containsExactly(100, 90, 80)
    }

    @Test
    fun `싫어요 top 3 를 반환한다`() {
        // when
        val mostDislikedWorks = workService.getMostDislikedWorks()

        // then
        assertThat(mostDislikedWorks).hasSize(3)
        assertThat(mostDislikedWorks).extracting("dislikeCount").containsExactly(50, 45, 40)
    }

    @Test
    fun `직품 금액 올바른 변경`() {
        // given
        val workId = 1L
        val amount = 300
        val expectedAmount = 300

        // when
        val result = workService.changeWorkAmount(workId, amount)

        // then
        assertThat(result).isNotNull
        assertThat(result?.coinAmount).isEqualTo(expectedAmount)
    }

    @Test
    fun `작품 금액은 음수여선 안된다`() {
        // given
        val workId = 1L
        val amount = -100

        // when & then
        assertThatThrownBy { workService.changeWorkAmount(workId, amount) }.isInstanceOf(BusinessException::class.java)
            .hasMessage(INVALID_INPUT_VALUE.message)
    }

    @Test
    fun `작품 금액은 0 ~ 100 사이여서도 안된다`() {
        // given
        val workId = 1L
        val amount = 50

        // when & then
        assertThatThrownBy { workService.changeWorkAmount(workId, amount) }.isInstanceOf(BusinessException::class.java)
            .hasMessage(INVALID_INPUT_VALUE.message)
    }

    @Test
    fun `작품 금액은 500 을 초과해서 안된다`() {
        // given
        val workId = 1L
        val amount = 600

        // when & then
        assertThatThrownBy { workService.changeWorkAmount(workId, amount) }.isInstanceOf(BusinessException::class.java)
            .hasMessage(INVALID_INPUT_VALUE.message)
    }
}
