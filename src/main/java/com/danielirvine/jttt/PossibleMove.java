package com.danielirvine.jttt;


public class PossibleMove {

	private final Board board;
	private final int score;

  public PossibleMove(Board board, int score) {
		this.board = board;
		this.score = score;
  }

	public Board getBoard() {
		return board;
	}

	public int getScore() {
		return score;
	}
}
