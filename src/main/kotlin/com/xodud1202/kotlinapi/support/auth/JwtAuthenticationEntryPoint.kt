package com.xodud1202.kotlinapi.support.auth

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 인증 오류 처리를 위한 클래스
 */
@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {
	override fun commence(
		request: HttpServletRequest?,
		response: HttpServletResponse?,
		authException: AuthenticationException?
	) {
		response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
	}
}
