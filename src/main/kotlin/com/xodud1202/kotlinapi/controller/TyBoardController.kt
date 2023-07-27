package com.xodud1202.kotlinapi.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class TyBoardController {

	@Value("\${spring.config.activate.on-profile}")
	var profile = ""

	@GetMapping("/test")
	fun apiTest(): String {
		return "Hello $profile!!"
	}
}