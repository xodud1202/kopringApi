package com.xodud1202.kotlinapi.support.filter

import com.xodud1202.kotlinapi.service.CustomerService
import com.xodud1202.kotlinapi.support.utils.JwtTokenUtil
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter @Autowired constructor(
	private val customerService: CustomerService, // 여기서는 CustomerService 인스턴스를 주입하면 됩니다.
	private val jwtTokenUtil: JwtTokenUtil // 이 클래스도 만들어주셔야 합니다. 후술할 예정입니다.
) : OncePerRequestFilter() {

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		chain: FilterChain
	) {
		val requestTokenHeader = request.getHeader("Authorization")

		var custId: String? = null
		var jwtToken: String? = null
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7)
			try {
				custId = jwtTokenUtil.getUsernameFromToken(jwtToken)
			} catch (e: IllegalArgumentException) {
				logger.error("Unable to get JWT Token")
			} catch (e: ExpiredJwtException) {
				logger.error("JWT Token has expired")
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String")
		}

		if (custId != null && SecurityContextHolder.getContext().authentication == null) {
			val userDetails: UserDetails = customerService.loadUserByUsername(custId)

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.authorities
				)
				usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
				SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
			}
		}
		chain.doFilter(request, response)
	}
}
