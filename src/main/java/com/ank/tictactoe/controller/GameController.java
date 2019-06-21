package com.ank.tictactoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ank.tictactoe.config.validator.Numeric;
import com.ank.tictactoe.model.GameVo;
import com.ank.tictactoe.model.Move;
import com.ank.tictactoe.service.GameService;

@RestController
public class GameController {

	@Autowired
	GameService gameService;

	@PostMapping("/game")
	public ResponseEntity<GameVo> createGame(@RequestBody GameVo gameVo) {
		gameVo = gameService.createGame(gameVo);

		ResponseEntity<GameVo> response = null;

		HttpStatus status = gameVo != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		response = new ResponseEntity<>(gameVo, status);
		return response;
	}

	@PutMapping("/game/{id}")
	public ResponseEntity<GameVo> move(@PathVariable(value = "id") @Numeric String gameId, @RequestBody Move move) {
		GameVo gameVo = gameService.makeMove(Integer.parseInt(gameId), move);

		ResponseEntity<GameVo> response = null;

		HttpStatus status = gameVo != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		response = new ResponseEntity<>(gameVo, status);
		return response;
	}
	
	@GetMapping("/game/{id}")
	public String getGameStatus(@PathVariable(value = "id") @Numeric String gameId) {
		return gameService.getGameStatus(Integer.parseInt(gameId));
	}

}
