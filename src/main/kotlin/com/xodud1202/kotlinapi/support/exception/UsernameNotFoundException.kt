package com.xodud1202.kotlinapi.support.exception

import org.springframework.security.core.AuthenticationException

/**
 * 로그인 정보가 없을 경우 발생시키는 예외
 *
 * @author xodud1202
 * @since  2021. 2. 15
 */
class UsernameNotFoundException : AuthenticationException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
