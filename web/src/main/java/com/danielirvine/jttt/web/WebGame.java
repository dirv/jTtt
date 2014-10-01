package com.danielirvine.jttt.web;

import com.danielirvine.jttt.*;

public class WebGame implements MoveProvider {

  private final Game game;

  public WebGame(Board existingBoard, boolean xHuman, boolean oHuman) {
    this.game = new Game(this, existingBoard, xHuman, oHuman);
  }

  public WebGame(int size, boolean xHuman, boolean oHuman) {
    this.game = new Game(this, size, xHuman, oHuman);
  }

  public WebGameState getGameState() {
    int length = game.getNumSquares();
    char[] board = new char[length];
    for(int i = 0; i < length; ++i) {
      board[i] = game.markAt(i);
    }
    boolean requiresInput = !game.getNextPlayer().hasAvailableMove();
    char mark = game.getNextPlayer().getMark();

    String message = null;
    if(requiresInput) {
      message = "click a square";
    } else {
      message = "wait";
    }
    String status = String.format("%s's go, please %s", mark, message);
    return new WebGameState(String.valueOf(board),
        status,
        requiresInput,
        game.isFinished()
        );
  }

  public boolean hasAvailableMove() {
    return false;
  }

  public int getMove(char mark) {
    return 0;
  }
}
