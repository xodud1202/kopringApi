package com.xodud1202.kotlinapi.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="TB_BOARD")
class Board (
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	val boardNo: Int,
	val boardDiv: String,
	val detailDiv: String,
	val title: String,
	val content: String,
	val viewYn: String,
	val readCnt: Int,
	val dispOrd: Int,
	val orgFileNm: String,
	val sysFileNm: String,
	val delYn: String,
	val regId: String,
	val regDt: LocalDateTime,
	val udtId: String,
	val udtDt: LocalDateTime,
)