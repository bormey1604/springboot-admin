package com.techgirl.student_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String statusCode;
    private String message;
    private Object data;
}
