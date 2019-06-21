package com.ank.tictactoe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ank.tictactoe.GameException;
import com.ank.tictactoe.model.Game;
import com.ank.tictactoe.model.GameState;
import com.ank.tictactoe.model.GameVo;
import com.ank.tictactoe.model.Move;
import com.ank.tictactoe.model.Piece;

@Service
public class GameService {

	@Autowired
	List<Game> gameList;

	@Autowired
	AtomicInteger gameIdentifier;

	public GameVo createGame(GameVo gameVo) {

		Game game = new Game();
		game.setGameId(gameIdentifier.incrementAndGet());
		game.setGameSize(gameVo.getGameSize());

		List<List<Integer>> board = new ArrayList<List<Integer>>();

		for (int i = 0; i < game.getGameSize(); i++) {

			List<Integer> rowList = new ArrayList<Integer>(game.getGameSize());
			for (int j = 0; j < game.getGameSize(); j++) {
				rowList.add(-1);
			}
			board.add(rowList);
		}

		game.setBoard(board);

		gameVo.getP1().setPiece(Piece.X);
		gameVo.getP2().setPiece(Piece.O);
		game.setP1(gameVo.getP1());
		game.setP2(gameVo.getP2());
		game.setGameState(GameState.STARTED);

		gameList.add(game);

		gameVo.setGameId(game.getGameId());

		return gameVo;
	}

	public GameVo makeMove(int gameId, Move move) {

		Game game = this.gameList.stream()

				.filter(g -> g.getGameId() == gameId)

				.findAny()

				.orElse(null);

		if (game == null) {
			throw new GameException("No game Exist by game Id", HttpStatus.BAD_REQUEST);
		}

		if (game.getGameState() != GameState.PROGRESS && game.getGameState() != GameState.STARTED) {
			throw new GameException("Game has already been completed or aborted", HttpStatus.BAD_REQUEST);
		}

		int num = -1;

		if (move.getMoveBy().getPlayerId().equals(game.getP1().getPlayerId())) {
			num = 1;
		} else if (move.getMoveBy().getPlayerId().equals(game.getP2().getPlayerId())) {
			num = 0;
		} else {
			throw new GameException("Invalid Player has made the request", HttpStatus.BAD_REQUEST);
		}

		int posX = Integer.parseInt(move.getX()) - 1;
		int posY = Integer.parseInt(move.getY()) - 1;

		List<List<Integer>> board = game.getBoard();

		if (board.get(posX).get(posY) != -1) {
			throw new GameException("Request has been made for invalid position", HttpStatus.BAD_REQUEST);
		}

		board.get(posX).set(posY, num);

		int gameState = getGameStateAfterMove(board);
		if (gameState == 0) {
			game.setGameState(GameState.COMLPETED);
			game.setWinner(game.getP2());
		} else if (gameState == 1) {
			game.setGameState(GameState.COMLPETED);
			game.setWinner(game.getP1());
		} else {
			game.setGameState(GameState.PROGRESS);
		}

		GameVo gameVo = new GameVo();
		gameVo.setGameId(game.getGameId());
		gameVo.setGameSize(game.getGameSize());
		gameVo.setGameState(game.getGameState());
		gameVo.setP1(game.getP1());
		gameVo.setP2(game.getP2());
		gameVo.setWinner(game.getWinner());

		return gameVo;
	}

	public String getGameStatus(int gameId) {
		Game game = this.gameList.stream()

				.filter(g -> g.getGameId() == gameId)

				.findAny()

				.orElse(null);

		if (game == null) {
			throw new GameException("No game Exist by game Id", HttpStatus.BAD_REQUEST);
		}

		return game.getGameState().toString();
	}

	public int getGameStateAfterMove(List<List<Integer>> board) {

		// check for each row
		for (List<Integer> row : board) {
			int num = row.get(0);
			if (num == -1)
				continue;

			int count = 0;
			for (Integer rowNum : row) {
				if (rowNum != num) {
					break;
				}
				count++;
			}
			if (count == row.size()) {
				return num;
			}
		}

		// check for each column
		for (int i = 0; i < board.size(); i++) {

			int num = board.get(0).get(i);
			if (num == -1) {
				continue;
			}

			int count = 0;
			for (int j = 0; j < board.size(); j++) {

				if (board.get(j).get(i) != num) {
					break;
				}
				count++;
			}
			if (count == board.size()) {
				return num;
			}
		}

		// check left diagonal
		int leftDiag = board.get(0).get(0);

		if (leftDiag != -1) {
			int count = 0;
			for (int i = 0; i < board.size(); i++) {

				if (board.get(i).get(i) != leftDiag) {
					break;
				}
				count++;
			}
			if (count == board.size()) {
				return leftDiag;
			}
		}

		// check right diagonal
		int rightDiag = board.get(0).get(board.size() - 1);

		if (rightDiag != -1) {
			int count = 0;
			for (int i = board.size() - 1; i >= 0; i--) {

				if (board.get(i).get(i) != rightDiag) {
					break;
				}
				count++;
			}
			if (count == board.size()) {
				return rightDiag;
			}
		}

		return -1;
	}

}
