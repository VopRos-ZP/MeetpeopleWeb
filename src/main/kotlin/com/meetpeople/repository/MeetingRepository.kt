package com.meetpeople.repository

import com.meetpeople.entity.Meeting
import org.springframework.data.jpa.repository.JpaRepository

interface MeetingRepository : JpaRepository<Meeting, Long>