package com.xodud1202.kotlinapi.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TB_CUST")
data class Customer(
    @Id
    @Column(name = "CUST_ID", nullable = false)
    var custId: String,

    @Column(name = "CUST_NM")
    var custNm: String? = null,

    @Column(name = "EMAIL", nullable = false)
    var email: String,

    @Column(name = "PWD")
    var pwd: String? = null,

    @Column(name = "CELL_PHONE")
    var cellPhone: String? = null,

    @Column(name = "BIRTH_YYMMDD")
    var birthYymmdd: String? = null,

    @Column(name = "SEX_TYPE")
    var sexType: String? = null,

    @Column(name = "MAIL_RECV_YN", nullable = false)
    var mailRecvYn: String = "N",

    @Column(name = "MAIL_RECV_DT", nullable = false)
    var mailRecvDt: Date = Date(),

    @Column(name = "SMS_RECV_YN", nullable = false)
    var smsRecvYn: String = "N",

    @Column(name = "SMS_RECV_DT", nullable = false)
    var smsRecvDt: Date = Date(),

    @Column(name = "CUST_GRADE", nullable = false)
    var custGrade: String,

    @Column(name = "CUST_GRADE_UDT_DY")
    var custGradeUdtDy: String? = null,

    @Column(name = "CUST_GRADE_UDT_REASON")
    var custGradeUdtReason: String? = null,

    @Column(name = "JOIN_DT")
    var joinDt: Date? = null,

    @Column(name = "ACCESS_DT")
    var accessDt: Date? = null,

    @Column(name = "EMAIL_AUTH_YN")
    var emailAuthYn: String = "N",

    @Column(name = "H_PHONE_AUTH_YN")
    var hPhoneAuthYn: String = "N",

    @Column(name = "H_PHONE_AUTH_DT")
    var hPhoneAuthDt: Date? = null,

    @Column(name = "SECESS_YN", nullable = false)
    var secessYn: String = "N",

    @Column(name = "SECESS_ID")
    var secessId: String? = null,

    @Column(name = "SECESS_DT")
    var secessDt: Date? = null,

    @Column(name = "REG_ID", nullable = false)
    var regId: String,

    @Column(name = "REG_DT", nullable = false)
    var regDt: Date = Date(),

    @Column(name = "UDT_ID")
    var udtId: String? = null,

    @Column(name = "UDT_DT")
    var udtDt: Date? = null,

    @Column(name = "RECOM_ID")
    var recomId: String? = null
) {
    // 필요한 경우 추가 메서드를 구현하세요.
}
