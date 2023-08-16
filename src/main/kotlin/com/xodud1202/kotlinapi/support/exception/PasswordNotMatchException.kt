package com.xodud1202.kotlinapi.support.exception

import org.springframework.security.core.AuthenticationException

/**
 * 패스워드가 동일하지 않을 경우
 * @author gagamel
 * @since 2021. 2. 15
 */
class PasswordNotMatchException : AuthenticationException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
