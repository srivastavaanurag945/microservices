package com.Students.Students;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
	 private LocalDateTime timestamp;
	    private String message;
	    private String details;

	    public ExceptionResponse(LocalDateTime timestamp, String message, String details){
	        this.timestamp = timestamp;
	        this.message = message;
	        this.details = details;
	    }
}
