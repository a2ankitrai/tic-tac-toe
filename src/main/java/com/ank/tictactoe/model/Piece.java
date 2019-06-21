package com.ank.tictactoe.model;

public enum Piece {

	X(1), O(0);

	private int value;

	Piece(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
