package com.example.usermanagement.exceptions;

import com.example.usermanagement.payload.api.ErrorData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestException extends RuntimeException {

    private String userMsg;
    private HttpStatus status;
    private List<ErrorData> errors;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public RestException(String userMsg, HttpStatus status) {
        super(userMsg);
        this.userMsg = userMsg;
        this.status = status;
    }

    public static RestException restThrow(String userMsg, HttpStatus httpStatus) {
        return new RestException(userMsg, httpStatus);
    }

    public static RestException restThrow(String userMsg) {
        return new RestException(userMsg, HttpStatus.BAD_REQUEST);
    }

}
