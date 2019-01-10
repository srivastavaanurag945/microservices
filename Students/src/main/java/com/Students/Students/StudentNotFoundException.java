package com.Students.Students;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{
	 public StudentNotFoundException(String message) {
	        super(message);
	    }
	 public StudentNotFoundException() {
	        super();
	    }
}
