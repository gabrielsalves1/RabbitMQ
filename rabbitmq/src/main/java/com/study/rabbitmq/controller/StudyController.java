package com.study.rabbitmq.controller;

import com.rabbitmq.lib.constants.RabbitMQConstants;
import com.rabbitmq.lib.dto.StudyDto;
import com.study.rabbitmq.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studies")
public class StudyController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    private ResponseEntity insert(@RequestBody StudyDto studyDto) {
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_STUDIES, studyDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
