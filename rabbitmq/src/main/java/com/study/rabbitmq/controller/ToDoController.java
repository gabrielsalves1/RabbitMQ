package com.study.rabbitmq.controller;

import com.rabbitmq.lib.constants.RabbitMQConstants;
import com.rabbitmq.lib.dto.ToDoDto;
import com.study.rabbitmq.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ToDoController {
    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    private ResponseEntity insert(@RequestBody ToDoDto toDoDto) {
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_TO_DO, toDoDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
