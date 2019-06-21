package com.ank.tictactoe;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ank.tictactoe.model.GenericResponseVo;

@ControllerAdvice
public class GameResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(GameException.class)
	protected ResponseEntity<Object> handleStockException(GameException ex, WebRequest request) {

		GenericResponseVo genericResponseVo = new GenericResponseVo(ex.getMessage(), LocalDateTime.now().toString());

		ResponseEntity<Object> response = new ResponseEntity<Object>(genericResponseVo, HttpStatus.BAD_REQUEST);
		return response;
	}
}
