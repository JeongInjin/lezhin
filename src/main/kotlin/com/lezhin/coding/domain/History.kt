package com.lezhin.coding.domain

import com.lezhin.coding.common.support.Auditing
import com.lezhin.coding.domain.constant.ActionType
import jakarta.persistence.*
import jdk.jfr.Description

@Entity
@Description("작품 이력 정보")
@Table(name = "history")
class History : Auditing() {
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

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION_TYPE", length = 20)
    @Description("유형")
    var actionType: ActionType? = null
}
