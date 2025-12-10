package com.abcde.message_management_service.controller;

import com.abcde.message_management_service.dto.CodeExecutionRequestDTO;
import com.abcde.message_management_service.service.CodeExecutionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {

    private final CodeExecutionProducer producer;

    @PostMapping("/execute")
    public ResponseEntity<?> sendMessage(@RequestBody CodeExecutionRequestDTO dto) {
        dto.setRequestId(UUID.randomUUID().toString());
        producer.send(dto);
        return ResponseEntity.ok("Queued with ID: " + dto.getRequestId());
    }

    @GetMapping("/test")
    public ResponseEntity<?> testMessage() {
        CodeExecutionRequestDTO dto = new CodeExecutionRequestDTO();
        dto.setRequestId(UUID.randomUUID().toString());
        dto.setCode("code : "+ LocalDateTime.now());
        producer.send(dto);
        return ResponseEntity.ok("Queued with ID: " + dto.getRequestId());
    }
}