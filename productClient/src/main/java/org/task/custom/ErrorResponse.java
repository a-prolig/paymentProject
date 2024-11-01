package org.task.custom;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorCode;
    private String errorDescription;
}
