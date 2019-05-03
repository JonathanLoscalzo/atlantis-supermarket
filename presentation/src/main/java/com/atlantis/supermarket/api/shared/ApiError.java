package com.atlantis.supermarket.api.shared;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * In case of error, validation or exception app returns a controlled error
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiError {
    HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timestamp;
    String message;
    String debugMessage;
    List<ApiSubError> subErrors;

    private ApiError() {
	timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
	this();
	this.status = status;
    }

    /**
     * in case of exception unexpected
     * 
     * @param status
     * @param ex
     */

    public ApiError(HttpStatus status, Throwable ex) {
	this();
	this.status = status;
	this.message = "Unexpected error";
	this.debugMessage = ex.getLocalizedMessage();
    }

    /**
     * in case of controlled exception (domain exceptions)
     * 
     * @param status
     * @param message
     * @param ex
     */
    public ApiError(HttpStatus status, String message, Throwable ex, List<ApiSubError> suberrors) {
	this();
	this.status = status;
	this.message = message;
	this.debugMessage = ex.getMessage();
	this.subErrors = suberrors;
    }

}
