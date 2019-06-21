package com.ank.tictactoe.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GameVo {

	private int gameId;

	@NotNull
	@Min(value = 3)
	@Max(value = 16)
	private int gameSize;

	@NotNull
	private Player p1;

	@NotNull
	private Player p2;

	private GameState gameState;

	private Player winner;

}
