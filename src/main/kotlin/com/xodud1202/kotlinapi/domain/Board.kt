package com.xodud1202.kotlinapi.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "TB_BOARD")
class Board (
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOARD_NO")
	val boardNo: Int,
	@Column(name = "BOARD_DIV")
	val boardDiv: String,
	@Column(name = "DETAIL_DIV")
	val detailDiv: String,
	@Column(name = "TITLE")
	val title: String,
	@Column(name = "CONTENT")
	val content: String,
	@Column(name = "VIEW_YN")
	val viewYn: String,
	@Column(name = "READ_CNT")
	val readCnt: Int,
	@Column(name = "DISP_ORD")
	val dispOrd: Int,
	@Column(name = "ORG_FILE_NAME")
	val orgFileNm: String,
	@Column(name = "SYS_FILE_NM")
	val sysFileNm: String,
	@Column(name = "DEL_YN")
	val delYn: String,
	@Column(name = "REG_ID")
	val regId: String,
	@Column(name = "REG_DT")
	val regDt: LocalDateTime,
	@Column(name = "UDT_ID")
	val udtId: String,
	@Column(name = "UDT_DT")
	val udtDt: LocalDateTime,
)