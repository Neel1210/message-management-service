package com.abcde.message_management_service.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class CodeExecutionRequestDTO implements Serializable {
    private String requestId;      // Unique ID for correlating request/response
    private String code;           // User's submitted code
    private String language;       // java / python / cpp etc.
    private String input;          // Stdin input (optional)
    private Long userId;           // To track user's submissions (optional)
    private Long contestId;        // If linked with contest (optional)
    private Long problemId;        // If linked with coding problem (optional)
}