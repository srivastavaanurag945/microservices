package com.Students.Students;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;

@RestController
@ControllerAdvice
public class CustomResponseHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) throws Exception {
	        ExceptionResponse expResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
	                request.getDescription(false));
	        return new ResponseEntity<>(expResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    @ExceptionHandler(StudentNotFoundException.class)
	    public final ResponseEntity<ExceptionResponse> handleStudentNotFoundException(Exception ex, WebRequest request) throws Exception {
	        ExceptionResponse expResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
	                request.getDescription(false));
	        return new ResponseEntity<>(expResponse, HttpStatus.NOT_FOUND);
	    }
	    @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	        String fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.joining(", "));
	        ExceptionResponse validationResponse = new ExceptionResponse(LocalDateTime.now(), "Validation Error", fieldErrors);
	        return new ResponseEntity<>(validationResponse, HttpStatus.BAD_REQUEST);
	    }
}