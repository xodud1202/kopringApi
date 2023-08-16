package com.xodud1202.kotlinapi.support.config

import com.xodud1202.kotlinapi.service.CustomerService
import com.xodud1202.kotlinapi.support.auth.JwtAuthenticationEntryPoint
import com.xodud1202.kotlinapi.support.filter.JwtRequestFilter
import com.xodud1202.kotlinapi.support.handler.AuthenticationFailureHandler
import com.xodud1202.kotlinapi.support.handler.AuthenticationSuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

	@Autowired
	private lateinit var authenticationSuccessHandler: AuthenticationSuccessHandler

	@Autowired
	private lateinit var authenticationFailureHandler: AuthenticationFailureHandler

	@Autowired
	private lateinit var jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint

	@Autowired
	private lateinit var customerService: CustomerService

	@Autowired
	private lateinit var jwtRequestFilter: JwtRequestFilter

	@Autowired
	fun configureGlobal(auth: AuthenticationManagerBuilder) {
		auth.userDetailsService(customerService)
			.passwordEncoder(passwordEncoder())
	}

	@Bean
	fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}

	@Bean
	override fun authenticationManagerBean(): AuthenticationManager {
		return super.authenticationManagerBean()
	}

	override fun configure(http: HttpSecurity) {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/**").permitAll() // 모든 경로에 대한 접근 허용
			.anyRequest().authenticated()
			.and().formLogin().loginProcessingUrl("/login")
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
			.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
	}
}
