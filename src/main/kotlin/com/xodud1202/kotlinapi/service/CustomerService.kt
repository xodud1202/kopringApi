package com.xodud1202.kotlinapi.service

import com.xodud1202.kotlinapi.repository.CustomerRepository
import com.xodud1202.kotlinapi.support.exception.UsernameNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) : UserDetailsService {
	private val log = LoggerFactory.getLogger(this::class.java)

	override fun loadUserByUsername(custId: String): UserDetails {
		val customer = customerRepository.findByCustId(custId)
			?: throw UsernameNotFoundException("User not found with email: $custId")
		log.info("customer ::: {}", customer)

		val authorities = mutableListOf<GrantedAuthority>()

		// 권한을 추가합니다. 예시에서는 ROLE_USER를 추가합니다.
		authorities.add(SimpleGrantedAuthority(customer.custGrade))

		return User.builder()
			.username(custId)
			.password(customer.pwd)
			.authorities(authorities)
			.build()
	}

	// 사용자 추가, 수정 등의 로직 구현
}
