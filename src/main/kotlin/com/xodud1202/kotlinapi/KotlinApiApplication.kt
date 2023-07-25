package com.xodud1202.kotlinapi

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.PostConstruct

@SpringBootApplication
class KotlinApiApplication {
	@Value("\${my-app.welcome-message}")
	val myAppWelcomeMessage: String = ""

	@PostConstruct
	fun printMyAppWelcomeMessage() {
		println(myAppWelcomeMessage)
	}
}

fun main(args: Array<String>) {
	runApplication<KotlinApiApplication>(*args)
}
