package com.xodud1202.kotlinapi.domain

data class GovHolidayApi (
    val locdate: String,       // 수신 일자
    val seq: String,           // 수신 순번
    val dateKind: String,      // 종류 (01: 국경일, 02: 기념일, 03: 24절기, 04: 잡절)
    val isHoliday: String,     // 휴일여부
    val dateName: String       // 명칭
)