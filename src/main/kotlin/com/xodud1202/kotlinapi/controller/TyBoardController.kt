package com.xodud1202.kotlinapi.controller

import com.xodud1202.kotlinapi.domain.Board
import com.xodud1202.kotlinapi.repository.BoardRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class TyBoardController (
	private val boardRepository: BoardRepository
) {

	/*
	@Value("\${spring.config.activate.on-profile}")
	var profile = ""
	*/

	@GetMapping("/boards")
	fun boardList(): List<Board> {
		return boardRepository.findAll()
	}
}