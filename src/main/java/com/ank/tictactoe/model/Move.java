package com.ank.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Move {

	private Player moveBy;
 
	private String x;
 
	private String y;
}
