package com.meetpeople.repository

import com.meetpeople.entity.Dialog
import org.springframework.data.jpa.repository.JpaRepository

interface DialogRepository : JpaRepository<Dialog, Long>