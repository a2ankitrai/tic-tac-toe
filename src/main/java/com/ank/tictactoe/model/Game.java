package com.ank.tictactoe.model;

import java.util.List;

import lombok.Data;

@Data
public class Game {

	private int gameId;
	private int gameSize;
	private Player p1;
	private Player p2;
	private Player winner;
	private GameState gameState;
	
	private List<List<Integer>> board;
	
}
