package com.example.base;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.example.base.dto.ErrorDetailsDTO;
import com.example.base.dto.FieldErrorDTO;
import com.example.user.exception.UserNotFoundException;

/**
 * Global Exception handler for application.
 * 
 * @author Salman.Khandu
 *
 */
@ControllerAdvice
public class RestApiGlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetailsDTO> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetailsDTO errorDetails = new ErrorDetailsDTO(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDetailsDTO handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {

		String errorURL = req.getRequestURL().toString();

		ErrorDetailsDTO errorInfo = new ErrorDetailsDTO(errorURL, HttpStatus.BAD_REQUEST.toString());

		BindingResult result = ex.getBindingResult();
		if (result.getFieldErrors().isEmpty()) {
			errorInfo.getFieldErrors()
					.addAll(result.getAllErrors().stream()
							.map(error -> new FieldErrorDTO(error.getCode(), error.getDefaultMessage()))
							.collect(Collectors.toList()));
		} else {
			errorInfo.getFieldErrors().addAll(populateFieldErrors(result.getFieldErrors()));
		}

		return errorInfo;
	}

	public List<FieldErrorDTO> populateFieldErrors(List<FieldError> fieldErrorList) {

		return fieldErrorList.stream()
				.map(fieldError -> new FieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()))
				.collect(Collectors.toList());

	}

	@ExceptionHandler({ UserNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ResponseEntity<ErrorDetailsDTO> handleUnauthorisedUserException(HttpServletRequest req,
			UserNotFoundException usernameNotFoundException) {
		String errorURL = req.getRequestURL().toString();
		ErrorDetailsDTO errorInfo = new ErrorDetailsDTO(errorURL, usernameNotFoundException.getMessage());

		return new ResponseEntity<>(errorInfo, null, HttpStatus.NOT_FOUND);
	}

}
