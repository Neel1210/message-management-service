package com.abcde.message_management_service.service;

import com.abcde.message_management_service.dto.CodeExecutionRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CodeExecutionProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbit.exchange}")
    private String exchange;

    @Value("${app.rabbit.routing-key}")
    private String routingKey;

    public CodeExecutionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(CodeExecutionRequestDTO dto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, dto);
        log.info("Message Sent: " + dto.getRequestId());
    }
}