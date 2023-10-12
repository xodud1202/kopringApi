package com.xodud1202.kotlinapi.repository

import com.xodud1202.kotlinapi.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, String> {
    fun findByCustId(custId: String): Customer?
}
