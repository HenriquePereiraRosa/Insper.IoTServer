package com.insper.iotserver.exceptionhandler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.insper.iotserver.exceptionhandler.util.ErrorMsg;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String userMsg = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String devMsg = ex.getCause().toString();
		
		return handleExceptionInternal(ex, new ErrorMsg(userMsg, devMsg), headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ NullPointerException.class })
	protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex, // TODO: throwing java.lang.IllegalStateException: Could not resolve parameter [1] in protected org.springframework.http.ResponseEntity<java.lang.Object> com.helm.iotserver.exceptionhandler.ApiExceptionHandler.handleNullPointerException(java.lang.NullPointerException,org.springframework.http.HttpHeaders,org.springframework.http.HttpStatus,org.springframework.web.context.request.WebRequest): No suitable resolver
			WebRequest request) {
		
		String userMsg = messageSource.getMessage("message.null.pointer", null, LocaleContextHolder.getLocale());
		String devMsg = String.valueOf(ex.getCause());
		
		return handleExceptionInternal(ex, new ErrorMsg(userMsg, devMsg), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler({ SQLIntegrityConstraintViolationException.class })
	protected ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex,
			WebRequest request) {
		
		String userMsg = messageSource.getMessage("message.id.invalid", null, LocaleContextHolder.getLocale());
		String devMsg = String.valueOf(ex.getCause());
		
		return handleExceptionInternal(ex, new ErrorMsg(userMsg, devMsg), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}


	@ExceptionHandler({ ConstraintViolationException.class })
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
																		WebRequest request) {

		String userMsg = messageSource.getMessage("message.id.invalid", null, LocaleContextHolder.getLocale());
		String devMsg = String.valueOf(ex.getCause());

		return handleExceptionInternal(ex, new ErrorMsg(userMsg, devMsg), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}


	@ExceptionHandler({ AccessDeniedException.class })
	protected ResponseEntity<Object> handleConstraintViolationException(AccessDeniedException ex,
																		WebRequest request) {

		String userMsg = messageSource.getMessage("message.access_denied", null, LocaleContextHolder.getLocale());
		String devMsg = String.valueOf(ex.getCause());

		return handleExceptionInternal(ex, new ErrorMsg(userMsg, devMsg), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}
