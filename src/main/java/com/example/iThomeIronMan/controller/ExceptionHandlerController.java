package com.example.iThomeIronMan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.iThomeIronMan.service.ex.ServiceException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler({BindException.class})
	public ResponseEntity<?> handleBindException(Throwable e) {
		
		BindingResult results = ((BindException) e).getBindingResult();

		// 取得第一個例外訊息
		String message = results.getFieldErrors().get(0).getDefaultMessage();

		// 取得全部例外訊息
		// for(FieldError er :results.getFieldErrors()) {
		// 	System.err.println(er.getDefaultMessage());
		// 	System.err.println(er.getField());
		// }
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@ExceptionHandler({ServiceException.class})
	public ResponseEntity<?> handleServiceException(Throwable e) {
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
	}
	
}
