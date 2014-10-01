package com.danielirvine.jttt.web;

public class WebGameState {

  private final String board;
  private final String statusText;
  private final String nextMove;
  private final boolean finished;

  public WebGameState(String board, String statusText, String nextMove, boolean finished) {
    this.board = board;
    this.statusText = statusText;
    this.nextMove = nextMove;
    this.finished = finished;
  }

  public String getBoard() {
    return board;
  }

  public String getStatusText() {
    return statusText;
  }

  public String getNextMove() {
    return nextMove;
  }

  public boolean getFinished() {
    return finished;
  }

}
