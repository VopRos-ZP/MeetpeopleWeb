package com.meetpeople.controller

import com.meetpeople.entity.Meeting
import com.meetpeople.service.MeetingService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v0/meetings")
class MeetingController(meetingService: MeetingService): Controller<Meeting>(meetingService)