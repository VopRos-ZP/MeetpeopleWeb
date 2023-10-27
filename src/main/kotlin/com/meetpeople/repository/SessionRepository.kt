package com.meetpeople.repository

import com.meetpeople.entity.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Long>