package com.example.usermanagement.payload.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorData {
    private String message;

    private Integer errorCode;

    private String fieldName;

    public ErrorData(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
