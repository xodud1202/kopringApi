package com.xodud1202.kotlinapi.repository

import com.xodud1202.kotlinapi.domain.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, String>