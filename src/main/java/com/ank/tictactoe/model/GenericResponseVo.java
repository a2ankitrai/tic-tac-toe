package com.ank.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponseVo {

	private String message;
    private String timeStamp;
}
