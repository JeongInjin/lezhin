package com.lezhin.coding.domain

import com.lezhin.coding.common.support.Auditing
import jakarta.persistence.*
import jdk.jfr.Description

@Entity
@Description("작품 평가 정보")
@Table(name = "evaluations")
class Evaluation : Auditing() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Description("아이디")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @Description("사용자")
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_ID")
    @Description("작품")
    var work: Work? = null

    @Description("좋아요/싫어요")
    @Column(name = "LIKE_DISLIKE")
    var likeDislike: Boolean? = null

    @Description("코멘트")
    @Column(name = "COMMENT", length = 255)
    var comment: String? = null

    fun setData(user: User, work: Work, likeDislike: Boolean, comment: String?) {
        this.user = user
        this.work = work
        this.likeDislike = likeDislike
        this.comment = comment
    }
}
