package com.meetpeople.controller

import com.meetpeople.entity.Comment
import com.meetpeople.service.CommentService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v0/comments")
class CommentController(commentService: CommentService): Controller<Comment>(commentService)