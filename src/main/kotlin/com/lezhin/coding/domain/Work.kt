package com.lezhin.coding.domain

import com.lezhin.coding.common.support.Auditing
import com.lezhin.coding.domain.constant.WorkType
import jakarta.persistence.*
import jdk.jfr.Description
import java.time.LocalDateTime

@Entity
@Description("작품 정보")
@Table(name = "works")
class Work : Auditing() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Description("아이디")
    var id: Long? = null

    @Description("작품 제목")
    @Column(name = "CONTENTS_NAME", length = 255)
    var contentsName: String? = null

    @Description("작가")
    @Column(name = "AUTHOR", length = 255)
    var author: String? = null

    @Description("작품 유형")
    @Column(name = "WORK_TYPE", length = 255)
    @Enumerated(EnumType.STRING)
    var workType: WorkType? = null

    @Description("코인 가격")
    @Column(name = "COIN_AMOUNT")
    var coinAmount: Int? = 0

    @Description("공개일")
    @Column(name = "OPEN_DATE")
    var openDate: LocalDateTime? = null

    @Column(name = "좋아요 갯수")
    var likeCount: Int = 0

    @Column(name = "싫어요 갯수")
    var dislikeCount: Int = 0

    @OneToMany(mappedBy = "work", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var evaluations: MutableList<Evaluation> = mutableListOf()

    @OneToMany(mappedBy = "work", cascade = [CascadeType.ALL])
    var histories: MutableList<History> = mutableListOf()

    fun changeCoinAmount(coinAmount: Int?) {
        this.coinAmount = coinAmount
        this.updatedAt = LocalDateTime.now()
    }

    fun likeDislikeCountUpdate(likeDislike: Boolean) {
        if (likeDislike) {
            this.likeCount += 1
        } else {
            this.dislikeCount += 1
        }
    }
}
