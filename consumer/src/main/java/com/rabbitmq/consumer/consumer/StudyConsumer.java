package com.rabbitmq.consumer.consumer;

import com.rabbitmq.lib.constants.RabbitMQConstants;
import com.rabbitmq.lib.dto.StudyDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StudyConsumer {

    @RabbitListener(queues = RabbitMQConstants.QUEUE_STUDIES)
    private void consumer(StudyDto studyDto) {
        System.out.println(studyDto.getName());
        System.out.println(studyDto.getDuration());
        System.out.println("------------------------------------");
    }
}
