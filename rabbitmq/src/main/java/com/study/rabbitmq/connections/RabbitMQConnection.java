package com.study.rabbitmq.connections;

import com.rabbitmq.lib.constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String EXCHANGE_NAME = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding relation(Queue queue, DirectExchange directExchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add() {
        Queue queueStudies = this.queue(RabbitMQConstants.QUEUE_STUDIES);
        Queue queueToDo = this.queue(RabbitMQConstants.QUEUE_TO_DO);

        DirectExchange exchange = this.directExchange();

        Binding linkStudies = this.relation(queueStudies, exchange);
        Binding linkToDo = this.relation(queueToDo, exchange);

        this.amqpAdmin.declareQueue(queueStudies);
        this.amqpAdmin.declareQueue(queueToDo);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(linkStudies);
        this.amqpAdmin.declareBinding(linkToDo);
    }
}
