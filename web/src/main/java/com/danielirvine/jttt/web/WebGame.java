package com.danielirvine.jttt.web;

import com.danielirvine.jttt.*;

public class WebGame implements MoveProvider {

  private final Game game;
  private Integer nextMove;

  public WebGame(Board existingBoard, boolean xHuman, boolean oHuman) {
    this.game = new Game(this, existingBoard, xHuman, oHuman);
  }

  public WebGame(int size, boolean xHuman, boolean oHuman) {
    this.game = new Game(this, size, xHuman, oHuman);
  }

  public WebGameState getGameState() {
    return new WebGameState(boardString(),
        statusString(),
        game.nextPlayerIdentifier(),
        game.isFinished()
        );
  }

  public void setNextMove(int sq) {
    nextMove = new Integer(sq);
  }

  public void playNextMove() {
    game.playNextMove();
  }

  public boolean hasAvailableMove() {
    return nextMove != null;
  }

  public int getMove(char mark) {
    Integer thisMove = nextMove;
    nextMove = null;
    return thisMove;
  }

  private String boardString() {
    int length = game.getNumSquares();
    char[] board = new char[length];
    for(int i = 0; i < length; ++i) {
      board[i] = game.markAt(i);
    }
    return String.valueOf(board);
  }

  private String instructionString() {
    if (game.getNextPlayer().hasAvailableMove()) {
      return "wait";
    }
    return "click a square";
  }

  private String statusString() {
    if (game.isWon()) {
      return winString();
    } else if (game.isDrawn()) {
      return drawString();
    } else {
      return moveString();
    }
  }

  private String drawString() {
    return String.format("It's a draw!");
  }

  private String winString() {
    return String.format("%s wins!",
        game.getLastPlayer().getMark());
  }

  private String moveString() {
    return String.format("%s's go, please %s",
        game.getNextPlayer().getMark(),
        instructionString());
  }
}
