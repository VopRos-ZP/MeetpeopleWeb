package com.meetpeople.controller

import com.meetpeople.dto.MeetingDTO
import com.meetpeople.entity.Meeting
import com.meetpeople.service.EntityService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v0/meetings")
class MeetingController(
    meetingService: EntityService<Meeting, MeetingDTO>
): Controller<Meeting, MeetingDTO>(meetingService)