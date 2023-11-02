package com.meetpeople.controller

import com.meetpeople.entity.Dialog
import com.meetpeople.service.DialogService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v0/dialogs")
class DialogController(service: DialogService): Controller<Dialog>(service)