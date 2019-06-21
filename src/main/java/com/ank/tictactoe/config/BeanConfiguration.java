package com.ank.tictactoe.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ank.tictactoe.model.Game;

@Configuration
public class BeanConfiguration {

	@Bean(name = "gameList")
	public List<Game> gameList() {

		List<Game> gameList = new ArrayList<Game>();
		return gameList;
	}
	
	@Bean(name = "gameIdentifier")
	public AtomicInteger stockIdentifier() {
		return new AtomicInteger(0);
	}
}
