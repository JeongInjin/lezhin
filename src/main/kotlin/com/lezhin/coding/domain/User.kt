package com.lezhin.coding.domain

import com.lezhin.coding.common.support.Auditing
import com.lezhin.coding.domain.constant.Gender
import com.lezhin.coding.domain.constant.UserType
import jakarta.persistence.*
import jdk.jfr.Description

@Entity
@Description("사용자 정보")
@Table(name = "users")
class User : Auditing() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Description("아이디")
    var id: Long? = null

    @Description("사용자 이름")
    @Column(name = "USER_NAME", length = 255)
    var userName: String? = null

    @Description("사용자 이메일")
    @Column(name = "USER_EMAIL", length = 255)
    var userEmail: String? = null

    @Enumerated(EnumType.STRING)
    @Description("성별")
    @Column(name = "GENDER", length = 10)
    var gender: Gender? = null

    @Enumerated(EnumType.STRING)
    @Description("사용자 유형")
    @Column(name = "USER_TYPE", length = 10)
    var userType: UserType? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var evaluations: MutableList<Evaluation> = mutableListOf()

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var histories: MutableList<History> = mutableListOf()
}
